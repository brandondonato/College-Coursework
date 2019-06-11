package assignment07;

import java.util.Random;

public class Guess implements Play {
	private Random random = new Random();
	@Override
	public int takeTurn(int currentState) {
		int retValue = 0;
		if(currentState > 1){
			retValue = random.nextInt((currentState/2) + 1);
			if(retValue == 0){
				while(retValue == 0){
					retValue = random.nextInt((currentState/2) + 1);
				}
			}
		}else{
			retValue = 1;
		}
		return (retValue);
	}

}
