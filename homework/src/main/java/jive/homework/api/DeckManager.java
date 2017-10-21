package jive.homework.api;

import jive.homework.entity.Card;
import jive.homework.error.DeckNotFoundException;
import jive.homework.error.EmptyDeckException;

public interface DeckManager {

	/**
	 * Creates a new deck and returns its identifier.
	 * @return identifier of the deck, this can be use for furthur 
	 * operations on a deck.
	 */
	public long createADeck() ; 
	/**
	 * Shuffles content of specified deck. 
	 * @param deckID, identifier of the deck to be shuffled. 
	 * If deckID does not exist, DeckNotFoundException will be thrown.
	 */
	public void shuffleADeck(long deckID) throws DeckNotFoundException; 
	/**
	 * Returns a card from the specified non-empty, valid deck.
	 * @param deckID identifier of the deck to be shuffled. 
	 * If deckID does not exist, DeckNotFoundException will be thrown.
	 * @return A card from deck. 
	 * @throws DeckNotFoundException if deckID does not represent a deck. 
	 * throws EmptyDeckException if specified deck is empty.
	 */
	public Card getACard(long deckID) throws DeckNotFoundException, EmptyDeckException;
}
