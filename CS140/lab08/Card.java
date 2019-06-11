package lab08;

public class Card {

	public static enum Suit {Hearts, Diamonds, Clubs, Spades};
	
	private Suit suit;
	private int value;
	
	public Card(Suit inputSuit, int inputValue){
		suit = inputSuit;
		if(inputValue >= 2 && inputValue <= 11 ){
			value = inputValue;
		}else{
			throw new IllegalArgumentException("You must provide a valid number");
		}
	}
	
	public void setSuit(Suit inputSuit){
		suit = inputSuit;
		
	}
	
	public void setValue(int inputValue){
		value = inputValue;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public boolean equals(Object other){
		boolean retValue = true;
		if(other instanceof Card){
			Card compareCard = (Card)other;
			if(compareCard.getValue() != this.value){
				retValue = false;
			}
			if(compareCard.getSuit() != this.suit){
				retValue = false;
			}
		}else{
			retValue = false;
		}
		return retValue;
	}
	
	@Override
	public int hashCode(){
		return (31 * suit.hashCode()) + (7 * Integer.hashCode(value)); 
	}
	
	@Override
	public String toString(){
		String retString = "";
		if(this.value == 11){
			retString = retString + "Ace";
		}else{
			retString = retString + value;
		}
		retString = retString + " of " + suit.toString();
		return retString;
	}
	
	public static void main(String[] args){
		
		Card test1 = new Card(Suit.Spades, 2);
		Card test2 = new Card(Suit.Spades, 2);
		Card test3 = new Card(Suit.Clubs, 11);
		Card test4 = new Card(Suit.Diamonds, 6);
		Card test5 = new Card(Suit.Hearts, 9);
	}
	
}
