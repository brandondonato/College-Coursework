/**
* 	a text file and parses the input line-by-line.
* @author Brandon Donato
*/

package coursesRegistration.util;

//Using this exception incase the given file does not exist
import java.lang.NullPointerException;
import java.io.FileNotFoundException;
import java.io.IOException;
//Using the following imports to parse through the input file
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


public class FileProcessor {
	/* Creating the instance variables*/
	private File file;
	private BufferedReader buffReader;

	/**
	*This is the constructor
	*/
	public FileProcessor(String filename){
		try{
			//Create a new file stream from the given filename
			file = new File(filename);
			//Create a FileReader from the new file
			FileReader fileReader = new FileReader(file);
			//Create the BufferedReader from the fileReader
			buffReader = new BufferedReader(fileReader);
		}catch(NullPointerException e){
			System.err.println("Error: The file you specified cannot" +
			" be found.");
			//Print the stack trace to stderr
			e.printStackTrace();
			//Exit with 0
			System.exit(0);
		}catch(FileNotFoundException e){
			System.err.println("Error: The file you specified cannot" +
			" be found.");
			//Print the stack trace to stderr
			e.printStackTrace();
			//Exit the Program
			System.exit(0);
		}finally{} //Completing the try-catch-finally
	}

	/**
	*@return The next line you are trying to read
	*/
	public String readNextLine(){
		//Create the return variable
		String retLine = null;
		try{
			retLine = buffReader.readLine();
		}catch(IOException e){
			//Print an error message, stack trace and exit
			System.err.println("Error: Could not read the next line.");
			e.printStackTrace();
			System.exit(0);
		}finally{}

		return retLine;
	}

	public void closeFile(){
		try{
			//Close the file
			buffReader.close();
		}catch(IOException e){
			//Print an error message, stack trace and exit
			System.err.println("Error: Could not read the next line.");
			e.printStackTrace();
			System.exit(0);
		}finally{}
	}
	/*
	*******************************************************************
	* Getters, Setters and toString methods
	*/

	/**
	*@return File name from the file being read from
	*/
	public String getFile(){
		return file.getName();
	}

	/**
	* Tries to set a new file based on the filename
	*/
	public void setFile(String filename){
		try{
			file = new File(filename);
		}catch(NullPointerException e){
			//Print an error message, stack trace and exit
			System.err.println("Error: Could not read the next line.");
			e.printStackTrace();
			System.exit(0);
		}finally{}
	}

	@Override
	public String toString(){
		return "File name: " + file.getName();
	}
}
