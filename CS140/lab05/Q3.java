package lab05;

public class Q3 {
    public int countMax(int[] array) {
        // First compute the maximum integer inside of `array`.
        // Then, in another loop, calculate the count of how many
        // times that value occurs in the array. Return this count
        // Assume that `array`
        // is not null but return 0 if it is empty.

        int maxInt = 0;
        int occurrences = 0;

        if(array.length > 0){
        	maxInt = array[0];
        	for(int i = 1; i < array.length; i++){
        		if(array[i] > maxInt){
        			maxInt = array[i];
        		}
        	}
        }

        for(int i = 0; i < array.length; i++){
        	if(maxInt == array[i]){
        		occurrences += 1;
        	}
        }

        return occurrences;
    }
}