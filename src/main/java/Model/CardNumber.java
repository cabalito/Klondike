package Model;

public enum CardNumber {
	ONE(1,"A"), TWO(2,"2"), THREE(3,"3"), FOUR(4,"4"), FIVE(5,"5"), SIX(6,"6"),
	SEVEN(7,"7"), EIGHT(8,"8"), NINE(9,"9"), TEN(10,"10"), JACK(11,"J"), QUEEN(12,"Q"), KING(13,"K");
 
	private final String cardNumber; 
	private final int position;

	private CardNumber(int position,String cardNumber) {
		this.position = position;
		this.cardNumber = cardNumber;
	}
	
	String getNumber(){
		return this.cardNumber;
	}
	
	public int getPosition() {
		return position;
	}
	
	public static boolean isFirstNumber(Card card) {
		if(card.number == ONE){
			return true;
		}
		return false;
	}
	
	public static boolean isLastNumber(Card card) {
		if(card.number == KING){
			return true;
		}
		return false;
	}
	
}