import java.util.ArrayList;
import cs1.Keyboard;

public class Woo{

    protected static String[][] scrabbleBoard = new String[15][15];
    protected static ArrayList hundredPieces = new ArrayList(100);
    protected static String[] englishLetters= {"e","e","e","e","e","e","e","e","e","e","e","e","a","a","a","a","a","a","a","a","a","i","i","i","i","i","i","i","i","i","o","o","o","o","o","o","o","o","n","n","n","n","n","n","r","r","r","r","r","r","t","t","t","t","t","t","l","l","l","l","s","s","s","s","u","u","u","u","d","d","d","d","g","g","g","b","b","m","m","c","c","p","p","f","f","h","h","v","v","w","w","y","y","k","k","j","x","q","z"};
    protected static int skipCounter = 4;

    public static void populate(){
	for (int row = 0; row < 15; row++){
	    for (int col = 0; col < 15; col++){
		scrabbleBoard[row][col]="| ";
	    }
	}	
    }

    public static void printBoard() {
	for (int row = 0; row < 15; row++){
	    for (int col = 0; col < 15; col++){
		System.out.print(scrabbleBoard[row][col]);
	    }
	    System.out.print("|\n");
	}
    }

    public static void hundred(String[] s){
	for(int i = 0; i< s.length; i++){
	    hundredPieces.add(englishLetters[i]);
	}
    }

    public static void swap( int i, int j ) {
	String x = (String)hundredPieces.get(i);
	String y = (String)hundredPieces.get(j);
	hundredPieces.remove(i);
	hundredPieces.remove(j);
	hundredPieces.add(j, x);
        hundredPieces.add(i, y);
	
    }


    public static void scramble() {
	for(int i = 0; i<hundredPieces.size(); i++){
	    swap(i, ((int)(Math.random() * hundredPieces.size()-1)));
	}

    }


    
    
    public static void main (String[] args){
	populate();
	hundred(englishLetters);
	
       
	System.out.println("How many players will there be?");
	int playerNumber = Keyboard.readInt();
	System.out.println("Player number 1 what is your name?");
	String playerName = Keyboard.readString();
	Player player1 = new Player(playerName);
	System.out.println(scrabbleBoard);
	while(skipCounter != 0 && hundredPieces.size() != 0){
	    player1.input();
	    printBoard();
	}
    }

}
