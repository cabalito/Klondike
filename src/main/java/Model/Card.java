package Model;

public class Card {

	public CardNumber number;
	public CardSuit suit;

	public Card(CardNumber number, CardSuit type) {
		this.number = number;
		this.suit = type;
	}
	
	@Override
	public String toString() {
		return "[" + (number.getNumber()+ suit.getIcon()) + "]";
	}

}
