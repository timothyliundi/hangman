//2501964711 - Timothy Liundi - Object Oriented Programming - Assignment 1 

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hangman{
	final String WORDLIST_FILENAME = "words.txt";

	public Hangman(){
		ArrayList<String> wordList = loadWords();
		String secretWord = chooseWord(wordList).toLowerCase();
		startGame(secretWord);
	}
	
	/**
	 * Returns a list of valid words. Words are strings of lowercase letters.
	 * Depending on the size of the word list, this function may
	 * take a while to finish.
	 */
	ArrayList<String> loadWords(){
		ArrayList<String> wordList = new ArrayList<>(55909);
		System.out.println("Loading word list from file...");
		try{
			Scanner input = new Scanner(new File(WORDLIST_FILENAME));
			while(input.hasNext()){
				wordList.add(input.next());
			}
		}
		catch(FileNotFoundException ex){
            System.out.println("File not found");
        }
		System.out.println(wordList.size() + " " + "words loaded.");
		
		return wordList;
	}
	
	/**
	 * wordlist (list): list of words (strings)
	 * Returns a word from wordlist at random
	 */
	String chooseWord(ArrayList<String> wordList){
		//FILL IN YOUR CODE HERE...
		
		//Generate random number
		Random ran = new Random();
		int x = ran.nextInt(wordList.size());
		//Return wordList with random index
		System.out.println(wordList.get(x));
		return wordList.get(x);
	}

	/**
	 * secretWord: string, the word the user is guessing
	 * lettersGuessed: list, what letters have been guessed so far
	 * returns: boolean, True if all the letters of secretWord are in lettersGuessed;
	 * False otherwise
	 */
	 boolean isWordGuessed(String secretWord, ArrayList<Character> lettersGuessed){
		//FILL IN YOUR CODE HERE...
		 
		//Check if all the letter in secretWord is already in the lettersGuessed list
		int counter=0;
		//Create nested loop to secretWord and lettersGuessed
		for(int i=0;i<secretWord.length();i++) {
			 for(int j=0;j<lettersGuessed.size();j++) {
				 //counter will be added by 1 if a letter in the list is 
				 //in the secretWord
				 if(secretWord.charAt(i)==lettersGuessed.get(j)) {
					 counter++;
				 }
			 }
		 }
		//Return true if counter is equal to length of secretWord meaning
		//every letter in secretWord is already in lettersGuessed list
		if(counter==secretWord.length()) {
			return true;
		//else return false meaning there is/are letter(s) that
		//have not been guessed
		}else return false;
	 }
	 
	 /**
	  * secretWord: string, the word the user is guessing
	  * lettersGuessed: list, what letters have been guessed so far
	  * returns: string, comprised of letters and underscores that represents
	  * what letters in secretWord have been guessed so far.
      */
	 String getGuessedWord(String secretWord, ArrayList<Character> lettersGuessed){
		 //FILL IN YOUR CODE HERE...
		 
		 //Create string to create the comprised of letters and underscores
		 StringBuilder guessed = new StringBuilder();
		 //For every letter in secretWord
		 for(int i=0;i<secretWord.length();i++) {
			 //Generate count
			 int count = 0;
			 //For every letter in the lettersGuessed list
			 for(int j=0;j<lettersGuessed.size();j++) {
				 //If the letter in secretWord is equal to the letter
				 //in the lettersGuessed list
				 if(secretWord.charAt(i)==lettersGuessed.get(j)) {
					 //add that letter to guessed string, then force continue
					 guessed.append(secretWord.charAt(i));
					 continue;
				 //else add count by 1
				 }else count++;
			 }
			 //count could be the same amount as the size of lettersGuessed
			 //if all the letters in the lettersGuessed list is not
			 //equal to the current secretWord letter.
			 if(count==lettersGuessed.size()) {
				 //add underscore to the guessed string
				 guessed.append("_ ");
			 }
			 //Reset counter
			 count=0;
		 }
		 //Exits the loop, return the guessed string
		 return guessed.toString();
		 
	 }
	 
	 /**
	  * lettersGuessed: list, what letters have been guessed so far
	  * returns: string, comprised of letters that represents what letters have not
	  * yet been guessed.
	  */
	 String getAvailableLetters(ArrayList<Character> lettersGuessed){
		 //FILL IN YOUR CODE HERE...
		 
		 //Create string to generate the alphabets
		 StringBuilder alphabets = new StringBuilder();
		 //For every lower case letters.
		 for(char i='a'; i<'z';i++) {
			 //Create boolean
			 boolean checker=false;
			 //Create another for loop in range of lettersGuessed size
			 for(int j=0;j<lettersGuessed.size();j++) {
				 //Check every letter from a to z
				 if(i==lettersGuessed.get(j)) {
					 //If it is equal to the current lettersGuessed letter,
					 //boolean set to true, force break
					 checker=true;
					 break;
				 }
			 }
			 //If the boolean is still false, meaning the current letter is
			 //not equal to all letters in lettersGuessed list
			 if(!checker) {
				 //Add the letter to alphabets string
				 alphabets.append(i);
			 }
		 }
		 //Return alphabets string, resulting in every letter that has not
		 //been guessed
		 return alphabets.toString();
	 }
	
	/**
	 * secretWord: string, the secret word to guess.
	 * 
	 * Starts up an interactive game of Hangman.
	 * 
	 * At the start of the game, let the user know how many 
	 * letters the secretWord contains.
	 * 
	 * Ask the user to supply one guess (i.e. letter) per round.
	 * 
	 * The user should receive feedback immediately after each guess 
	 * about whether their guess appears in the computers word.
	 * 
	 * After each round, you should also display to the user the 
	 * partially guessed word so far, as well as letters that the 
	 * user has not yet guessed.
	 * 
	 * Follows the other limitations detailed in the problem write-up.
	 */
	 void startGame(String secretWord){
		 //FILL IN YOUR CODE HERE...
		 
		 //Game Header
		 System.out.println("Welcome to the game, Hangman!");
		 System.out.println("I am thinking of a word that is " + secretWord.length() + " letter long.");
		 ArrayList<Character> lettersGuessed = new ArrayList<Character>();
		 
		 //For every chances that were given, which is 8
		 for(int chance=8;chance>0;chance--) {
			 System.out.println("-------------");
			
			 //Check first if player has already guessed the word using the boolean isWordGuessed
			 if(isWordGuessed(secretWord,lettersGuessed)) {
				 System.out.println("Congratulations, you won!");
				 return;
			 }
			 //If not, continue the game
			 System.out.println("You have " + chance + " guesses left.");
			 System.out.println("Available letters: " + getAvailableLetters(lettersGuessed));
			 
			 //Check the input
			 //Input must be a-z or A-Z
			 Scanner input = new Scanner(System.in);
			 System.out.print("Please guess a letter: ");
			 char letter = input.next().charAt(0);
			 //Check if player inputs capital letter, force lower case
			 if(letter>=65 && letter<=90) {
				 letter+=32;
			 }
			 //Check if player input other than lower case or upper case letter
			 if(letter<97 || letter>122) {
				 System.out.println("Please input a letter!");
				 chance++;
				 continue;
			 }
			 
			 //Check if the letterGuessed (which is the list of letters that have been guessed so far)
			 //if the input letter is in the list
			 int counter = 0;
			 //If not
			 if(!lettersGuessed.contains(letter)) {
				 lettersGuessed.add(letter);
				 //Check if the input is in the secretWord (the word that needs to be guessed)
				 for(int i=0;i<secretWord.length();i++) {
					//counter will be added by 1 if the letter is not in the secretWord
					 if(letter!=secretWord.charAt(i)) {
						 counter++;
					 }
				 }
				 //In this case, the counter should be equal to the length of secretWord if the letter
				 //is not in the secretWord
				 if(counter==secretWord.length()) {
					 System.out.println("Oops! That letter is not in my word: "+ getGuessedWord(secretWord,lettersGuessed));
				 }
				 //If counter is not equal to secretWord.length(), meaning the letter should be
				 //somewhere in the secretWord
				 else{
					 System.out.println("Good guess: "+ getGuessedWord(secretWord,lettersGuessed));
					 //Add 1 to chance because player guessed a letter
					 chance++;
				 }
			 }
			 //If the letter is already in the list
			 else {
				 System.out.println("Oops! You've already guessed that letter: ");
				 chance++;
			 }
		 }
		 //Outside of for loop, meaning player has no more chances left
		 System.out.println("-------------");
		 System.out.println("Sorry, you ran out of guesses. The word was "+ secretWord +".");
	 }
	 
	 public static void main(String[] args){
		Hangman hangman = new Hangman();
	 }
}
