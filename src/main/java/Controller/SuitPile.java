package Controller;

import Model.Card;
import Model.CardNumber;
import Model.CardSuit;

public class SuitPile extends Pile{

	private CardSuit cardSuit;
	
	public SuitPile(CardSuit suit) {
		this.cardSuit = suit;
	}

	@Override
	public boolean insertInPile(Card card){
		if(isEmpty()){
			return insertFirstCard(card);
		}else{
			if (isTheFollowOne(card)) {
				super.insertInPile(card);
				return true;
			}
		}
		
		return false;
	}

	private boolean isTheFollowOne(Card card) {
		if(isCorrectSuit(card) && isCorrectNumber(card)){
			return true;
		}
		return false;
	}

	private boolean isCorrectSuit(Card card) {
		if(card.suit == cardSuit){
			return true;
		}
		return false;
	}

	private boolean isCorrectNumber(Card card) {
		if(card.number.getPosition() == takeLastCard().number.getPosition()+1){
			return true;
		}
		return false;
	}

	private boolean insertFirstCard(Card card) {
		if (CardNumber.isFirstNumber(card)) {
			super.insertInPile(card);
			return true;
		}
		return false;
	}

}
