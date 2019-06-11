package lab03;

public class Car{
	
	private int mileage;
	private String model;

	public Car(int totalMiles, String modelCar){
		mileage = totalMiles;
		model = modelCar;
	}

	public boolean shouldSell(){
	    boolean answer = true;
		if (mileage < 100000){
			answer = false;
		}
		return answer;
	}
	
	public int getMileage(){
		return mileage;
	}

	public String getModel(){
		return model;
	}

	public static void main(String[] args){		
		Car[] carList = {new Car(120000, "Toyota"), new Car(10000, "Honda"), 
						new Car(15000, "BMW"), new Car(101000, "Volkswagen")};
		int soldTotal = 0;
		for(Car a : carList){
			if (a.shouldSell()){
				soldTotal += 1;
			}
		}
		System.out.println("Number of cars to be sold: " + soldTotal);
		System.out.println("Expected number of cars sold: 2");
	}

}
