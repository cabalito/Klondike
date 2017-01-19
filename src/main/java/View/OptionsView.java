package View;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Controller.Game;
import Exceptions.InvalidDataException;
import Model.CardSuit;

public class OptionsView {
	
	private final String ERROR_MESSAGE = "Valor introducido incorrecto";
	private final int MIN_INDEX = 0;
	
	private BoardView boardView;
	private Game game;
	
	
	public OptionsView(Game game) {
		this.boardView = new BoardView();
		this.game = game;
	}
	
	
	public void print(Menu menu) {
		System.out.println(menu.getMessage());
	}
	
	public void select() {
		int input = inputNumericValue();
		switch (input) {
			case 1:
				game.moveToDiscard();
				break;
			case 2:
				game.moveDiscartedToDeck();
				break;
			case 3:
				game.moveFromDiscardToSuit();
				break;
			case 4:
				game.moveFromDiscardToStair();
				break;
			case 5:
				game.moveFromStairToSuit();
				break;
			case 6:
				game.moveFromStairToStair();
				break;
			case 7:
				game.moveFromSuitToStair();
				break;
			case 8:
				game.flipOverStair();
				break;
			case 9:
				break;
			default:
				game.play();
		}
	}
	
	public int selectStair() throws InvalidDataException {
		int stairNumber = inputNumericValue();
		if(stairNumber<=boardView.obtainMaxNumberOfStairs() && stairNumber>=MIN_INDEX){
			return stairNumber;
		}
		throw new InvalidDataException(ERROR_MESSAGE);
	}

	public CardSuit selectSuit() throws InvalidDataException{
		CardSuit[] arrayCardSuit = CardSuit.values();
		int suitNumber = inputNumericValue();
		if(suitNumber<=boardView.obtainMaxIndexSuitPile() && suitNumber>=MIN_INDEX){
			return arrayCardSuit[suitNumber];
		}
		throw new InvalidDataException(ERROR_MESSAGE);
	}

	
	public int inputNumericValue() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputInt = 0;
		String input = null;
		try {
			input = br.readLine();
			if(isNumeric(input)){
				inputInt = Integer.parseInt(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputInt;
	}
	
	private boolean isNumeric(String s) {  
	    return s.matches("\\d+");  
	} 
}
