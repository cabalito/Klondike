package View;

import Controller.BoardController;
import Controller.Pile;
import Controller.StairPile;
import Model.CardSuit;

public class BoardView {

	private final String NEW_BOARD_TAG = "========================\n";
	private BoardController board;

	public BoardView() {
		this.board = new BoardController();
	}

	public void print() {
		System.out.println(toString());
	}
	
	public void printError(String error) {
		System.err.println(error);
	}

	@Override
	public String toString() {
		String literalDeck = NEW_BOARD_TAG;
		literalDeck += pileToString("Baraja: ", board.getDeckPile());
		literalDeck += pileToString("Descartes: ", board.getDiscardPile());
		literalDeck += "\n";

		literalDeck += pileToString(CardSuit.Hearts.getIcon() + ": ", board.getHeartSuit());
		literalDeck += pileToString(CardSuit.Diamonds.getIcon() + ": ", board.getDiamondSuit());
		literalDeck += pileToString(CardSuit.Clovers.getIcon() + ": ", board.getCloverSuit());
		literalDeck += pileToString(CardSuit.Pikes.getIcon() + ": ", board.getPikeSuit());
		literalDeck += "\n";

		literalDeck += stairsToString();

		return literalDeck;
	}

	private String stairsToString() {
		int stairIndex = 1;
		String literalStairs = "";
		for (Pile stair : board.getStairList()) {
			literalStairs += pileToString("Escalera " + stairIndex + ": ", stair);
			stairIndex++;
		}
		return literalStairs;
	}

	private String pileToString(String pileName, Pile pile) {
		String deckString = pileName;
		deckString += pile.toString();

		return deckString;
	}

	public void moveFromDeckToDiscard() {
		board.moveFromDeckToDiscard();
		
	}

	public void moveFromDiscartedToDeck() {
		board.moveFromDiscartedToDeck();
	}

	public void moveFromDiscardToSuit() {
		board.moveFromDiscardToSuit();
		
	}

	public void moveFromDiscardToStair(int stairNumber) {
		board.moveFromDiscardToStair(stairNumber);
		
	}

	public void moveFromStairToSuit(int stairNumber) {
		board.moveFromStairToSuit(stairNumber);
		
	}

	public void moveFromStairToStair(int stairOrigen, int amount, int stairDestination) {
		board.moveFromStairToStair(stairOrigen, amount, stairDestination);
		
	}

	public void moveFromSuitToStair(CardSuit cardSuit, int stairNumber) {
		board.moveFromSuitToStair(cardSuit,stairNumber);
		
	}

	public void flipOverStair(int stairNumber) {
		board.flipOverStair(stairNumber);
		
	}

	public int obtainMaxNumberOfStairs() {
		return board.NUM_STAIRS;
	}

	public int obtainMaxIndexSuitPile() {
		return board.NUM_SUITS;
	}

}
