package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Card;
import Model.CardSuit;

public class BoardController {
	
	private DiscardPile discardPile;
	private DeckPile deckPile;
	
	private SuitPile diamondSuit;
	private SuitPile heartSuit;
	private SuitPile cloverSuit;
	private SuitPile pikeSuit;
	
	public final int NUM_STAIRS = 7;
	public final int NUM_SUITS = 4;
	private List<StairPile> stairPiles;
	
	public DiscardPile getDiscardPile() {
		return discardPile;
	}

	public DeckPile getDeckPile() {
		return deckPile;
	}

	public SuitPile getDiamondSuit() {
		return diamondSuit;
	}

	public SuitPile getHeartSuit() {
		return heartSuit;
	}

	public SuitPile getCloverSuit() {
		return cloverSuit;
	}

	public SuitPile getPikeSuit() {
		return pikeSuit;
	}

	public int getNUM_STAIRS() {
		return NUM_STAIRS;
	}

	public List<StairPile> getStairList() {
		return stairPiles;
	}

	public BoardController(){
		deckPile = new DeckPile();
		deckPile.shuffle();
		discardPile = new DiscardPile();
		
		diamondSuit = new SuitPile(CardSuit.Diamonds);
		heartSuit = new SuitPile(CardSuit.Hearts);
		cloverSuit = new SuitPile(CardSuit.Clovers);
		pikeSuit = new SuitPile(CardSuit.Pikes);
		
		createSevenStairs();
		
	}

	private void createSevenStairs() {
		stairPiles = new ArrayList<>();
		for(int stairNumber=0; stairNumber<NUM_STAIRS; stairNumber++){
			StairPile stair = createStair(stairNumber);
			stairPiles.add(stair);
		}
		
	}
	
	private StairPile createStair(int stairNumber) {
		List<Card> cardList = new ArrayList<>();
		int numberOfCards = stairNumber+1;
		for(int i=1; i<=numberOfCards; i++){
			Card cardFromDeck = deckPile.takeLastCard();
			cardList.add(cardFromDeck);
			deckPile.remove(cardFromDeck);
		}
		return new StairPile(cardList);
	}

	public void moveFromDeckToDiscard() {
		if(!deckPile.isEmpty()){
			Card card = deckPile.takeLastCard();
			discardPile.insertInPile(card);
			deckPile.remove(card);
		}
	}

	public void moveFromDiscartedToDeck() {
		if(deckPile.isEmpty()){
			if(!discardPile.isEmpty()){
				List<Card> discartedCards = discardPile.takeAll();
				deckPile.updatePile(discartedCards);
				discardPile.removeAll();
			}
		}
		
	}

	public void moveFromDiscardToSuit() {
		if(!discardPile.isEmpty()){
			Card card = discardPile.takeLastCard();
			boolean isCorrect = obtainSuitPile(card.suit).insertInPile(card);
			if(isCorrect){
				discardPile.remove(card);
			}
		}		
	}
	
	public void moveFromDiscardToStair(int selectedStair) {
		int stairNumber = selectedStair-1;
		if(!discardPile.isEmpty()){
			Card card = discardPile.takeLastCard();
			StairPile stair = stairPiles.get(stairNumber);
			boolean isCorrect = stair.insertInPile(card);
			if(isCorrect){
				discardPile.remove(card);
			}
		}
	}
	
	private SuitPile obtainSuitPile(CardSuit cardSuit) {
		SuitPile suitPile;
		switch (cardSuit) {
			case Diamonds:
				suitPile = diamondSuit;
				break;
			case Hearts:
				suitPile = heartSuit;
				break;
			case Pikes:
				suitPile = pikeSuit;
				break;
			case Clovers:
				suitPile = cloverSuit;
				break;
			default:
				suitPile = null;
				break;
			}
		return suitPile;
	}

	public void moveFromStairToSuit(int selectedStair) {
		int stairNumber = selectedStair-1;
		StairPile stairPile = stairPiles.get(stairNumber);
		if(!stairPile.isEmpty()){
			Card card = stairPile.takeLastCard();
			boolean isCorrect = obtainSuitPile(card.suit).insertInPile(card);
			if(isCorrect){
				stairPile.remove(card);
			}
		}
	}

	public void moveFromStairToStair(int stairOrigen, int amount, int stairDestination) {
		int numberStairOrigen = stairOrigen-1;
		int numberStairDestination = stairDestination-1;
		StairPile origen = stairPiles.get(numberStairOrigen);
		if(!origen.isEmpty()){
			List<Card> cardList = origen.takeMany(amount);
			StairPile destination = stairPiles.get(numberStairDestination);
			boolean isCorrect = destination.insertInPile(cardList);
			if(isCorrect){
				for(Card card : cardList){
					origen.remove(card);
				}
			}
		}
	}

	public void moveFromSuitToStair(CardSuit cardSuit, int selectedStair) {
		int stairNumber = selectedStair-1;
		SuitPile suitPile = obtainSuitPile(cardSuit);
		if(!suitPile.isEmpty()){
			Card card =  suitPile.takeLastCard();
			StairPile stairPile = stairPiles.get(stairNumber);
			boolean isCorrect = stairPile.insertInPile(card);
			if(isCorrect){
				suitPile.remove(card);
			}
		}
	}

	public void flipOverStair(int selectedStair) {
		int stairNumber = selectedStair-1;
		StairPile stairPile = stairPiles.get(stairNumber);
		stairPile.flipOver();
	}

}
