package Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Model.Card;
import Model.CardNumber;

public class StairPile extends Pile{
	
	int discartedElements;
	private final String COVERED_CARD_TAG = "[";
	
	public StairPile(List<Card> cardList) {
		super();
		super.insertInPile(cardList);
		discartedElements = 1;
	}

	@Override
	protected boolean insertInPile(Card card) {
		boolean isCorrect = false;
		if(!isEmpty()){
			if (isTheFollowOne(card)) {
				discartedElements++;
				isCorrect = super.insertInPile(card);
				return isCorrect;
			}
		}else{
			return insertFirstCard(card);
		}
		
		return false;
	}
	
	List<Card> takeMany(int amount){
		List<Card> cardList = new ArrayList<>();
		if( amount<=discartedElements){
			int initial=lastIndex()-amount+1;
			for(int index=initial; index<=lastIndex(); index++){
				cardList.add(takeOne(index));
			}
		}
		return cardList;
	}
	
	public void flipOver() {
		if(!isEmpty() && discartedElements == 0){
			discartedElements++;
		}
	}
	
	private boolean insertFirstCard(Card card) {
		if (CardNumber.isLastNumber(card)) {
			super.insertInPile(card);
			discartedElements++;
			return true;
		}
		return false;
	}

	
	@Override
	protected boolean insertInPile(List<Card> cardList) {
		boolean isCorrect = true;
		for(Card card : cardList){
			if(isCorrect){
				isCorrect = insertInPile(card);
			}else{
				return false;
			}
		}
		return isCorrect;
	}
	
	@Override
	protected void remove(Card card){
		super.remove(card);
		discartedElements--;
	}
	
	@Override
	protected void remove(){
		super.remove();
		discartedElements--;
	}

	private boolean isTheFollowOne(Card card) {
		if(isCorrectSuit(card) && isCorrectNumber(card)){
			return true;
		}
		return false;
	}

	
	private boolean isCorrectSuit(Card card) {
		if(card.suit.getColor() != takeLastCard().suit.getColor()){
			return true;
		}
		return false;
	}

	private boolean isCorrectNumber(Card card) {
		if(card.number.getPosition() == takeLastCard().number.getPosition()-1){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String deckLiteral = "";
		int pileSize = lastIndex()+ 1;
		if (!isEmpty()) {
			int coveredElements =  pileSize - discartedElements;
			for(int i=0; i<coveredElements; i++){
				deckLiteral += COVERED_CARD_TAG;
			}
			for(int i=coveredElements; i<=lastIndex(); i++){
				deckLiteral += takeOne(i).toString();
			}
		}else{
			deckLiteral += EMPTY_TAG;
		}
		return deckLiteral + NEW_LINE;
	}

}
