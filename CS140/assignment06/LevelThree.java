package assignment06;
import java.lang.Math;

public class LevelThree extends LevelFour{

	private BankAccount[] accountArray;
	
	public LevelThree(double[] balances){
		super(balances.length);
		accountArray = new BankAccount[balances.length];
		for(int i = 0; i < balances.length; i++){
			accountArray[i] = new BankAccount(balances[i]);
		}
	}
	
	@Override
	public double measure(){
		double retValue = 0;
		for(int i = 0; i < accountArray.length; i++){
			retValue += accountArray[i].getBalance();
		}
		return retValue;
	}
	
	public double distance(int i, double mean){
		double retValue;
		retValue = (accountArray[i].getBalance() - mean);
		return Math.abs(retValue);
	}
	
	
	
	
	
}
