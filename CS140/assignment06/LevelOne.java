package assignment06;

public class LevelOne extends LevelTwo{

	private int numOfAccounts;
	
	public LevelOne(double[] balances){
		super(balances);
		numOfAccounts = balances.length;
	}
	
	@Override
	public double measure(){
		double maxDiff = 0;
		for(int i = 0; i < numOfAccounts; i++){
			if(maxDiff < super.distance(i, super.measure())){
				maxDiff = super.distance(i, super.measure());
			}
		}
		return maxDiff;
	}
}
