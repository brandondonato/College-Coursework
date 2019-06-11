package assignment06;

public class LevelTwo extends LevelThree {

	private int numOfAccounts;
	public LevelTwo(double[] balances){
		super(balances);
		numOfAccounts = balances.length;
		
	}
	
	@Override
	public double measure(){
		double totalSum = super.measure();
		return(totalSum/numOfAccounts);
	}
	
}
