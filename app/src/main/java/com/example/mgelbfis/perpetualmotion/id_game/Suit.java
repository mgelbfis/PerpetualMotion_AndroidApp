package com.example.mgelbfis.perpetualmotion.id_game;

import android.graphics.Color;

public enum Suit {
	HEARTS (Color.RED, '♥'), DIAMONDS (Color.RED, '♦'), CLUBS (Color.BLACK, '♣'), SPADES (Color.BLACK, '♠');

	private int color;
	private char character;

	Suit(int color, char character)
	{
		this.color = color;
		this.character = character;
	}

	public char getCharacter()
	{
		return character;
	}

	public int getColor()
	{
		return color;
	}
}
