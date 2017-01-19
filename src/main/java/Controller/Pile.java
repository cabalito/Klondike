package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Card;

public class Pile {
	
	private List<Card> cardsInPile;
	protected final String NEW_LINE = "\n";
	protected final String EMPTY_TAG = "<vacio>\t";

	public Pile() {
		this.cardsInPile = new ArrayList<>();
	}
	
	protected Card takeOne(int index){
		return this.cardsInPile.get(index);
	}
	
	protected Card takeLastCard(){
		return takeOne(lastIndex());
	}
	
	protected void updatePile(List<Card> cardList){
		this.cardsInPile = cardList;
	}
	
	protected boolean insertInPile(Card card){
		this.cardsInPile.add(card);
		return true;
	}
	
	protected boolean insertInPile(List<Card> cardList){
		this.cardsInPile.addAll(cardList);
		return true;
	}
	
	protected void remove(Card card){
		this.cardsInPile.remove(card);
	}
	
	protected void remove(){
		this.cardsInPile.remove(lastIndex());
	}
	
	protected boolean isEmpty(){
		return (this.cardsInPile.size() == 0);
	}
	
	protected int lastIndex(){
		return cardsInPile.size()-1;
	}
	
	@Override
	public String toString() {
		String deckLiteral = "";

		if (cardsInPile.isEmpty()) {
			deckLiteral = EMPTY_TAG;
		} else {
			for (Card card : cardsInPile) {
				deckLiteral += card.toString();
			}
		}
		return deckLiteral + NEW_LINE;
	}


}
