package jive.homework.api;

import jive.homework.entity.Card;
import jive.homework.entity.Deck;
import jive.homework.error.EmptyDeckException;

public interface DeckOperator {

	/**
	 * Shuffles content of deck in a random fashion.
	 * @param deck, represents a deck of cards. Provided value can not be null. 
	 * If null is provided an exception will be thrown. 
	 */
	public void shuffle(Deck deck) ;
	
	/**
	 * Based on the content of the deck, last card will be 
	 * returned if deck is not empty, however if deck is empty, 
	 * null will returned. 
	 * @param deck, representing a deck of card. Provided value can not be null. 
	 * If null is provided an exception will be thrown. 
	 * @return last {@linkplain Card} in the deck will be return if deck is not empty, 
	 * otherwise null will be returned. 
	 * 
	 */
	public Card getACard(Deck deck) throws EmptyDeckException;
}
