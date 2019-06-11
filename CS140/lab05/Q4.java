package lab05;

public class Q4 {
    public int[] reverse(int[] array) {
        // return a new array with
        // the elements of `array` reversed.
        // Assume `array` is not null.

    	int[] newArray = new int[array.length];
    	int forwardCount = 0;

        for(int i = (array.length - 1); i >= 0; i--){
        	newArray[forwardCount] = array[i];
        	forwardCount += 1;
        }

        return newArray;
    }
}