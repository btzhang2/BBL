import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{

    protected static String[][] scrabbleBoard = new String[16][16];
    protected static ArrayList hundredPieces = new ArrayList(100);
    protected static String[] englishLetters= {"e","e","e","e","e","e","e","e","e","e","e","e","a","a","a","a","a","a","a","a","a","i","i","i","i","i","i","i","i","i","o","o","o","o","o","o","o","o","n","n","n","n","n","n","r","r","r","r","r","r","t","t","t","t","t","t","l","l","l","l","s","s","s","s","u","u","u","u","d","d","d","d","g","g","g","b","b","m","m","c","c","p","p","f","f","h","h","v","v","w","w","y","y","k","k","j","x","q","z"};
    protected static int skipCounter = 4;

    public static void populate(){
	//show row numbers, for row numbers 10+, the ones digis of the tens are shown
	for (int r = 0; r < 10; r++) {
	    scrabbleBoard[r][0] = Integer.toString(r);
	}
	for (int r = 10; r < 16; r++) {
	    scrabbleBoard[r][0] = Integer.toString(r).substring(1);
	}

	//show col numbers, for col numbers 10+, the ones digis of the tens are shown	
	for (int c = 0; c < 10; c++) {
	    scrabbleBoard[0][c] = Integer.toString(c) + "|";
	}
	for (int c = 10; c < 16; c++) {
	    scrabbleBoard[0][c] = Integer.toString(c).substring(1) + "|";
	    scrabbleBoard[0][15] = Integer.toString(15).substring(1);
	}
	for (int r = 1; r < 16; r++){
	    for (int c = 1; c < 16; c++){
		scrabbleBoard[r][c]="| ";
	    }
	}	
    }

    public static void printBoard() {
	for (int r = 0; r < 16; r++){
	    for (int c = 0; c < 16; c++){
		System.out.print(scrabbleBoard[r][c]);
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
	String playerName = Keyboard.readWord();
	Player player1 = new Player(playerName);
	printBoard();
	player1.firstWord();
	player1.pointsAdd();
	printBoard();
	System.out.println("Points: " + player1.getPoints());
	while(skipCounter != 0 && hundredPieces.size() != 0){
	    player1.input();
	    player1.pointsAdd();
	    printBoard();
	    System.out.println("Points: " + player1.getPoints());
	}
    }
}
