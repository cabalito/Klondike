package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Card;

public class DiscardPile extends Pile{
	
	public List<Card> takeAll(){
		List<Card> discartedList = new ArrayList<>();
		for(int index=lastIndex(); index>=0; index--){
			discartedList.add(takeOne(index));
		}
		return discartedList;
	}

	public void removeAll() {
		while(!isEmpty()){
			remove();
		}
	}

}
