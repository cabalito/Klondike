import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Controller.BoardController;
import Controller.DeckPile;
import Controller.DiscardPile;
import Controller.Pile;
import Controller.StairPile;
import Controller.SuitPile;
import Model.Card;
import Model.CardNumber;
import Model.CardSuit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTest {

	private static BoardController board;
	private static Card asOfClovers;
	private static Card queenOfDiamonds;
	private static Card twoOfDiamonds;
	private static Card kingOfClovers;	
	private static String emptyTag;
	private static int firstStairIndex;
	private static List<StairPile> stairPiles;

	
	@Test
	public void setUp() {
		board = new BoardController();
		asOfClovers = new Card(CardNumber.ONE, CardSuit.Clovers);
		queenOfDiamonds = new Card(CardNumber.QUEEN, CardSuit.Diamonds);
		twoOfDiamonds = new Card(CardNumber.TWO, CardSuit.Diamonds);
		kingOfClovers = new Card(CardNumber.KING, CardSuit.Clovers);
		emptyTag = (String) obtainPrivateAtributeFromBoardController(new Pile(), "EMPTY_TAG");
		firstStairIndex = 1;
		stairPiles = (List<StairPile>) obtainPrivateAtributeFromBoardController(board,"stairPiles");

	}
	
	@Test
	public void test01MoveFromDeckToDiscard() {
		board.moveFromDeckToDiscard();
		DiscardPile discartedPile = (DiscardPile) obtainPrivateAtributeFromBoardController(board, "discardPile");
		assertTrue(discartedPile.takeAll().size()>0);
	}
	
	@Test
	public void test02NotMoveFromDiscartedToFULLDeck() {
		DiscardPile discartedPile = (DiscardPile) obtainPrivateAtributeFromBoardController(board,"discardPile");
		
		String discartedPileBefore = discartedPile.toString();
		board.moveFromDiscartedToDeck();
		String discartedPileAfter = discartedPile.toString();
		
		assertEquals(discartedPileBefore, discartedPileAfter);
	}
	
	@Test
	public void test03EmptyDeck(){
		DeckPile deckPile = (DeckPile) obtainPrivateAtributeFromBoardController(board, "deckPile");
		int sizeOfDeck = (int) obtainPrivateAtributeFromBoardController(deckPile, "NUM_CARDS");

		for(int i=0; i< sizeOfDeck; i++){
			board.moveFromDeckToDiscard();
		}
		
		assertEquals(emptyTag, deckPile.toString());
	}
	
	@Test
	public void test04MoveFromDiscartedToEMPTYDeck() {
		
		DiscardPile discartedPile = (DiscardPile) obtainPrivateAtributeFromBoardController(board,"discardPile");
		
		String discartedPileBefore = discartedPile.toString();
		board.moveFromDiscartedToDeck();
		String discartedPileAfter = discartedPile.toString();
		
		assertNotEquals(discartedPileBefore, discartedPileAfter);
	}
	
	@Test
	public void test05MoveFromDiscardToSuit() {
		
		DiscardPile discartedPile = (DiscardPile) obtainPrivateAtributeFromBoardController(board,"discardPile");
		SuitPile cloverSuitPile = (SuitPile) obtainPrivateAtributeFromBoardController(board,"cloverSuit");
		insertInPile(discartedPile, asOfClovers);
		
		String cloverSuitBefore = cloverSuitPile.toString();
		board.moveFromDiscardToSuit();
		String cloverSuitAfter = cloverSuitPile.toString();
		
		isCardInPile(cloverSuitBefore, cloverSuitAfter, asOfClovers);
	}

	@Test
	public void test06MoveFromDiscardToStair() {
		
		DiscardPile discartedPile = (DiscardPile) obtainPrivateAtributeFromBoardController(board,"discardPile");
		StairPile firstStairPile = stairPiles.get(0);
		
		insertInPile(discartedPile, queenOfDiamonds);
		updatePile(firstStairPile, kingOfClovers);
		
		String firstStairPileBefore = firstStairPile.toString();
		board.moveFromDiscardToStair(firstStairIndex);
		String firstStairPileAfter = firstStairPile.toString();
		
		isCardInPile(firstStairPileBefore, firstStairPileAfter, queenOfDiamonds);
	}
	
	@Test
	public void test07MoveFromStairToSuit() {
		
		StairPile firstStairPile = stairPiles.get(0);
		SuitPile cloverSuitPile = (SuitPile) obtainPrivateAtributeFromBoardController(board,"cloverSuit");

		updatePile(firstStairPile, asOfClovers);
		removeAllCard(cloverSuitPile);
		
		String cloverSuitPileBefore = cloverSuitPile.toString();
		board.moveFromStairToSuit(firstStairIndex);
		String cloverSuitPileAfter = cloverSuitPile.toString();
		
		isCardInPile(cloverSuitPileBefore, cloverSuitPileAfter, asOfClovers);
	}
	
	@Test
	public void test08MoveFromStairToStair() {
		
		int secondStairIndex = 2;
		StairPile firstStairPile = stairPiles.get(0);
		StairPile secondStairPile = stairPiles.get(1);
		
		updatePile(firstStairPile, queenOfDiamonds);
		updatePile(secondStairPile, kingOfClovers);
		
		String pileBefore = secondStairPile.toString();
		board.moveFromStairToStair(firstStairIndex, 1, secondStairIndex);
		String pileAfter = secondStairPile.toString();
		
		isCardInPile(pileBefore, pileAfter, queenOfDiamonds);
	}
	
	@Test
	public void test09MoveFromSuitToStair() {
		
		
		StairPile firstStairPile = stairPiles.get(0);
		SuitPile cloverSuitPile = (SuitPile) obtainPrivateAtributeFromBoardController(board,"cloverSuit");

		updatePile(cloverSuitPile, asOfClovers);
		updatePile(firstStairPile, twoOfDiamonds);
		
		String firstStairPileBefore = firstStairPile.toString();
		board.moveFromSuitToStair(CardSuit.Clovers, firstStairIndex);
		String firstStairPileAfter = firstStairPile.toString();
		
		isCardInPile(firstStairPileBefore, firstStairPileAfter, asOfClovers);
	}
	
	
	private void isCardInPile(String beforePileString, String afterPileString, Card card) {
		assertFalse(beforePileString.contains(card.toString()));
		assertTrue(afterPileString.contains(card.toString()));
	}
	
	
	private Object obtainPrivateAtributeFromBoardController(Object instanceOfClass, String parameterName) {
		Object privateObject = null;
		try {
			Field f = instanceOfClass.getClass().getDeclaredField(parameterName); 
			f.setAccessible(true);
			privateObject = f.get(instanceOfClass); 
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {} 
		
		return privateObject; 
	}
	
	private void insertInPile(Pile instanceOfPile, Card card) {
		try {
			Method method = instanceOfPile.getClass().getSuperclass().getDeclaredMethod("insertInPile", Card.class);
			method.setAccessible(true);
			method.invoke(instanceOfPile, card);
			} catch (IllegalAccessException | IllegalArgumentException | 
					InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	}
	

	private void updatePile(Pile instanceOfPile, Card card) {
		try {
			Method method = instanceOfPile.getClass().getSuperclass().getDeclaredMethod("updatePile", List.class);
			method.setAccessible(true);//makes public private method
			List<Card> cardList = new ArrayList<>();
			cardList.add(card);
			method.invoke(instanceOfPile, cardList);
			} catch (IllegalAccessException | IllegalArgumentException | 
					InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	}
	
	private void removeAllCard(Pile instanceOfPile) {
		try {
			Method method = instanceOfPile.getClass().getSuperclass().getDeclaredMethod("remove");
			method.setAccessible(true);
			while(!instanceOfPile.toString().contains(emptyTag)){
				method.invoke(instanceOfPile);
			}
			} catch (IllegalAccessException | IllegalArgumentException | 
					InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	}
	

}
