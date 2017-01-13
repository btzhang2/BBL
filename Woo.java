import java.util.ArrayList;
import cs1.Keyboard;

public class Woo{

    protected  String[][] scrabbleBoard;

    public Woo(){
	scrabbleBoard = new String[15][15];
	populate();
    }

    public void populate(){
	for (int row = 0; row < 15; row++){
	    for (int col = 0; col < 15; col++){
		scrabbleBoard[row][col]="| ";
		System.out.print(scrabbleBoard[row][col]);
	    }
	    System.out.print("|\n");
	}	
    }

    public void printBoard() {
	for (int row = 0; row < 15; row++){
	    for (int col = 0; col < 15; col++){
		System.out.print(scrabbleBoard[row][col]);
	    }
	    System.out.print("|\n");
	}
    }

    public void input(){
	System.out.println("Please enter the word length");
	int wordLength = Keyboard.readInt();
	int wordIndex = 0;
	System.out.println("Please input the row index.");
	int row = Keyboard.readInt();
	System.out.println("Please input the column index.");
	int col = Keyboard.readInt(); //try-catch later?
	System.out.println("Please type 'right' to build your word right to left or 'up' to build your word up to down");
	String direction = Keyboard.readWord();
	System.out.println("Please type the word you want to input");
	String word = Keyboard.readWord();
	boolean validWord = Dictionary.wordChecker(word);
	if (validWord) {
	    if (direction.equals("right")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    col+=1;
		    wordIndex+=1;
		}
	    }
	    if (direction.equals("up")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    row+=1;
		    wordIndex+=1;
		}
	    }
	}
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    input();
	}
    }
    
    
    public static void main (String[] args){
	Woo scrabble = new Woo();
	System.out.println(scrabble.scrabbleBoard);
	scrabble.input();
	scrabble.printBoard();	
    }
}
