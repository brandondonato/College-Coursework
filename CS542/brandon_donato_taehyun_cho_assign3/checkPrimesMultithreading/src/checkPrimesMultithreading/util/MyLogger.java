package checkPrimesMultithreading.util;



public class MyLogger{

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {NONE, RESULTS_CONTENTS,RESULTS_ADDITION,THREAD,CONSTRUCTOR};
    private static DebugLevel debugLevel;

    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
    	switch (levelIn) {
    	case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
    	case 3: debugLevel = DebugLevel.THREAD; break;
    	case 2: debugLevel = DebugLevel.RESULTS_ADDITION; break;
    	case 1: debugLevel = DebugLevel.RESULTS_CONTENTS; break;
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
