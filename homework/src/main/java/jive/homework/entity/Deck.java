package jive.homework.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of{@linkplain Card}s. 
 * @author ajsaadat
 *
 */
public class Deck {
	
	private List<Card> content = new ArrayList<>() ;
	
	public Deck(Deck deck){
		content = new ArrayList<>(deck.getContent()) ;
	}
	
	public Deck(List<Card> content){
		if(content == null || content.isEmpty()){
			throw new IllegalArgumentException("An empty deck can not be created.") ;
		}
		this.content = content ; 
	}
	
	public List<Card> getContent() {
		return content;
	}

	public void setContent(List<Card> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Deck [content=" + content + "]";
	} 
	
}
