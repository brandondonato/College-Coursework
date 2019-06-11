package checkPrimesMultithreading.workers;

import checkPrimesMultithreading.util.FileProcessor;
import checkPrimesMultithreading.util.Results;
import checkPrimesMultithreading.util.IsPrime;
import checkPrimesMultithreading.util.MyLogger;



public class WorkerThread implements Runnable{

	public Results results;
	public FileProcessor inputFile;
	public IsPrime prime;

	public WorkerThread(FileProcessor fileIn, Results resultsIn, IsPrime primeIn) {
		String message = "Constructor for Class: " + this.getClass().getSimpleName();
		MyLogger.writeMessage(message, MyLogger.DebugLevel.CONSTRUCTOR);

		results = resultsIn;
		inputFile = fileIn;
		prime = primeIn;
	}

	@Override
	public void run() {
		String message = "Run Method in Thread called";
		MyLogger.writeMessage(message, MyLogger.DebugLevel.THREAD);
		String line;
		//Process line by line
		while((line = inputFile.readNextLine()) != null){ 
			int val = Integer.parseInt(line);
			// Get boolean value of prime
			boolean primeCheck = prime.isPrime(val); 
			if(primeCheck) {
				// If prime, add to results list container
				results.addPrime(val); 
				message = "Added the prime number: " + val;
				MyLogger.writeMessage(message, MyLogger.DebugLevel.RESULTS_ADDITION);
			}
		}
	}
}
