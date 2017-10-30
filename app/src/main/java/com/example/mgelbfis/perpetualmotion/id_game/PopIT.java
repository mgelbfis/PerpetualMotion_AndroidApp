package com.example.mgelbfis.perpetualmotion.id_game;

import java.util.Scanner;
import java.util.Stack;

public class PopIT {
	private Deck theDeck;
	private Stack<Card>[] stacks; 
	private int quantity;
	
	//constructor
	public PopIT(){
		this.theDeck = new Deck();
		this.theDeck.shuffle();
		this.stacks = (Stack<Card>[]) new Stack[4];
		this.quantity = 52;
		
		//fill stacks with 4 stacks and put 1 card into each of the 4
		int i = 0;
		while(i < stacks.length){
			this.stacks[i] = new Stack();
			this.stacks[i].push(theDeck.deal());
			i++;
		}
	}
	
	public void display(){
		StringBuffer display = new StringBuffer();
		display.append("Stack 1:\t\t\t\tStack 2:\t\t\t\tStack 3:\t\t\t\tStack 4:\n");
		for(int i = 0; i < stacks.length; i++){
			if(stacks[i].isEmpty()){
				display.append("\t\t--\t\t\t");
			}
			else{
				display.append(stacks[i].peek().toString());
				display.append("\t\t");
			}
		}
		display.append("\n\nKeep poppin' It! There are ");
		display.append(quantity);
		display.append(" cards left to be discarded.");
		System.out.println(display.toString());
	}
	
	public void discard(int choice, int stack1, int stack2)throws InvalidInputException{
		
		if(stack1<1 || stack1>4 || stack2<1 || stack2>4){
			throw new InvalidInputException("You entered a stack number that does not exist");
		}
		
		//correct the stacks to work with the arrays
		stack1--;
		stack2--;
		
		if(stacks[stack1].isEmpty()||stacks[stack2].isEmpty()){
			throw new InvalidInputException("One of the decks you entered is already empty.");
		}
		
		switch(choice){
		case 1:if(stacks[stack1].peek().getRank().getValue().equals(stacks[stack2].peek().getRank().getValue()))
				{
					stacks[stack1].pop();
					stacks[stack2].pop();
					this.quantity -= 2;
				}
				else return;
			break;
		case 2:if(stacks[stack1].peek().getSuit().equals(stacks[stack2].peek().getSuit()))
				{
					if(stacks[stack1].peek().getRank().getValue()
							< stacks[stack2].peek().getRank().getValue())
					{
						stacks[stack1].pop();
						this.quantity--;
					}
					else if(stacks[stack2].peek().getRank().getValue()
							< stacks[stack1].peek().getRank().getValue())
					{
						stacks[stack2].pop();
						this.quantity--;
					}
				}
				else return;
			break;
		//to protect this class from other programmers
		default: return;
		}
		
	}
	
	public boolean refillStacks(){
		if(theDeck.isEmpty()){
			return false;
		}
		for(Stack s: this.stacks){
			s.push(this.theDeck.deal());
		}
		return true;
	}
	
	public boolean gameWon(){
		boolean done = true;
		for(int i=0; i<stacks.length; i++){
			if(!stacks[i].isEmpty()){
				done = false;
			}
		}
		if(!theDeck.isEmpty()){
			done = false;
		}
		return done;
	}
	
	public static void main(String []args){
		PopIT NewGame = new PopIT();
		
		boolean win = false;
		
		while(!win){
			
			NewGame.display();
			
			//I didn't make a separate method to get the choice 
			//because that's basically what the whole main is

			System.out.println("\nType the number of the action you would like to do this turn:");
			
			System.out.println("\n\t1. Pick 2 cards of the same rank "
					+ "and both will be discarded \n\t2. Pick 2 "
					+ "cards of the same suit and"
					+ " the one with the lower rank will be discarded."
					+ "\n\t3. Deal 4 new cards, 1 on top of each stack."
					+ "\n\n\tNOTE: 10, Jack, Queen and King are all the same rank.\n");
			
			Scanner player = new Scanner(System.in);
			
			int choice = player.nextInt();
			
			try{
			switch(choice){
			case 1:
			case 2:
					System.out.println("Enter the number of the first stack:");
						int stack1 = player.nextInt();
						System.out.println("Enter the number of the second stack:");
						int stack2 = player.nextInt();
						NewGame.discard(choice, stack1, stack2);
					break;
			case 3: boolean filled = NewGame.refillStacks();
					if(!filled){
						System.out.println("Sorry, there are no more cards left to deal. If you can't do anything, you lost."
								+ "\nDo you want to try one more move? Enter 1 if yes, 2 if not.");
						int cont = player.nextInt();
						if(cont==2){
							System.out.println("Sorry! Ending application...feel free to try again later!");
							System.exit(0);
						}
					}
					break;
			default: throw new InvalidInputException("That was not a valid choice.");
			}//ends switch
			}
			
			catch(InvalidInputException e){
				System.out.println(e);
			}
			
			win = NewGame.gameWon();
		}//ends while
		
		System.out.println("CONGRATULATIONS!! YOU WON!!");
	}//ends main

}//ends PopIT
