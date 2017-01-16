import cs1.Keyboard;

public class Woo{

    protected  String[][] scrabbleBoard;

    public Woo(){
	scrabbleBoard = new String[16][16];
	populate();
    }

    public void populate(){
	for (int row = 0; row < 10; row++) {
	    scrabbleBoard[row][0] = Integer.toString(row);
	}
	for (int row = 10; row < 16; row++) {
	    scrabbleBoard[row][0] = Integer.toString(row).substring(1);
	}
	for (int col = 0; col < 10; col++) {
	    scrabbleBoard[0][col] = Integer.toString(col) + "|";
	}
	for (int col = 10; col < 16; col++) {
	    scrabbleBoard[0][col] = Integer.toString(col).substring(1) + "|";
	    scrabbleBoard[0][15] = Integer.toString(15).substring(1);
	}
	for (int row = 1; row < 16; row++){
	    for (int col = 1; col < 16; col++){
		scrabbleBoard[row][col]="| ";
	    }
	}	
    }

    public void printBoard() {
	for (int row = 0; row < 16; row++){
	    for (int col = 0; col < 16; col++){
		System.out.print(scrabbleBoard[row][col]);
	    }
	    System.out.print("|\n");
	}
    }

    public void firstWord() {
	System.out.println("Please type 'r' to build your word right to left or 'u' to build your word up to down");
	String direction = Keyboard.readWord();
	System.out.println("Please type the word you want to input");
	String word = Keyboard.readWord();
	word = word.toLowerCase();
	int wordLength = word.length();
	int wordIndex = 0;
	boolean validWord = Dictionary.wordChecker(word);
	if (validWord) {
	    int row = 8;
	    int col = 8;
	    if (direction.equals("r")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    col+=1;
		    wordIndex+=1;
		}
	    }
	    else if (direction.equals("u")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    row+=1;
		    wordIndex+=1;
		}
	    }
	    else {
		System.out.println("Sorry, this direction is not valid.");
		firstWord();
	    }
	}
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    firstWord();
	}
    }
    
    public void input(){
	System.out.println("Please input the row index.");
	int row = Keyboard.readInt();
	while (row > 15 || row == 0) {
	    System.out.println("Sorry, this row index is not valid. Please input the row index.");
	    row = Keyboard.readInt();
	}
	System.out.println("Please input the column index.");
	int col = Keyboard.readInt();
	while (col > 15 || col == 0) {
	    System.out.println("Sorry, this column index is not valid. Please input the column index.");
	    col = Keyboard.readInt();
	}
        System.out.println("Please type 'r' to build your word right to left or 'u' to build your word up to down");
	String direction = Keyboard.readWord();
	System.out.println("Please type the word you want to input");
	String word = Keyboard.readWord();
	word = word.toLowerCase();
	int wordLength = word.length();
	while (direction.equals("r") && (wordLength + col) > 16) {
	    System.out.println("Sorry, this word does not fit on the board. Please type the word you want to input.");
	    word = Keyboard.readWord();
	    word = word.toLowerCase();
	    wordLength = word.length();
	}
	while (direction.equals("u") && (wordLength + row) > 16) {
	    System.out.println("Sorry, this word does not fit on the board. Please type the word you want to input.");
	    word = Keyboard.readWord();
	    word = word.toLowerCase();
	    wordLength = word.length();
	}
	if (direction.equals("r")) {
	    int numExistingLs = 0;
	    for (int i = 0; i < wordLength; i++) {
		if (!scrabbleBoard[row][col + i].equals("| ")) {
		    numExistingLs += 1;
		    if (!scrabbleBoard[row][col + i].substring(1).equals(word.substring(i,i+1))) {
			System.out.println("Sorry, that play is invalid.");
			input();
		    }
		}
	    }
	    if (numExistingLs == 0) {
		System.out.println("Sorry, that play is invalid.");
		input();
	    }
	}
	else if (direction.equals("u")) {
	    int numExistingLs = 0;
	    for (int i = 0; i < wordLength; i++) {
		if (!scrabbleBoard[row + i][col].equals("| ")) {
		    numExistingLs += 1;
		    if (!scrabbleBoard[row + i][col].substring(1).equals(word.substring(i,i+1))) {
			System.out.println("Sorry, that play is invalid.");
			input();
		    }
		}
	    }
	    if (numExistingLs == 0) {
		System.out.println("Sorry, that play is invalid.");
		input();
	    }
	}
	int wordIndex = 0;
	boolean validWord = Dictionary.wordChecker(word);
	if (validWord) {
	    if (direction.equals("r")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    col+=1;
		    wordIndex+=1;
		}
	    }
	    else if (direction.equals("u")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    row+=1;
		    wordIndex+=1;
		}
	    }
	    else {
		System.out.println("Sorry, this direction is not valid.");
		input();
	    }
	}
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    input();
	}
    }
    
    
    public static void main (String[] args){
	Woo scrabble = new Woo();
	scrabble.printBoard();
	scrabble.firstWord();
	scrabble.printBoard();
	scrabble.input();
	scrabble.printBoard();	
    }
}
