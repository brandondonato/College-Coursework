package genericCheckpointing.xmlStoreRestore;
import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class XMLSerialization implements SerStrategy {
	
	public void processInput(SerializableObject sObject, StoreRestoreHandler writer) {
		//Get the class type and then get the fields for
		//	the class and store them
		Class<?> cls = sObject.getClass();
		//System.out.println(cls);
		Field[] fieldList = cls.getDeclaredFields();
		
		//Write the identifier line to the file
		String startLine = "<DPSerialization>";
		writer.writeNextLine(startLine);
		
		//Write the name of the class type to the file
		String complexStart = " <complexType xsi:type=\"";
		String className = cls.getName();
		complexStart += className + "\">";
		writer.writeNextLine(complexStart);
		
		//Iterate through all of the fields and serialize them based on the type
		for(int i = 0; i< fieldList.length; i++) {
			Field field = fieldList[i];
			
			//Customize the return line for each field depending upon 
			//	the type of the field
			//System.out.println("name= " + field.getName() );
			//System.out.println("type= " + field.getType() );
/************************* For type int ****************************/
			if(field.getType() == int.class) {
				
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "int";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file only if the
				//	value is equal to, or greater than 10
				if( (int)invokeRet >= 10) {
					writer.writeNextLine(writeString);
				}
				
/************************* For type long ****************************/
			}else if(field.getType() == long.class) {
				
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "long";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file only if the
				//	value is equal to, or greater than 10
				if( (long)invokeRet >= 10) {
					writer.writeNextLine(writeString);
				}
/************************* For type String **************************/
			}else if(field.getType() == String.class) {
				
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "string";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file
				writer.writeNextLine(writeString);
/************************* For type boolean *************************/				
			}else if(field.getType() == boolean.class) {
	
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "is"+fieldName;
				String type = "boolean";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file
				writer.writeNextLine(writeString);
/************************* For type double **************************/
			}else if(field.getType() == double.class) {
				
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "double";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file only if the
				//	value is equal to, or greater than 10
				if( (double)invokeRet >= 10) {
					writer.writeNextLine(writeString);
				}
/************************* For type float **************************/
			}else if(field.getType() == float.class) {
			
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "float";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file
				writer.writeNextLine(writeString);
/************************* For type short **************************/
			}else if(field.getType() == short.class) {
				
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "short";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file
				writer.writeNextLine(writeString);
/************************* For type char **************************/
			}else if(field.getType() == char.class) {
				
				//Get the information to be written to the file: 
				//	fieldName, fieldType and fieldValue
				String fieldName = field.getName();
				String methodName = "get"+fieldName;
				String type = "char";
				
				//Initialize the getterMethod and parameters for the 
				//	function call to receive the value
				Method getterMethod = null;
				Object[] params = new Object[0];
				try {
					//No second parameter because the function does not
					//	have any parameters
					getterMethod = cls.getMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					System.err.println("Error: The method could not be found.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Invoke the getter method to grab the value of the field
				Object invokeRet = null;
				try {
					invokeRet = getterMethod.invoke(sObject, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Error: The method could not properly called.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
				
				//Now that we have the value, type and name, put them
				//	into a string to write to the file
				String writeString = "  <";
				writeString += fieldName + " xsi:type=\"xsd:";
				writeString += type +"\">";
				writeString += invokeRet + "</";
				writeString += fieldName + ">";
				//System.out.println(writeString + "\n");
				//System.out.println("Return value: " + invokeRet);
				
				//Take the string and write it to the file
				writer.writeNextLine(writeString);
			}
			
		} //End iterating through fields
		
		//End the complex type with its end tag
		String endComplex = " </complexType>";
		writer.writeNextLine(endComplex);
		
		//End the DPSerialization with its tag
		String endDP = "</DPSerialization>";
		writer.writeNextLine(endDP);	
	}
}
