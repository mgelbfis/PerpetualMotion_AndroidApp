package com.example.mgelbfis.perpetualmotion.id_game;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> deck;
	
	public Deck(){
		deck = new ArrayList<Card>(52);
		//fill deck with cards
		for(int i=0; i < (Suit.values()).length; i++){
				for(int k=0; k < (Rank.values()).length; k++){
					if(i>2){
						deck.add(new Card(Rank.values()[k],Suit.values()[i]));
					}
					if(i<=2){
						deck.add(new Card(Rank.values()[k],Suit.values()[i]));
					}
				}
		}
	}
	
	public void shuffle(){
		Random rand = new Random();
		int value;
		Card temp;
		
		for(int i=0; i<deck.size(); i++){
			value = rand.nextInt(52);
			temp = deck.get(i);
			deck.set(i, deck.get(value));
			deck.set(value, temp);
		}
	}
	
	public Card deal(){
		Card top = deck.get(0);
		deck.remove(0);
		return top;
	}
	
	public boolean isEmpty(){
		if(deck.isEmpty()){
		return true;
		}
		return false;
	}
	

}
