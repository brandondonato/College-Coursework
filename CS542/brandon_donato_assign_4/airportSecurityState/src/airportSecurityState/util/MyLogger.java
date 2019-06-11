package  airportSecurityState.util;

public class MyLogger{

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {NONE, RAISE_STATE,LOWER_STATE,
    								AVERAGE_TRAFFIC,AVERAGE_ITEMS};
    private static DebugLevel debugLevel;

    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
    	switch (levelIn) {
    	case 4: debugLevel = DebugLevel.AVERAGE_ITEMS; break;
    	case 3: debugLevel = DebugLevel.AVERAGE_TRAFFIC; break;
    	case 2: debugLevel = DebugLevel.LOWER_STATE; break;
    	case 1: debugLevel = DebugLevel.RAISE_STATE; break;
    	default: debugLevel = DebugLevel.NONE; break;
    	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
    	debugLevel = levelIn;
    }

    public static void writeMessage (String message, DebugLevel levelIn ) {
	     if (levelIn == debugLevel) System.out.println(message);
    }

    public String toString() {
    	return "The debug level has been set to the following " + debugLevel;
    }
}
