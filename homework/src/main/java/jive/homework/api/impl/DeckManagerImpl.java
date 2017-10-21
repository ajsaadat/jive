package jive.homework.api.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jive.homework.api.DeckFactory;
import jive.homework.api.DeckManager;
import jive.homework.api.DeckOperator;
import jive.homework.entity.Card;
import jive.homework.entity.Deck;
import jive.homework.error.DeckNotFoundException;
import jive.homework.error.EmptyDeckException;

public class DeckManagerImpl implements DeckManager {

	private Map<Long, Deck> decks = new ConcurrentHashMap<>(); 
	private DeckFactory dFactory = new DeckFactoryImpl() ;
	private DeckOperator dOperator = new DeckOperatorImpl() ;
	
	private static DeckManagerImpl dManager= new DeckManagerImpl() ; 
	
	
	private DeckManagerImpl() {
		
	}
	
	public static DeckManagerImpl getInstance(){
		return dManager ; 
	}
	
	@Override
	public synchronized long createADeck() {
		Deck nDeck = dFactory.buildDeck() ; 
		long creationTimeStamp = System.nanoTime() ;
		decks.put(creationTimeStamp, nDeck) ;
		return creationTimeStamp;
	}

	@Override
	public synchronized void shuffleADeck(long deckID) throws DeckNotFoundException {
		Deck cachedDeck = decks.get(deckID) ;
		if(cachedDeck == null){
			throw new DeckNotFoundException("Deck [" + deckID + " is not a valid deck.") ;
		}else{
			dOperator.shuffle(cachedDeck) ; 
		}

	}

	@Override
	public synchronized Card getACard(long deckID) throws DeckNotFoundException, EmptyDeckException {
		Deck cachedDeck = decks.get(deckID) ;
		if(cachedDeck == null){
			throw new DeckNotFoundException("Deck [" + deckID + "] is not a valid deck.") ;
		}else{
			Card retrievedCard = dOperator.getACard(cachedDeck) ; 
			return retrievedCard ; 
		}
	}

}
