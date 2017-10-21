package jive.homework.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import jive.homework.api.DeckFactory;
import jive.homework.api.DeckOperator;
import jive.homework.entity.Card;
import jive.homework.entity.Deck;

public class DeckOperatorImplTest {

	
	@Test
	public void shuffle(){
		DeckFactory dFactory = new DeckFactoryImpl() ; 
		Deck nDeck = dFactory.buildDeck() ; 
		DeckOperator dOperator = new DeckOperatorImpl() ; 
		System.out.println("before deck: " + nDeck);
		List<Card> contentBeforeShuffle = new ArrayList<Card>(nDeck.getContent()) ;
		dOperator.shuffle(nDeck);
		Assert.assertNotEquals("Order of cards in the deck can not be same after shuffle", 
				nDeck.getContent(), contentBeforeShuffle);
		
		
	}
	
	@Test
	public void getACard() throws Exception{
		DeckFactory dFactory = new DeckFactoryImpl() ; 
		Deck nDeck = dFactory.buildDeck() ; 
		DeckOperator dOperator = new DeckOperatorImpl() ; 
		System.out.println("Deck before popping " + nDeck);
		Card card = dOperator.getACard(nDeck) ; 
		System.out.println(card);
		System.out.println("Deck after popping " + nDeck);
		dOperator.shuffle(nDeck);
		card = dOperator.getACard(nDeck) ; 
		System.out.println(card);
		System.out.println("Deck after popping n shuffle " + nDeck);
	}
}
