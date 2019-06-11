package coursesRegistration.driver;
import coursesRegistration.util.FileProcessor;
import coursesRegistration.scheduler.Scheduler;

/**
 * @author Brandon Donato
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}") ) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
			System.exit(0);
		}
		//System.out.println("Hello World! Lets get started with the assignment");
		
		String coursePrefs = args[0]; //First argument = Students
		//System.out.println(coursePrefs);
		String courseInfo = args[1]; //Second argument = Courses
		//System.out.println(courseInfo);
		
		FileProcessor prefFile = new FileProcessor(coursePrefs);
		FileProcessor infoFile = new FileProcessor(courseInfo);
		
		Scheduler scheduler = new Scheduler(prefFile, infoFile);
		scheduler.scheduleClasses();
	}
}
