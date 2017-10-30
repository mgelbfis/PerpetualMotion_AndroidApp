package com.example.mgelbfis.perpetualmotion.id_game;

public class Card {

	private Rank rank;
	private Suit suit;


	
	public Card(Rank r, Suit s){
		this.rank = r;
		this.suit = s;

	}
	
	public Rank getRank(){
		return this.rank;
	}
	
	public Suit getSuit(){
		return this.suit;
	}
	
	public int getColor(){
		return this.suit.getColor();
	}
	
	public String toString(){
		StringBuffer out = new StringBuffer();
		out.append("Rank: ");
		out.append(this.rank.toString());
		out.append(" Suit: ");
		out.append(this.suit.toString());
		//Leave out color because it is not necessary for this game
		//out.append("\nColor: ");
		//out.append(this.color.toString());
		return out.toString();	
	}
}
