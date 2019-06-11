
package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.server.StoreI;

import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.vistor.PalindromVisitor;
import genericCheckpointing.vistor.PrimeVisitorImpl;
import genericCheckpointing.vistor.VisitorI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

// import the other types used in this file

public class Driver {

    public static void main(String[] args) {

		// FIXME: read the value of checkpointFile from the command line
	    //Command Line Validation
	    if(args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || 
	    		args[2].equals("${arg2}") ) {
	    	System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
	    	System.err.println("Please enter: 1)Mode 2)N(number of objects) 3)Checkpoint File Name.");
	    	System.exit(1);
	    }
	    
	    //Set the values from the command line
	    int NUM_OF_OBJECTS = 0;
	    try {
	    	NUM_OF_OBJECTS = Integer.parseInt(args[1]);
	    }catch(NumberFormatException e) {
	    	System.err.println("Error: The second argument could not be converted to an int.");
	    	e.printStackTrace();
	    	System.exit(1);
	    }
	    //Get the mode and the filename
	    String mode = args[0];
	    String fileName = args[2];
	    
	
		ProxyCreator pc = new ProxyCreator();
	
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler)
		StoreRestoreHandler handler = new StoreRestoreHandler();
		
		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 },
									 handler
									 );
	
		// FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file
		handler.openFile(fileName);
		
		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;
		
		// Use an if/switch to proceed according to the command line argument
		// For deser, just deserialize the input file into the data structure and then print the objects
		if(mode.equals("deser") ) {
			//Create the temporary variable and vector to store info
			SerializableObject myRecordedRet = null;
			Vector<SerializableObject> myVec = new Vector<SerializableObject>();
			
			//Go through the file for the designated number of objects
			//	and serialize the objects
			for(int i = 0; i < NUM_OF_OBJECTS; i++) {
				myRecordedRet = ((RestoreI) cpointRef).readObj("XML");
				myVec.add(myRecordedRet);
			}
			
			//Print out the objects stored in the vector
			for(int i = 0; i < myVec.size(); i++) {
				System.out.println( myVec.get(i) );
			}
			
		}else if(mode.equals("serdeser") ) {
			// The code below is for "serdeser" mode
			// For "serdeser" mode, both the serialize and deserialize functionality should be called.
		
			// create a data structure to store the objects being serialized
			Vector<SerializableObject> serVec = new Vector<SerializableObject>();
			
		    // NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
			// passed as "N" from the command line.
			
			for (int i=0; i<NUM_OF_OBJECTS; i++) {
	
			    // FIXME: create these object instances correctly using an explicit value constructor
			    // use the index variable of this loop to change the values of the arguments to these constructors
			    myFirst = new MyAllTypesFirst(i);
			    mySecond = new MyAllTypesSecond(i);
			    
			    //Add the new references to the serVector
			    serVec.add(myFirst);
			    serVec.add(mySecond);
	
			    // FIXME: store myFirst and mySecond in the data structure
			    // authID (13 and 17) is not being used in the assignment, but
			    // is left for future use.
			    ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
			    ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");
	
			}
	
			SerializableObject myRecordRet;
			
			// create a data structure to store the returned objects
			Vector<SerializableObject> deserVec = new Vector<SerializableObject>();
			
			//Iterate through the file and deser all of the objects
			for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
	
			    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			    // FIXME: store myRecordRet in the vector (or arrayList)
			    deserVec.add(myRecordRet);
			}
	
			// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
			handler.closeFile();
			
			// FIXME: compare and confirm that the serialized and deserialzed objects are equal.
			// The comparison should use the equals and hashCode methods. Note that hashCode
			// is used for key-value based data structures
			int numDiff = 0;
			for(int i = 0; i < 2*NUM_OF_OBJECTS; i++) {
				/*
				System.out.println("----------------------------------------");
				System.out.println("serVec Value");
				System.out.println(serVec.get(i));
				System.out.println("deserVec Value");
				System.out.println(deserVec.get(i));
				*/
				
				if( serVec.get(i).equals( deserVec.get(i) ) ) {
					//System.out.println("DECISION: TRUE");
				}else {
					//System.out.println("DECISION: FALSE");
					numDiff++;
				}
			}
			System.out.print("Number of differnces between serialization and deserialization: ");
			System.out.println(numDiff);
			
	
	        // FIXME
	        // Create an instance of the PrimeVisitorImpl and use it to determine the number of 
			//	unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond
			VisitorI primeVisitor = new PrimeVisitorImpl();
			for(int i = 0; i < serVec.size(); i++) {
				//Cast the pointer to count the number of primes
				if(serVec.get(i).getClass() == (MyAllTypesFirst.class) ) {
					MyAllTypesFirst temp = (MyAllTypesFirst)serVec.get(i);
					temp.accept(primeVisitor);
				}else {
					MyAllTypesSecond temp = (MyAllTypesSecond)serVec.get(i);
					temp.accept(primeVisitor);
				}
			}
			
			//Print out the number of primes
			System.out.print("Number of unique prime numbers found: ");
			System.out.println( ((PrimeVisitorImpl)primeVisitor).numUniquePrimes );

	        // FIXME
	        // Create an instance of the PalindromeVisitorImpl and use it to determine the 
	        // number of unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond
	       	VisitorI palindromeVisitor = new PalindromVisitor();
	       	for(int i = 0; i < serVec.size(); i++) {
	       		//Cast pointer to check if there are palindromes
	       		if(serVec.get(i).getClass() == (MyAllTypesFirst.class) ) {
					MyAllTypesFirst temp = (MyAllTypesFirst)serVec.get(i);
					temp.accept(palindromeVisitor);
				}else {
					MyAllTypesSecond temp = (MyAllTypesSecond)serVec.get(i);
					temp.accept(palindromeVisitor);
				}
	       	}
			//Print out the number of palindromes
	       	System.out.print("Number of palindromes found: ");
	       	System.out.println( ((PalindromVisitor)palindromeVisitor).numPalindromes );
		}else {
			//Error where mode is neither deser or serdeser
			System.err.println("Error: No valid mode selected. Please choose 'deser' or 'serdeser' ");
			System.exit(1);
		}
	
    }
    
}
