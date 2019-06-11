package assignment02;

public class ArrayStats{
	
	private double[] numberArray;
	private double average;
	private double maxElement;
	private double minElement;
	private int minIndex;
	private int maxIndex;

	public ArrayStats(double... vals){
		numberArray = vals;
		if(numberArray != null){
			maxElement = 0;
			minElement = 0;
			minIndex = 0;
			maxIndex = 0;
			double runningTotal = 0.0;
			for (int i = 0; i < numberArray.length; i++){
				if (numberArray[i] > maxElement){
					maxElement = numberArray[i];
					maxIndex = i;
				}
				if (numberArray[i] < minElement){
					minElement = numberArray[i];
					minIndex = i;
				}
				runningTotal += i;
				average = (runningTotal/(i+1));

			}

		}else{
			maxElement = 0;
			minElement = 0;
			average = 0;
			maxIndex = -1;
			minIndex = -1;
		}
	}

	public double getAvg(){
		return average;
	}
	public double getMax(){
		return maxElement;
	}
	public double getMin(){
		return minElement;
	}
	public int getMinIdx(){
		return minIndex;
	}
	public int getMaxIdx(){
		return maxIndex;
	}
	public double[] getData(){
		return numberArray;
	}
}

