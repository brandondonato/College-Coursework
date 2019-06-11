package lab08;

import java.util.ArrayList;

public class ComposedHand {

	ComputationMethod method;
	ArrayList<Card> cards = new ArrayList<Card>();
	
	public ComposedHand(ComputationMethod compMeth){
		method = compMeth;
	}
	
	public void addCard(Card c){
		cards.add(c);
	}
	
	public void clear(){
		cards.clear();
	}
	
	@Override
	public String toString(){
		String retString = "";
		for(int i = 0; i < cards.size(); i++){
			retString += cards.get(i);
			retString += ". ";
		}
		return retString;
	}
	
	public int value(){
		return method.compute(cards);
	}
}
