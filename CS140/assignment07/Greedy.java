package assignment07;

public class Greedy implements Play {

	@Override
	public int takeTurn(int currentState) {
		int retValue = 0;
		if(currentState > 1){
			retValue = currentState/2;
		}else{
			retValue = 1;
		}
		return retValue;
	}

}
