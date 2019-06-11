package airportSecurityState.airportStates;

import airportSecurityState.airportStates.AirportStateI;

public interface AirportContextI{
	//Calls to the class to change the security
	public void changeSecurity(AirportStateI state);
	
	//Calculating the average numbers
	public float calculateAvgTraffic(float numPeople, float days);
	public float calculateAvgPI(float prohibItems, float days);
	
	//Getters and setters for the traffic and prohibited items
	public float getAvgTrafficPerDay();
	public void setAvgTrafficPerDay(float averageIn);
	public float getAvgProhibitedItemsPerDay();
	public void setAvgProhibitedItemsPerDay(float averageIn);
	
	//The function that completes the actual file parsing
	public void parseFile();
}