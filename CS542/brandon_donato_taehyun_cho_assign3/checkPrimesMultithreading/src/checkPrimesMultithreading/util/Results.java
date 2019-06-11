package checkPrimesMultithreading.util;
import checkPrimesMultithreading.util.MyLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import checkPrimesMultithreading.util.FileDisplayInterface;
import checkPrimesMultithreading.util.StdoutDisplayInterface;
import java.util.ArrayList;
import java.util.List;


public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	public String filename;
	public int resultSum;
	public List<Integer> primeContainer;
	public Results(){//Constructor
		String message = "Constructor for Class: " + this.getClass().getSimpleName();
		MyLogger.writeMessage(message, MyLogger.DebugLevel.CONSTRUCTOR);
		primeContainer = new ArrayList<Integer>();
	}
	// @Override
	public void printLineToStdout(String line) {
		//Print to stdout
		filename = "stdout";
		System.out.println(line);
	}
	// @Override
	public void printLineToFile(String line, String filename) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
			//Create the bufferedWriter and the FileWriter and pass
			//	"true" to append to the file so that it does not
			//	overwrite what was previously written
			this.filename = filename;
			//Do the actual writing
			writer.write(line);
			writer.write('\n');
			writer.close();
		}catch(IOException e) {
			System.out.println("There was an error with writing to the file.");
			e.printStackTrace();

		}finally{}
	}

	public synchronized void addPrime(int prime){
		primeContainer.add(prime);
	}
	public String printSumPrime(){ //Needs to be fixed
		int sumVal = 0;
		for(Integer val : primeContainer){ //System.out.println(val);
			sumVal += val;
		}
		return "The sum of all the prime numbers is: " + sumVal;
	}
	public String printValuesPrimeContainer(){
		String retVal = "Content in the Prime Container: [";
		for(Integer val : primeContainer) retVal += val + " ";
		retVal += "]";
		return retVal;
	}

	@Override
	public String toString() {
		return "Last wrote to: " + filename;
	}

	public String getFilename() {
		return filename;
	}
	public List<Integer> getPrimeContainer(){
		return primeContainer;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setPrimeContainer(List<Integer> primeContainerIn){
		primeContainer = primeContainerIn;
	}

	@Override
	public void writeSumToScreen() {
		// TODO Auto-generated method stub
		System.out.println("The sum of all the prime numbers is: " + resultSum);
	}


}
