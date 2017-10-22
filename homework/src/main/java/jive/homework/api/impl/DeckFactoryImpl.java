package jive.homework.api.impl;

import java.util.ArrayList;
import java.util.List;

import jive.homework.api.DeckFactory;
import jive.homework.entity.Card;
import jive.homework.entity.Deck;

public class DeckFactoryImpl implements DeckFactory {

	private static String[] suits = {"H", "S", "D", "C"} ;
	@Override
	public Deck buildDeck() {
		List<Card> cards = new ArrayList<>() ;
		for(int i = 1 ; i <= 10 ; i++){
			cards.addAll(buildSuits(i + "")) ;
		}
		
		cards.addAll(buildSuits("J")) ;
		cards.addAll(buildSuits("Q")) ;
		cards.addAll(buildSuits("K")) ;
		
		Deck newDeck = new Deck(cards) ;
		return newDeck;
	}
	
	private List<Card> buildSuits(String cardNumber){
		List<Card> cards = new ArrayList<>() ;
		for(String suit : suits ){
			Card newCard = new Card(suit, cardNumber) ;
			cards.add(newCard) ;
			
		}
		return cards ; 
	}

}
