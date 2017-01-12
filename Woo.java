import java.util.ArrayList;
import cs1.Keyboard;

public class Woo {

    protected static ArrayList[][] scrabbleBoard;

    public Woo(){
	scrabbleBoard = new ArrayList[15][15];
	populate();
    }

    public void populate(){
	for (int row = 0; row < 15; row++){
	    for (int col = 0; col < 15; col++){
		scrabbleBoard[row][col].add("test");
		System.out.print(scrabbleBoard[row][col]);
	    }
	    System.out.print("\n");
	}	
    }
    
    public static void main (String[] args){
    }
}
