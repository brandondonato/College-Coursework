package assignment04;
import java.util.ArrayList;

public class VariousMethods{

	public static int oddEvenCount(long[] arr){
		int oddCount = -1;
		if(arr != null){
			if(arr.length > 1){
				oddCount = 0;
				for(int i = 1; i < arr.length; i += 2){	
						if((arr[i] % 2 ==0) && (arr[i] != 0)){
							oddCount += 1;
						}					
				}
			}else{
				oddCount = 0;
			}		
		}
		return oddCount;
	}


	public static int minCount(double min, ArrayList<BankAccount> list){
		int minNum = -1;
		if(list != null){
			minNum = 0;
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) != null){
					if((list.get(i).getBalance()) > min){
					minNum += 1;
					} 
				}
			}
		}
		return minNum;
	}

	public static double justAnExercise(double cap, char initial, BankAccount[] arr){
		BankAccount retAccount = new BankAccount(0);
		double retValue = -1.0;
		ArrayList<BankAccount> comparedList = new ArrayList<>();
		if(arr != null) {
			for(int i = 0; i < arr.length; i++){
				if(arr[i] != null){
					if((i % 3 == 0) && arr[i].getBalance() < cap){
						comparedList.add(arr[i]);
					}
					if((i % 3 == 1) && (arr[i].getId()) % 2 == 0){
						comparedList.add(arr[i]);
					}
					if((i % 3 == 2) && 
			(arr[i].getCustomer().getName().toUpperCase().indexOf(Character.toUpperCase(initial)) == 0)){
						comparedList.add(arr[i]);
					}
				}
			}
			if(comparedList.size() > 0){
				retAccount = comparedList.get(0);
				for(int i = 1; i < comparedList.size(); i++){
					if(comparedList.get(i).getBalance() > retAccount.getBalance()){
						retAccount = comparedList.get(i);
					}
				}
				retValue = retAccount.getBalance();
			}
		}
		return retValue;  
	}

	public static int anotherExercise(ArrayList<BankAccount> list){
		int retIndex = -1;
		
		if(list != null) {
			int nameLength = 0;
			for(int i = 1; i < list.size(); i++){
				if(list.get(i) != null){
					if(list.get(i).getCustomer().getName().length() >= nameLength){
						retIndex = i;
						nameLength = list.get(i).getCustomer().getName().length();
					}	
				}
			}
		}
		return retIndex;
	}


}