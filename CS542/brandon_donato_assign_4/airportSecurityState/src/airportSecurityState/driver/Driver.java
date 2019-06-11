package airportSecurityState.driver;
import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.Results;
import airportSecurityState.airportStates.AirportContextI;
import airportSecurityState.airportStates.Airport;

import java.lang.NumberFormatException;
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

		/*
		 * Verify the input so that there is only one argument
		 */
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")  ) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.err.println("Please enter an input file, an output file, and a debug value.");
			System.err.println("The debug values are: 0 - Nothing, 1 - Rased States, "+
								"2 - Lowered States, 3 - Average Traffic, 4 - Average Items.");
			System.exit(0);
		}

		//Try and check the value of the debug value
		int debugValue = 0;
		try {
			debugValue = Integer.parseInt(args[2]);
		}catch(NumberFormatException e) {
			System.err.println("Error: The second argument could not be converted to an int.");
			e.printStackTrace();
			System.exit(1);
		}
		//Invalid Debug Value
		if(debugValue > 4 || debugValue < 0) {
			System.err.println("Error: Invalid debug value. The number must be between 0-4");
			System.exit(1);
		}
		//Set the debug value
		MyLogger.setDebugValue(debugValue);

		//Get the input and output files
		String inputFile = args[0];
		String outputFile = args[1];

		//Create the file processor from the file names
		FileProcessor inputProcessor = new FileProcessor(inputFile);

		//Create the new context class
		Airport airport = new Airport(inputProcessor, outputFile);
		airport.parseFile();
	}
}
