package checkPrimesMultithreading.workers;

import java.util.ArrayList;
import checkPrimesMultithreading.util.MyLogger;
import checkPrimesMultithreading.workers.WorkerThread;

public class ThreadPool {
	//This is the object pool where the thread objects are stored
	private ArrayList<WorkerThread> objectPool;

	/**
	 * Here, we initialize the ArrayList of threads
	 */
	public ThreadPool() {
		String message = "Constructor for Class: " + this.getClass().getSimpleName();
		MyLogger.writeMessage(message, MyLogger.DebugLevel.CONSTRUCTOR);
		//Initialize the thread pool
		objectPool = new ArrayList<WorkerThread>();
	}

	/**
	 *
	 * @return A worker thread if the object pool is not empty,
	 * 			or null if the pool is empty
	 */
	public WorkerThread borrowObject() {
		if( !objectPool.isEmpty() ) {
			//Get the thread from the thread pool
			WorkerThread retThread = objectPool.get(0);
			//Take the thread out of the thread pool
			objectPool.remove(objectPool.get(0));
			//Return the thread taken from the thread pool
			return retThread;
		}else {
			return null;
		}
	}

	/**
	 *
	 * @param workerIn: The worker thread to be added to the object
	 * 					pool
	 */
	public void addWorkerThreadObject(WorkerThread workerIn) {
		objectPool.add(workerIn);
	}

	/**
	 *
	 * @param workerIn: Effectively the same as adding workers, but the
	 * 					workers should be returned when they have been
	 * 					used
	 */
	public void returnWorkerThreadObject(WorkerThread workerIn) {
		objectPool.add(workerIn);
	}
}
