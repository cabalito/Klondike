package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Card;
import Model.CardNumber;
import Model.CardSuit;



public class DeckPile extends Pile{
	
	private final String DECK_TAG = "[X,X] \t";
	protected final int NUM_CARDS;

	
	public DeckPile() {
		super();
		List<Card> cards = createDeck();
		NUM_CARDS = cards.size();
		insertInPile(cards);
	}

	private static List<Card> createDeck() {
		List<Card> cards = new ArrayList<Card>();
		
		for (CardSuit cardType : CardSuit.values())
			for(CardNumber cardNumber : CardNumber.values()){
				cards.add(new Card(cardNumber, cardType));
			}
		return cards;
	}
	
	void shuffle() {
		Random randomGenerator = new Random();
		
		List<Card> randomDeck = new ArrayList<Card>();
		Card randomCard;
		List<Integer> numbersGenerated = new ArrayList<Integer>();
		int index;
		while (randomDeck.size() < NUM_CARDS) {
			index = randomGenerator.nextInt(NUM_CARDS);
			if (!numbersGenerated.contains(index)) {
				numbersGenerated.add(index);
				randomCard = takeOne(index); 
				randomDeck.add(randomCard);
			}
		}
		updatePile(randomDeck);
	}

	@Override
	public String toString() {
		String literalDeck = "";

		if (isEmpty()) {
			literalDeck = EMPTY_TAG;
		} else {
			literalDeck = DECK_TAG;
		}

		return literalDeck;
	}
}

