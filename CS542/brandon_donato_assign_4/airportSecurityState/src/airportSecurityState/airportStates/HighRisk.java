package airportSecurityState.airportStates;

import airportSecurityState.airportStates.Airport;
import airportSecurityState.util.MyLogger;

public class HighRisk implements AirportStateI{
	//This is the airport the class will use as a reference
	public Airport airport;
	public int[] operations = {2,4,6,8,10};
	
	//Constructor takes the Airport class
	public HighRisk(Airport airportIn) {
		airport = airportIn;
	}
	
	//Add the implementation for the single class method
	public void increaseOrDecreaseSecurity() {
		//You need to change state if either of these conditions are true
		
		//Go to the lowest state if its applicable (LOW_RISK)
		if(airport.getAvgTrafficPerDay() < 4 && airport.getAvgProhibitedItemsPerDay() < 2) {
			airport.changeSecurity(airport.LOW_RISK);
			//Write to stdout if the proper logger setting is enabled
			String message = "Changed state from HIGH_RISK to LOW_RISK";
			MyLogger.writeMessage(message, MyLogger.DebugLevel.LOWER_STATE);
			
		//Decrease to MODERATE_RISK only if both conditions are met
		}else if(airport.getAvgTrafficPerDay() < 8 && airport.getAvgProhibitedItemsPerDay() < 4) {
			airport.changeSecurity(airport.MODERATE_RISK);
			//Write to stdout if the proper logger setting is enabled
			String message = "Changed state from HIGH_RISK to MODERATE_RISK";
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
