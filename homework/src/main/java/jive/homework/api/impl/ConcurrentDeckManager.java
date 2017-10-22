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

public class ConcurrentDeckManager implements DeckManager {

	private Map<Long, Deck> decks ; 
	private static volatile ConcurrentDeckManager dManager ; 
	private DeckFactory dFactory ;
	private DeckOperator dOperator ;
	private ConcurrentDeckManager() {
		decks = new ConcurrentHashMap<>() ;
		dFactory = new DeckFactoryImpl() ;
		dOperator = new DeckOperatorImpl() ;
	}
	
	public static ConcurrentDeckManager getInstance(){
		if(dManager == null){
			synchronized (dManager) {
				if(dManager == null){
					dManager =  new ConcurrentDeckManager() ; 
					return dManager ; 
				}
			}
		}
		return dManager ;  
	}
	
	@Override
	public long createADeck() {
		Deck nDeck = dFactory.buildDeck() ; 
		long creationTimeStamp = System.nanoTime() ;
		decks.put(creationTimeStamp, nDeck) ;
		return creationTimeStamp;
	}

	@Override
	public void shuffleADeck(long deckID) throws DeckNotFoundException {
		Deck cachedDeck = decks.get(deckID) ;
		if(cachedDeck == null){
			throw new DeckNotFoundException("Deck [" + deckID + " is not a valid deck.") ;
		}else{
			synchronized(cachedDeck){
				decks.put(deckID, cachedDeck) ;
			}
		}
	}

	@Override
	public Card getACard(long deckID) throws DeckNotFoundException, EmptyDeckException {
		Deck cachedDeck = decks.get(deckID) ;
		if(cachedDeck == null){
			throw new DeckNotFoundException("Deck [" + deckID + "] is not a valid deck.") ;
		}else{
			synchronized(cachedDeck){
				Card retrievedCard = dOperator.getACard(cachedDeck) ; 
				return retrievedCard ; 
			}
			
		}
	}

}
