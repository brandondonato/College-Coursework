package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.SerStrategy;
import genericCheckpointing.xmlStoreRestore.DeserStrategy;


//Imports for file reading + writing + exceptions
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NullPointerException;


public class StoreRestoreHandler implements InvocationHandler {
	//Member variables for file reading and writing
	private File file;
	private BufferedReader buffReader;
	private String fileName;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//Get the method name and class names of the invoked methods
		String methodName = method.getName();
		Class<?> className = method.getDeclaringClass();
		
		//If the method is write from the StoreI
		if(methodName.equals("writeObj") ) {
			//System.out.print("Writing to file with wire format: ");
			
			//Verify that the wire format is XML
			if("XML".equals(args[2]) ) {
				//System.out.println(args[2]);
				
				//Now send the information to the helper function in
				//	XMLSerialization to serialize the object and write
				//	it to the given file
				this.serializeData((SerializableObject)args[0], new XMLSerialization() );
				
			}else{
				System.err.print("\nError: The given Strategy is not accepted.");
				System.err.println(" Please give the Strategy \"XML.\"");
				System.exit(1);
			}

			return null;
			
		//If the method is read	
		}else if(methodName.equals("readObj") ) {
			//Reading from the file with args[0] = "XML"
			//System.out.print("Reading from file with wire format: ");
			
			//Verify that the wire format is XML
			if("XML".equals(args[0]) ) {
				//System.out.println(args[0]);
				
				//Now, send the information to some helper function to 
				//	deserialize the information.
				SerializableObject retObj = null;
				retObj = this.deserializeData( new XMLDeserialization() );
				return retObj;
			}else {
				System.err.print("\nError: The given Strategy is not accepted.");
				System.err.println(" Please give the Strategy \"XML.\"");
				System.exit(1);
			}
		
			return null;
		}else {
			return null;
		}
	}
	/**
	 * 
	 * @param sObject = The object to be serialized and written to file
	 * @param sStrategy = XMLSerialization parameter which outputs to file
	 */
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject, this);
	}
	
	/**
	 * Delegates the processing to XMLDeerialization, as part of the strategy pattern
	 * @param dStrategy = XMLDeserialization parameter that reads the file
	 */
	public SerializableObject deserializeData(DeserStrategy dStrategy) {
		SerializableObject retVar = null;
		retVar = dStrategy.processOutput(this);
		return retVar;
	}
	
	/**
	 * 
	 * @param filename: the name of the file to be opened
	 */
	public void openFile(String filename) {
		try {
			//Create a new file stream and create a new buffered reader
			file = new File(filename);
			FileReader fileReader = new FileReader(file);
			buffReader = new BufferedReader(fileReader);
		}catch(NullPointerException e) {
			//Error if the input filename is a null value
			System.err.println("Error: The named file is a null value");
			e.printStackTrace();
			System.err.println("Exiting the program.");
			System.exit(1);
		}catch(FileNotFoundException e) {
			//Error if the named file cannot be found
			System.err.println("Error: The named file cannot be found");
			e.printStackTrace();
			System.err.println("Exiting the program.");
			System.exit(1);
		}finally {}//Completing the try-catch-finally
		
		//Set the file name in case we need to write to the file
		fileName = filename;
	}
	/**
	 * 
	 * @param line = The line to be written to the given file saved in
	 *				 filename
	 */
	public void writeNextLine(String line) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true) ) ){
			
			//Take the line and write it to the file
			writer.write(line);
			writer.write("\n");
			writer.close();
		}catch(IOException e) {
			System.err.println("Error: There was an error when writing to the file.");
			e.printStackTrace();
			System.exit(1);
		}finally {}
	}
	
	/**
	 * @return The next line in the file you are reading
	 */
	public String readNextLine() {
		String retLine = null;
		try {
			retLine = buffReader.readLine();
		}catch(IOException e) {
			System.err.println("Error: Could not read the next line.");
			e.printStackTrace();
			System.exit(1);
		}finally{}
		
		return retLine;
	}
	
	/**
	 * Closes the file in the buffReader
	 */
	public void closeFile() {
		try {
			//Close the file
			buffReader.close();
		}catch(IOException e) {
			System.err.println("Error: Could not close the file");
			e.printStackTrace();
			System.exit(1);
		}finally {}
	}
	

}
