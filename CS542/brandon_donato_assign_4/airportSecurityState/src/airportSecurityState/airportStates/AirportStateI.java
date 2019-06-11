package airportSecurityState.airportStates;

/**
 * 
 * @author Brandon
 *
 */
public interface AirportStateI{
	//These are the functions needed to swap between states
	public void increaseOrDecreaseSecurity();
	public String printOperations();
	
}