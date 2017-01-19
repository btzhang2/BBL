import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{

    private String[][] scrabbleBoard = new String[16][16];
    private ArrayList hundredPieces = new ArrayList(100);
    private String[] englishLetters= {"e","e","e","e","e","e","e","e","e","e","e","e","a","a","a","a","a","a","a","a","a","i","i","i","i","i","i","i","i","i","o","o","o","o","o","o","o","o","n","n","n","n","n","n","r","r","r","r","r","r","t","t","t","t","t","t","l","l","l","l","s","s","s","s","u","u","u","u","d","d","d","d","g","g","g","b","b","m","m","c","c","p","p","f","f","h","h","v","v","w","w","y","y","k","k","j","x","q","z"};
    private int skipCounter = 4;

    public void populate(){
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

    public void printBoard() {
	for (int r = 0; r < 16; r++){
	    for (int c = 0; c < 16; c++){
		System.out.print(scrabbleBoard[r][c]);
	    }
	    System.out.print("|\n");
	}
    }

    public void hundred(String[] s){
	for(int i = 0; i< s.length; i++){
	    hundredPieces.add(englishLetters[i]);
	}
    }

    public void removeHundred(){
	hundredPieces.remove(2);
    }

    public String getHundred(){
	return (String)hundredPieces.get(2);
    }

    public void swap( int i, int j ) {
	String x = (String)hundredPieces.get(i);
	String y = (String)hundredPieces.get(j);
        hundredPieces.remove(i);
	hundredPieces.remove(j);
	hundredPieces.add(j, x);
        hundredPieces.add(i, y);
	
    }

    public void scramble() {
	for(int i = 0; i<hundredPieces.size(); i++){
	    swap(i, ((int)(Math.random() * hundredPieces.size()-1)));
	}

    }

    public void addLetter(int row, int col, int wordIndex, String word){
	scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex + 1);
    }

    public boolean existingColLetters(int row, int col, int i){
	return scrabbleBoard[row][col + i].equals("| ");
    }

    public boolean existingRowLetters(int row, int col, int i){
	return scrabbleBoard[row + i][col].equals("| ");
    }

    public boolean check1(int row, int col, int i, String word){
	return scrabbleBoard[row][col + i].substring(1).equals(word.substring(i,i+1));
    }

    public boolean check2(int row, int col, int i, String word){
	return scrabbleBoard[row + i][col].substring(1).equals(word.substring(i,i+1));
    }

    public boolean getOrCheckAboveOrBelow (int row, int col){
	return scrabbleBoard[row][col].equals("| ") || scrabbleBoard[row][col].equals("|" + col);
    }

    public boolean getOrCheckRightOrLeft(int row, int col){
	return scrabbleBoard[row][col].equals("| ") || scrabbleBoard[row][col].equals("|" + row);
    }
    
    public String addUDRLLetter1(int row, int col, String localWord){
	return scrabbleBoard[row][col].substring(1) + localWord;
    }

    public String addUDRLLetter2(int row, int col){
	return scrabbleBoard[row][col].substring(1);
    }

    public String addLetter1(int row, int col){
	return scrabbleBoard[row][col].substring(1);
    }  
    
    public static void main (String[] args){
	Woo board = new Woo();
	
	board.populate();
	board.hundred(board.englishLetters);
	
       
	System.out.println("How many players will there be?");
	int playerNumber = Keyboard.readInt();
	System.out.println("Player number 1 what is your name?");
	String playerName = Keyboard.readWord();
	Player player1 = new Player(playerName, board);
	board.printBoard();
	player1.firstWord(board);
	board.printBoard();
	while(board.skipCounter != 0 && board.hundredPieces.size() != 0){
	    player1.input(board);
	    board.printBoard();
	}
    }
}