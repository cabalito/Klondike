package Controller;

import Exceptions.InvalidDataException;
import Model.CardSuit;
import View.BoardView;
import View.Menu;
import View.OptionsView;

public class Game {
	
	private BoardView board;
	private OptionsView options;
	
	public Game() {
		board = new BoardView();
		options = new OptionsView(this);
	}

	public void play(){
		board.print();
		options.print(Menu.MAIN);
		options.select();
	}

	public void moveToDiscard() {
		board.moveFromDeckToDiscard();
		play();
	}

	public void moveDiscartedToDeck() {
		board.moveFromDiscartedToDeck();
		play();
	}

	public void moveFromDiscardToSuit() {
		board.moveFromDiscardToSuit();
		play();		
	}

	public void moveFromDiscardToStair() {
		try{
			options.print(Menu.TO_STAIR);
			int stairNumber = options.selectStair();
			board.moveFromDiscardToStair(stairNumber);
		}catch(InvalidDataException e){
			board.printError(e.getMessage());
		}
		play();		
	}

	public void moveFromStairToSuit() {
		try{
			options.print(View.Menu.FROM_STAIR);
			int stairNumber = options.selectStair();
			board.moveFromStairToSuit(stairNumber);
		}catch(InvalidDataException e){
			board.printError(e.getMessage());
		}
		play();	}

	public void moveFromStairToStair() {
		try{
			options.print(Menu.FROM_STAIR);
			int stairOrigen = options.selectStair();
			
			options.print(Menu.CARDS);
			int amount = options.inputNumericValue();
			
			options.print(Menu.TO_STAIR);
			int stairDestination = options.selectStair();
			
			board.moveFromStairToStair(stairOrigen, amount, stairDestination);
		}catch(InvalidDataException e){
			board.printError(e.getMessage());
		}
		play();
	}

	public void moveFromSuitToStair() {
		try{
			options.print(Menu.FROM_SUIT);
			CardSuit cardSuit = options.selectSuit();
			
			options.print(Menu.TO_STAIR);
			int stairNumber = options.selectStair();
			board.moveFromSuitToStair(cardSuit, stairNumber);
		}catch(InvalidDataException e){
			board.printError(e.getMessage());
		}
		play();
		
	}

	public void flipOverStair() {
		try{
			options.print(Menu.TO_STAIR);
			int stairNumber = options.selectStair();
			board.flipOverStair(stairNumber);
		}catch(InvalidDataException e){
			board.printError(e.getMessage());
		}
		play();
	}

}
