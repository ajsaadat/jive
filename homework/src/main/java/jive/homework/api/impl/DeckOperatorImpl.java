package jive.homework.api.impl;

import java.util.List;
import java.util.Random;

import jive.homework.api.DeckOperator;
import jive.homework.entity.Card;
import jive.homework.entity.Deck;
import jive.homework.error.EmptyDeckException;

public class DeckOperatorImpl implements DeckOperator {

	
	@Override
	public void shuffle(Deck deck) {
		if(deck == null){
			throw new IllegalArgumentException("Provided deck can not be null") ; 
		}
		List<Card> cards = deck.getContent() ;
		if(cards.isEmpty() || cards.size() == 1){
			return ; 
		}
		
		Random random = new Random() ;  
		for(int counter = cards.size() -1 ; counter > 0 ; counter--){
			int randomPosition = random.nextInt(counter) ;
			Card randomCard = cards.get(randomPosition);
			Card temp = cards.get(counter) ;
			
			cards.set(counter, randomCard) ; 
			cards.set(randomPosition, temp) ; 
			
		}

	}

	@Override
	public Card getACard(Deck deck) throws EmptyDeckException {
		if(deck == null){
			throw new IllegalArgumentException("Provided deck can not be null") ; 
		}
		
		List<Card> cards = deck.getContent() ;
		if(cards.isEmpty()){
			throw new EmptyDeckException("There is no cards in the deck.") ; 
		}
		return cards.remove(cards.size() - 1) ;
		
	}

}
