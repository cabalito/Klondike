package Model;

public enum CardSuit {
	Hearts("♥", "Red"), Diamonds("♦","Red"), Clovers("♣","Black"), Pikes("♠","Black");

	private final String color; // Black or Red
	private final String icon;

	private CardSuit(String icon,String color) {
		this.color = color;
		this.icon = icon;
	}

	public String getColor() {
		return color;
	}
	
	public String getIcon(){
		return icon;
	}
}
