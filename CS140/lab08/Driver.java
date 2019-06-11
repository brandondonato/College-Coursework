package lab08;

public class Driver {

	public static void main(String[] args) {
		
		BlackjackHand myHand = new BlackjackHand();
		
		Card card1 = new Card(Card.Suit.Spades, 11);
		Card card2 = new Card(Card.Suit.Hearts, 8);
	    Card card3 = new Card(Card.Suit.Diamonds, 6);
		Card card4 = new Card(Card.Suit.Hearts, 2);
		
		myHand.addCard(card1);
		myHand.addCard(card2);
		myHand.addCard(card3);
		myHand.addCard(card4);
		
		
		BlackjackMethod blackMethod = new BlackjackMethod();
		ComposedHand newHand = new ComposedHand(blackMethod);
		
		newHand.addCard(card1);
		newHand.addCard(card2);
		newHand.addCard(card3);
		newHand.addCard(card4);
		
		System.out.println(newHand);
		System.out.println(myHand);

	}

}
