package checkPrimesMultithreading.workers;

import checkPrimesMultithreading.util.Results;
import checkPrimesMultithreading.util.FileProcessor;
import checkPrimesMultithreading.util.IsPrime;
import checkPrimesMultithreading.util.MyLogger;
import checkPrimesMultithreading.workers.ThreadPool;

import java.lang.IllegalThreadStateException;
import java.lang.InterruptedException;

import java.util.ArrayList;

public class CreateWorkers{
	//The variables needed for later when printing the values to stdout
	public Results results;
	public FileProcessor inputFile;
	public IsPrime prime;
	//This is the thread pool object
	public ThreadPool pool;

	/**
	 * This is the constructor for the object which takes these values
	 * and stores them within the Object for later use
	 */
	public CreateWorkers(Results resultsIn, FileProcessor fileIn, IsPrime primeIn, int numThreadsIn) {
		String message = "Constructor for Class: " + this.getClass().getSimpleName();
		MyLogger.writeMessage(message, MyLogger.DebugLevel.CONSTRUCTOR);

		results = resultsIn;
		inputFile = fileIn;
		prime = primeIn;
		//Create our new thread pool
		pool = new ThreadPool();

		//Create 5 worker threads and add them to the object pool
		for(int i = 0; i < numThreadsIn; i++) {
			WorkerThread newThread = new WorkerThread(inputFile, results, prime);
			pool.addWorkerThreadObject(newThread);
		}
	}

	/**
	 *
	 * @param numThreadsIn: This tells CreateWorkers how many threads
	 * 						it needs to create
	 */
	public void startWorkers(int numThreadsIn) {
		//The list of threads we will need to join back
		ArrayList<Thread> threads = new ArrayList<Thread>();
		//Create a new thread for each pass and have it begin the run
		//	method
		for(int i = 0; i < numThreadsIn; i++) {
			//If the object pool is not empty, then take a thread
			WorkerThread worker = pool.borrowObject();
			//Add the working threads to the list and start them
			if(worker!=null){
				Thread thread = new Thread(worker);
				threads.add(thread);
				//Begin executing each thread
				try {
					thread.start();
				}catch(IllegalThreadStateException e) {
					System.err.println("Error: Thread is not in the proper state.");
					e.printStackTrace();
					System.exit(1);
				}finally {}
			}
		}
		//Go through all of the threads and join them back
		for(Thread worker : threads) {
			try {
				worker.join();
			}catch(InterruptedException e) {
				System.err.println("Error: The thread has an interruption error.");
				e.printStackTrace();
				System.exit(1);
			}finally {}
		}
	}
}
