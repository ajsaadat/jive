package jive.homework.api.impl;

import org.junit.Assert;
import org.junit.Test;

import jive.homework.api.DeckManager;
import jive.homework.entity.Card;
import jive.homework.error.DeckNotFoundException;
import jive.homework.error.EmptyDeckException;

public class DeckManagerImplTest {

	
	@Test
	public void createADeck(){
		DeckManager dManager = DeckManagerImpl.getInstance() ;
		long deckID = dManager.createADeck() ; 
		Assert.assertNotNull("Error deckID can not be null.", deckID);
	}
	
	@Test
	public void getACard() throws DeckNotFoundException, EmptyDeckException{
		DeckManager dManager = DeckManagerImpl.getInstance() ;
		for(int i = 1 ; i <= 52 ; i++){
			long deckID = dManager.createADeck() ; 
			Card card = dManager.getACard(deckID) ;
			System.out.println("Retrieved card [" + card +"].");
			Assert.assertNotNull("A new deck must return a card", card);
		}
	}
	
	@Test
	public void getMoreCardsThanDeckSize() {
		DeckManager dManager = DeckManagerImpl.getInstance() ;
		long deckID = dManager.createADeck() ; 
		for(int i = 1 ; i <= 52 ; i++){
			Card card = null;
			try {
				card = dManager.getACard(deckID);
				Assert.assertNotNull("A new deck must return a card", card);
			} catch (DeckNotFoundException | EmptyDeckException e) {
				Assert.assertNull("A new deck must return a card", card);
			}
		}
	}
}
