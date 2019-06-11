package airportSecurityState.airportStates;

import airportSecurityState.airportStates.Airport;
import airportSecurityState.airportStates.AirportStateI;
import airportSecurityState.util.MyLogger;

public class LowRisk implements AirportStateI {
	//The context class that will deal with the states
	public Airport airport;
	public int[] operations = {1,3,5,7,9};
	
	//The constructor that takes the context class
	public LowRisk(Airport airportIn) {
		airport = airportIn;
	}
	
	public void increaseOrDecreaseSecurity() {
		//You need to change state if either of these conditions are true
		
		//Go to the highest state if its applicable (HIGH_RISK)
		if(airport.getAvgTrafficPerDay() >= 8 || airport.getAvgProhibitedItemsPerDay() >= 4) {
			airport.changeSecurity(airport.HIGH_RISK);
			//Write to stdout if the proper logger setting is enabled
			String message = "Changed state from LOW_RISK to HIGH_RISK";
			MyLogger.writeMessage(message, MyLogger.DebugLevel.RAISE_STATE);
			
		//Moderate Risk increase
		}else if(airport.getAvgTrafficPerDay() >= 4 || airport.getAvgProhibitedItemsPerDay() >= 2) {
			airport.changeSecurity(airport.MODERATE_RISK);
			//Write to stdout if the proper logger setting is enabled
			String message = "Changed state from LOW_RISK to MODERATE_RISK";
			MyLogger.writeMessage(message, MyLogger.DebugLevel.RAISE_STATE);
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
