package View;

import Model.CardSuit;

public enum Menu{
    MAIN ("---------------------------\n"
	    		+ "1. Mover de baraja a descarte\n"
	    		+ "2. Mover de descarte a baraja\n"
	    		+ "3. Mover de descarte a palo\n"
	    		+ "4. Mover de descarte a escalera\n"
	    		+ "5. Mover de escalera a palo\n"
	    		+ "6. Mover de escalera a escalera\n"
	    		+ "7. Mover de palo a escalera\n"
	    		+ "8. Voltear en escalera\n"
	    		+ "9. Salir\n"
	    		+ "Elije opción: "), 
    CARDS("¿Cuántas cartas?: "),
    FROM_STAIR("¿Desde qué escalera? [1-7]: "),
    TO_STAIR ("¿A qué escalera? [1-7]: "),
	FROM_SUIT ("¿Desde qué palo? " + cardSuitOptions());
			
 
    private final String message;
    
    public String getMessage() {
		return message;
	}

	private static String cardSuitOptions() {
		String cardSuitOptions = "";
		int index = 0;
		for(CardSuit suit : CardSuit.values()){
			cardSuitOptions += index + " ("+suit.getIcon()+"),  ";
			index++;
		}
		return cardSuitOptions;
	}

	private Menu(String message) {
		this.message = message;
	}
}