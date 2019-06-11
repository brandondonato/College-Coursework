package lab03;

public class Arrays{

	public static void main(String[] args){
		int[] nullArray = null;
		int[] emptyArray = {};
		int[] oddArray = {1,2,3};
		int[] evenArray = {1,2,3,4};

		System.out.println("Expected average: -1.0");
		System.out.println("Actual average: " + average(nullArray)+"\n");

		System.out.println("Expected average: -1.0");
		System.out.println("Actual average: " + average(emptyArray)+"\n");
		
		System.out.println("Expected average: 2.0");
		System.out.println("Actual average: " + average(oddArray)+"\n");

		System.out.println("Expected average: 2.5");
		System.out.println("Actual average: " + average(evenArray)+"\n");

		System.out.println("Expected average: -1.0");
		System.out.println("Actual average: " + averageOfRange(nullArray, 0, 2)+"\n");

		System.out.println("Expected average: -1.0");
		System.out.println("Actual average: " + averageOfRange(emptyArray, 0, 2)+"\n");
		
		System.out.println("Expected average: 2.0");
		System.out.println("Actual average: " + averageOfRange(oddArray, 0, 3)+"\n");

		System.out.println("Expected average: 2.5");
		System.out.println("Actual average: " + averageOfRange(evenArray, 0, 4)+"\n");

		try{
			//averageOfRange(evenArray, -1, 2);
			averageOfRange(oddArray, 0, 7);
		}catch(ArrayIndexOutOfBoundsException e ){
			System.out.println("Sorry, the bounds you have put are invalid.");
		}
	}

	/**
	* Computes the average of the elements of the array
	* @param the array whose elements are to be averaged
	* @return the average of the elements in the array, or -1 if the array is null or empty
	*/
	public static double average(int[] array) {
    	double arrayTotal = 0.0;
    	double arrayLength = 0.0;
    	double arrayAverage = -1.0; 
    	
    	if (array != null){
    		for (int number : array){
    			arrayTotal = arrayTotal + number;
    			arrayLength += 1;
    		}
    	}
    	
    	if (arrayTotal != 0.0){
    		arrayAverage = (arrayTotal/arrayLength);
    	}
    	//System.out.println(arrayAverage);
    	return arrayAverage;
	}

	/**
	* Computes the average of the elements of the array within the given range
	* @param array the array whose elements to average
	* @param lowerBound the index of the first element to include in the computation
	* @param upperBound one more than the index of the last element to include in the 
			computation
	* @return the average of the elements in array from indices lowerBound to
			 upperBound, excluding the upperBound, or -1 if the array is null, empty, 
			 or lowerBound >= upperBound
	*/
	public static double averageOfRange(int[] array, int lowerBound, int upperBound) {
    	double arraySum = 0.0;
    	double totalNumbers = 0.0;
    	double arrayLength = 0.0;
    	double arrayAverage = -1.0;
    	
    	if (array != null){
    		for (int elements : array);
    			arrayLength += 1;
    	}

    	if (array == null || array.length == 0 || lowerBound >= upperBound){
    		arrayAverage = -1.0;
    	}else{
    		for (int i = lowerBound; i < upperBound; i++){
    		arraySum = arraySum + array[i];
    		totalNumbers += 1;
    		}
    	}

    	if (arraySum > 0.0){
    		arrayAverage = (arraySum/totalNumbers);
    	}
    	return arrayAverage;    	
	}

}