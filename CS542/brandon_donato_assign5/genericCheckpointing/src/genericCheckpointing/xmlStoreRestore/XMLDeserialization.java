package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import genericCheckpointing.util.SerializableObject;

public class XMLDeserialization implements DeserStrategy {

	@Override
	//Reads through the given file and deserializes the information
	public SerializableObject processOutput(StoreRestoreHandler reader) {
		//Create the vector that will store all of the objects after 
		//	running through the file
		Vector<Object> objVec = new Vector<Object>();
		SerializableObject retObj = null;
		
		//Process the file line-by-line, performing while loops whenever a 
		//	new tag is reached
		String currentLine = reader.readNextLine();
		//while(currentLine != null) {
			//System.out.println(currentLine);
			if(currentLine.equals("<DPSerialization>") ) {
				
				//Now begin while loop for as long as there are parts of
				//	the serialization left
				while(!currentLine.equals("</DPSerialization>") ) {
					
					//Get the current line that holds the current type of
					//	MATF or MATS. The name of the class can be found 
					//	at index 3 when split in this way.
					//If you wanted to use reflection for a different 
					//	object, change the className string to contain
					//	the path to where your class is
					currentLine = reader.readNextLine();
					String typeLine = currentLine;
					String className = "genericCheckpointing.util.";
					
					if(typeLine.startsWith(" <complexType") ) {
						
						//Parse the object and determine what the class
						//	is for each object. Create a new instance 
						//	and initialize all fields.
						String[] splitLine = typeLine.split("[.\"]");
						className += splitLine[3];
						Class<?> cls = null;
						//System.out.println(className);
						try {
							cls = Class.forName(className);
						} catch (ClassNotFoundException e) {
							System.err.println("Error: The class cannot be found.");
							e.printStackTrace();
							System.exit(1);
						}finally {}
						
						//Create the new instance of the object using
						//	reflection
						Object obj = null;
						try {
							obj = cls.newInstance();
						} catch (InstantiationException | IllegalAccessException e1) {
							System.err.println("Error: Could not make newInstance call");
							e1.printStackTrace();
							System.exit(1);
						}finally {}
						
						//Begin the while loop for each individual object
						//	to initialize all fields
						currentLine = reader.readNextLine();
						while(!currentLine.equals(" </complexType>") ) {
							
							//Split the line to get the field type (index 7),
							//	field name (index 3) from typeNames. Use
							//	strValue to get the value of the object 
							//	whether it's a string or not (index 2)
							//System.out.println("The current line:"+currentLine);
							String[] typeNames = currentLine.split("[ <>:\"]");
							String[] strValue = currentLine.split("[<>]");
							/*
							int count = 0;
							for(String i : stringArr) {
								System.out.print(i);
								System.out.println(" at count: " + count);
								count++;
							}
							
							
							
							int count1 = 0;
							for(String i : modifyArray) {
								System.out.print(i);
								System.out.println(" at count: " + count1);
								count1++;
							}
							*/
							
							//Set the information parsed from the file
							String fieldName = typeNames[3];
							String type = typeNames[7];
							String value = strValue[2];
							String methodName = "set" + fieldName;
							/*
							System.out.println(fieldName + ", " + type + ", " + value);
							System.out.println(methodName);
							*/
							
							//Depending on the type, find the proper method 
							//	that will set the value of our field
							Method setMethod = null;
							Object[] params = null;
							
							if(type.equals("int") ) {
								//System.out.println("The type is int");
								try {
									setMethod = cls.getMethod(methodName, int.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									Integer valueI = Integer.parseInt(value);
									params[0] = valueI;
								}catch(NoSuchMethodException | NumberFormatException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("string") ) {
								//System.out.println("The type is string");
								try {
									setMethod = cls.getMethod(methodName, String.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									params[0] = value;
								}catch(NoSuchMethodException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("long") ) {
								//System.out.println("The type is long");
								try {
									setMethod = cls.getMethod(methodName, long.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									Long valueL = Long.parseLong(value);
									//System.out.println("Long value: " +value);
									params[0] = valueL;
								}catch(NoSuchMethodException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("boolean") ) {
								//System.out.println("The type is boolean");
								try {
									setMethod = cls.getMethod(methodName, boolean.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									Boolean valueB = Boolean.parseBoolean(value);
									params[0] = valueB;
								}catch(NoSuchMethodException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("double") ) {
								//System.out.println("The type is double");
								try {
									setMethod = cls.getMethod(methodName, double.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									Double valueD = Double.parseDouble(value);
									params[0] = valueD;
								}catch(NoSuchMethodException | NumberFormatException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("float") ) {
								//System.out.println("The type is float");
								try {
									setMethod = cls.getMethod(methodName, float.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									Float valueF = Float.parseFloat(value);
									params[0] = valueF;
								}catch(NoSuchMethodException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("short") ) {
								//System.out.println("The type is short");
								try {
									setMethod = cls.getMethod(methodName, short.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									Short valueS = Short.parseShort(value);
									params[0] = valueS;
								}catch(NoSuchMethodException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}else if(type.equals("char") ) {
								//System.out.println("The type is char");
								try {
									setMethod = cls.getMethod(methodName, char.class);
									//Set the parameters and the values for the method call
									params = new Object[1];
									char valueC;
									if( !value.equals("") ) {
										valueC = value.charAt(0);
									}else {
										valueC = Character.MIN_VALUE;
									}
									params[0] = valueC;
								}catch(NoSuchMethodException e) {
									System.err.println("Error: Could find the proper method");
									e.printStackTrace();
									System.exit(1);
								}finally {}
							}
							
							//Call the method and give the object as a value
							//	for the function call
							Object result = null;
							try {
								result = setMethod.invoke(obj, params);
								//System.out.println("Result: " + result);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								System.err.println("Error: Could not properly set the method.");
								e.printStackTrace();
								System.exit(1);
							}finally {}
							
							
							//Advance to the next object in the file
							currentLine = reader.readNextLine();
						}//End complexType while loop (setting the fields of each object
						
						//Now set the Class object to an object with value
						//	and then add the Serializable Object to the 
						//	vector
						//objVec.add(obj);
						retObj = (SerializableObject)obj;
					}//End outer complexType loop (initializing each object)
					
				}//End while for DPSerialization (finding next object)
				
			}// End if there exists another object to serialize
			
		//Return the object created from the file
		return retObj;
		
	}//End function

}
