package lab08;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestValue {

	Card card1 = new Card(Card.Suit.Hearts, 11);
	Card card2 = new Card(Card.Suit.Hearts, 2);
    Card card3 = new Card(Card.Suit.Hearts, 4);
	Card card4 = new Card(Card.Suit.Hearts, 10);
	
	BlackjackHand test1 = new BlackjackHand();
	BlackjackHand test2 = new BlackjackHand();
	BlackjackHand test3 = new BlackjackHand();
	BlackjackHand test4 = new BlackjackHand();
	BlackjackHand test5 = new BlackjackHand();
	BlackjackHand test6 = new BlackjackHand();
	
	
	@Test
	public void test1() {
		
		test1.addCard(card2);
		test1.addCard(card3);
		assertEquals(6, test1.value());
	}
	
	@Test
	public void test2() { 
		
		test2.addCard(card1);
		test2.addCard(card4);
		assertEquals(21 ,test2.value());
	}
	
	@Test
	public void test3() {
		
		test3.addCard(card1);
		test3.addCard(card1);
		assertEquals(12,test3.value());
		
	}
	
	@Test
	public void test4() {
		
		test4.addCard(card1);
		test4.addCard(card1);
		test4.addCard(card1);
		assertEquals(13, test4.value());
		
	}
	
	@Test
	public void test5() {
		
		test5.addCard(card4);
		test5.addCard(card4);
		test5.addCard(card2);
		assertEquals(22, test5.value());
		
	}
	
	@Test
	public void test6() {
		
		test6.addCard(card4);
		test6.addCard(card4);
		test6.addCard(card1);
		assertEquals(21, test6.value());
		
	}

}
