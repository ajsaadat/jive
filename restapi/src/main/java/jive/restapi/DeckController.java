package jive.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jive.homework.api.DeckManager;
import jive.homework.api.impl.DeckManagerImpl;
import jive.homework.entity.Card;
import jive.homework.error.DeckNotFoundException;
import jive.homework.error.EmptyDeckException;

@RestController
public class DeckController {

	    private DeckManager dManager = DeckManagerImpl.getInstance() ;

	    @RequestMapping(method=RequestMethod.GET , value="/deck")
	    public Card getACard(@RequestParam(value="deckID") Long deckID) throws DeckNotFoundException, EmptyDeckException {
	        Card retrievedCard = dManager.getACard(deckID) ;
	        return retrievedCard ; 
	    }
	    
	    @RequestMapping(method=RequestMethod.POST , value="/deck")
	    public Long createADeck(){
	    	Long deckID = dManager.createADeck() ; 
	    	return deckID ; 
	    }
	    
	    @RequestMapping(method=RequestMethod.PUT , value="/deck")
	    public void shuffleADeck(@RequestParam(value="deckID") Long deckID) throws DeckNotFoundException{
	    	dManager.shuffleADeck(deckID);
	    }
	    
}
