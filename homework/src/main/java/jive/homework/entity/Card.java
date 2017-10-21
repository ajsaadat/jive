package jive.homework.entity;

/**
 * Represents a card in a deck. 
 * A card has two properties:
 *  1) suit (heart, spade, diamond, club)
 *  2) number (jack = 11, queen = 12, king = 13)
 * @author ajsaadat
 *
 */
public class Card {

	private String suit ; 
	private String number ; 
	
	public Card(String suit, String number){
		if(suit == null || suit.trim().isEmpty()){
			throw new IllegalArgumentException("Card suit can not be empty or null") ;
		}else if(number == null || number.trim().isEmpty()){
			throw new IllegalArgumentException("Card number can not be empty or null") ;
		}
		this.suit = suit ; 
		this.number = number ; 
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Card [number=" + number + ", suit=" + suit + "]";
	}
}
