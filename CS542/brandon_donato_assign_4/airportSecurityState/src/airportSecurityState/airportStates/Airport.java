package airportSecurityState.airportStates;
//state imports
import airportSecurityState.airportStates.HighRisk;
import airportSecurityState.airportStates.ModerateRisk;
import airportSecurityState.airportStates.LowRisk;
import airportSecurityState.airportStates.AirportStateI;

import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.Results;

import java.lang.NumberFormatException;

public class Airport implements AirportContextI {
	//These are the state declarations
	public AirportStateI HIGH_RISK, MODERATE_RISK, LOW_RISK;
	private AirportStateI currentState;
	
	//The list of banned items
	public final static String[] BANNED_ITEMS = {"Grains", "NailCutters",
			"Plants", "EndangeredAnimals"};
	
	//The factors used to increase/decrease values
	public float avgTrafficPerDay = 0;
	public float avgProhibitedItemsPerDay = 0;
	
	//The FileProcessor
	public FileProcessor processorIn;
	
	//The output file name for the results instance declared later
	public String outputFile;
	
	public Airport(FileProcessor fileIn, String fileOut ) {
		//Initialize the new states
		HIGH_RISK = new HighRisk(this);
		MODERATE_RISK = new ModerateRisk(this);
		LOW_RISK = new LowRisk(this);
		
		//Set the current state to LOW_RISK by default
		currentState = LOW_RISK;
		
		//Set the FileProcessors
		processorIn = fileIn;
		
		//Set the outputFile name
		outputFile = fileOut;
	}
	
	//This function parses through the input file and then proceeds to
	//	change the state if necessary. It then prints the results out
	//	to another .txt file.
	public void parseFile() {
		//Temp variables to store number of total passengers and items
		float numTravelers = 0;
		float numProhibItems = 0;
		float days = 0;
		String line;
		
		//Process the file line by line
		while( (line = processorIn.readNextLine()) != null ) {
			//Line separates into 4 parts: part 1 == day, part 3 == item
			String[] lineParts = line.split("[;:]");
			
			//Verify proper input
			if(lineParts.length != 4) {
				System.err.println("Error: Lines are not in proper format.");
				System.exit(1);
			}
			
			try {
				//Set the proper number of days
				int newDay = Integer.parseInt(lineParts[1]);
				
				//Verifying the days always increment
				if(newDay < days) {
					System.err.println("Error: Lines are not in proper format.");
					System.exit(1);
				}else {
					days = newDay;
				}
			}catch(NumberFormatException e) {
				System.err.println("Error: Could not parse integer properly");
				e.printStackTrace();
				System.exit(1);
			}
			//Increment the number of travelers
			numTravelers++;
			
			//Check for the banned items
			for(int i = 0; i < BANNED_ITEMS.length; i++) {
				if(lineParts[3].equals(BANNED_ITEMS[i])) {
					numProhibItems++;
				}
			}
			
			//Calculate the averages
			avgTrafficPerDay = calculateAvgTraffic(numTravelers, days);
			avgProhibitedItemsPerDay = calculateAvgPI(numProhibItems, days);
			
			//Here are the logger outputs for both the avgTraffic and
			//	avgProhibItems
			String trafficMessage = "The average traffic per day is currently: ";
			trafficMessage += avgTrafficPerDay;
			MyLogger.writeMessage(trafficMessage, MyLogger.DebugLevel.AVERAGE_TRAFFIC);
			String prohibItemMessage = "The average prohibited items per day is currently: ";
			prohibItemMessage += avgProhibitedItemsPerDay;
			MyLogger.writeMessage(prohibItemMessage, MyLogger.DebugLevel.AVERAGE_ITEMS);
			
			//Determine if we need to change state
			currentState.increaseOrDecreaseSecurity();
			
			//Write the current state operations to file
			String currentOperations = currentState.printOperations();
			Results resultsObj = new Results();
			resultsObj.printLineToFile(currentOperations, outputFile);
			
			/*
			System.out.println(line);
			System.out.println("Days, number of travelers, number of items: " + 
									days+ " " + numTravelers + " " + numProhibItems);
			System.out.println("AverageTrafficPerDay, AverageProhibitedItemsPerDay " +
								avgTrafficPerDay + ", " + avgProhibitedItemsPerDay);
			String newline = currentState.printOperations();
			System.out.println(newline);
			System.out.println(" ");
			*/
			
		}
	}

	@Override
	//This method will call the change state function within each
	//	class. It should be called at the end of editing the 
	//	different lines.
	public void changeSecurity(AirportStateI state) {
		currentState = state;
	}
	
	@Override
	//Calculate the average traffic per day
	public float calculateAvgTraffic(float numPeople, float days) {
		return (numPeople/days);
	}

	@Override
	//Calculate the average prohibited items per day
	public float calculateAvgPI(float prohibItems, float days) {
		return (prohibItems/days);
	}

	@Override
	//Get the current average traffic
	public float getAvgTrafficPerDay() {
		return avgTrafficPerDay;
	}

	@Override
	//Set the current average traffic
	public void setAvgTrafficPerDay(float averageIn) {
		avgTrafficPerDay = averageIn;
	}

	@Override
	//Get the current average prohibited items
	public float getAvgProhibitedItemsPerDay() {
		return avgProhibitedItemsPerDay;
	}

	@Override
	//Set the current average prohibited items 
	public void setAvgProhibitedItemsPerDay(float averageIn) {
		avgProhibitedItemsPerDay = averageIn;	
	}
	
}
