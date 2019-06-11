package airportSecurityState.airportStates;

import airportSecurityState.airportStates.Airport;
import airportSecurityState.util.MyLogger;

public class ModerateRisk implements AirportStateI{
	//The context class that will deal with the states
	public Airport airport;
	public int[] operations = {2,3,5,8,9};
	
	
	//The constructor that takes the context class
	public ModerateRisk(Airport airportIn) {
		airport = airportIn;
	}
	
	public void increaseOrDecreaseSecurity() {
		//You need to change state if either of these conditions are true
		
		//Go to the highest state if its applicable (HIGH_RISK)
		if(airport.getAvgTrafficPerDay() >= 8 || airport.getAvgProhibitedItemsPerDay() >= 4) {
			airport.changeSecurity(airport.HIGH_RISK);
			//Write to stdout if the proper logger setting is enabled
			String message = "Changed state from MODERATE_RISK to HIGH_RISK";
			MyLogger.writeMessage(message, MyLogger.DebugLevel.RAISE_STATE);
			
		//Decrease to LOW_RISK only if both conditions are met
		}else if(airport.getAvgTrafficPerDay() < 4 && airport.getAvgProhibitedItemsPerDay() < 2) {
			airport.changeSecurity(airport.LOW_RISK);
			//Write to stdout if the proper logger setting is enabled
			String message = "Changed state from MODERATE_RISK to LOW_RISK";
			MyLogger.writeMessage(message, MyLogger.DebugLevel.LOWER_STATE);
		}
	}
	
	//Return the list of operations
	public String printOperations() {
		String retString = "";
		for(int i = 0; i < operations.length; i++) {
			retString = retString + operations[i] + " ";
		}
		return retString;
	}
}
