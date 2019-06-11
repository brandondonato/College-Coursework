package lab08;
import java.util.List;

public class BlackjackMethod extends ComputationMethod {

	public int compute(List<Card> cards){
		int retValue = 0;
		int numAces = 0;
		for(int i = 0; i < cards.size(); i++){
			if(cards.get(i).getValue() == 11){
				numAces += 1;
			}else{
				retValue += cards.get(i).getValue();
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
