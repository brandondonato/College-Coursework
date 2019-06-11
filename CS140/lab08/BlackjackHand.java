package lab08;

public class BlackjackHand extends CardHand {

	public int value(){
		int retValue = 0;
		int numAces = 0;
		for(int i = 0; i < super.cards.size(); i++){
			if(super.cards.get(i).getValue() == 11){
				numAces += 1;
			}else{
				retValue += super.cards.get(i).getValue();
			}
		}
		if(retValue < 21){
			for(int j = numAces; j > 0; j--){
				if(retValue < 21){
					if((retValue + 11) > 21){
						retValue += 1;
					}else{
						retValue += 11;
					}
				}
				if(retValue > 21){
					retValue += 1;
				}
			}
		}
		if(retValue > 21){
			for(int a = numAces; a > 0; a--){
				retValue += 1;
			}
		}
		return retValue;
	}
	
	
}
