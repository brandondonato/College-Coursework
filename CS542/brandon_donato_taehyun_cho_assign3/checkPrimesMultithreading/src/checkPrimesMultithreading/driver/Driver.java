package checkPrimesMultithreading.driver;

import checkPrimesMultithreading.util.Results;
import checkPrimesMultithreading.util.MyLogger;
import checkPrimesMultithreading.util.IsPrime;
import checkPrimesMultithreading.util.FileProcessor;
import checkPrimesMultithreading.workers.CreateWorkers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brandon Donato and Taehyun Cho
 *
 */
public class Driver {
	public static void main(String[] args) {
		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value 
		 * specified in build.xml. To avoid that, below condition is 
		 * used.
		 */
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.err.println("The files are as follows: args0 = input, args1 = delete, args2 = output1");
			System.exit(0);
		}
		String inputFile = args[0];
		int NUM_THREADS = Integer.parseInt(args[1]);
		int DEBUG_VALUE = Integer.parseInt(args[2]);
		//Check the correct values
		if(NUM_THREADS < 1 || NUM_THREADS > 5){ 
			System.err.println("Error: Invalid values of NUM_THREADS. Must be between 1-5 ");
			System.exit(0);
		}
		if(DEBUG_VALUE < 0 || DEBUG_VALUE > 4){
			System.err.println("Error: Invalid values of DEBUG_VALUE. Must be between 0-4 ");
			System.exit(0);
		}
		MyLogger.setDebugValue(DEBUG_VALUE); //Set Driver 

		Results results = new Results();
		FileProcessor fileProcessor = new FileProcessor(inputFile);
		IsPrime isPrime = new IsPrime();
		CreateWorkers createWorkers = new CreateWorkers(results, fileProcessor, isPrime, NUM_THREADS);
		createWorkers.startWorkers(NUM_THREADS);
		String retVal = results.printSumPrime();
		String contents = results.printValuesPrimeContainer();
		MyLogger.writeMessage(contents, MyLogger.DebugLevel.RESULTS_CONTENTS);
		System.out.println(retVal);

	}
}
