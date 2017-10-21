package jive.homework.error;

public class DeckNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeckNotFoundException(String message){
		super(message) ;
	}

}
