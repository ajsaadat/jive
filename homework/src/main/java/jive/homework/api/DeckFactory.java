package jive.homework.api;

import jive.homework.entity.Deck;

/**
 * Builds a deck of cards containing 52 cards. 
 * @author ajsaadat
 *
 */
public interface DeckFactory {
	/**
	 * Builds a deck of cards
	 * @return a {@linkplain Deck}.
	 */
	public Deck buildDeck() ; 
}
