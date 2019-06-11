package lab08;

import java.util.ArrayList;

public abstract class CardHand {

	ArrayList<Card> cards = new ArrayList<Card>();
	
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
	
	public abstract int value();
}
