import java.util.ArrayList;
import cs1.Keyboard;

public class Woo {

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
	while (wordLength != 0) {
	    System.out.println("Please input the row index.");
	    int row = Keyboard.readInt();
	    System.out.println("Please input the column index.");
	    int col = Keyboard.readInt(); //try-catch later?
	    //System.out.println("Please state the direction
	    System.out.println("Please type the letter you want to input");
	    String word = Keyboard.readWord();
	    //for (int x = 0; x < word.length()
	    scrabbleBoard[row][col] = "|" + word;
	    wordLength -= 1;
	}
}
    
    public static void main (String[] args){
	Woo scrabble = new Woo();
	System.out.println(scrabble.scrabbleBoard);
	scrabble.input();
	scrabble.printBoard();	
    }
}
