package jive.homework.api.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import jive.homework.api.DeckFactory;
import jive.homework.api.DeckManager;
import jive.homework.api.DeckOperator;
import jive.homework.entity.Card;
import jive.homework.entity.Deck;
import jive.homework.error.DeckNotFoundException;
import jive.homework.error.EmptyDeckException;

public class NonBlockingDeckManager implements DeckManager {

	private Map<Long, AtomicReference<Deck>> decks ; 
	private static volatile NonBlockingDeckManager dManager ; 
	private DeckFactory dFactory ;
	private DeckOperator dOperator ;
	private NonBlockingDeckManager() {
		decks = new ConcurrentHashMap<>() ;
		dFactory = new DeckFactoryImpl() ;
		dOperator = new DeckOperatorImpl() ;
	}
	
	public static NonBlockingDeckManager getInstance(){
		if(dManager == null){
			synchronized (NonBlockingDeckManager.class) {
				if(dManager == null){
					dManager =  new NonBlockingDeckManager() ; 
					return dManager ; 
				}
			}
		}
		return dManager ;  
	}
	
	@Override
	public long createADeck() {
		AtomicReference<Deck> nDeck = new AtomicReference<>(dFactory.buildDeck()) ; 
		long creationTimeStamp = System.nanoTime() ;
		decks.put(creationTimeStamp, nDeck) ;
		return creationTimeStamp;
	}

	@Override
	public void shuffleADeck(long deckID) throws DeckNotFoundException {
		AtomicReference<Deck> cachedDeck = decks.get(deckID);
		Deck oldDeck = cachedDeck.get() ;
		if(oldDeck == null){
			throw new DeckNotFoundException("Deck [" + deckID + "] is not a valid deck.") ;
		}else{
			boolean updated = false ; 
	        while(!updated){
	        	Deck clonedDeck = new Deck(oldDeck) ; 
	        	dOperator.shuffle(clonedDeck);
	            updated = cachedDeck.compareAndSet(oldDeck, clonedDeck);
	        }
			
		}
	}
	
	

	@Override
	public Card getACard(long deckID) throws DeckNotFoundException, EmptyDeckException {
		AtomicReference<Deck> cachedDeck = decks.get(deckID);
		Deck oldDeck = cachedDeck.get() ;
		if(oldDeck == null){
			throw new DeckNotFoundException("Deck [" + deckID + "] is not a valid deck.") ;
		}else{
			
			boolean updated = false ; 
			Card retrievedCard = null ; 
			while(!updated){
				Deck clonedDeck = new Deck(oldDeck) ; 
				retrievedCard = dOperator.getACard(clonedDeck) ; 
				updated = cachedDeck.compareAndSet(oldDeck, clonedDeck) ; 
				oldDeck = cachedDeck.get() ;
			}
			return retrievedCard ; 
		}
	}

}
