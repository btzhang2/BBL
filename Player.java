import java.util.ArrayList;
import cs1.Keyboard;

public class Player extends Woo{
    private int points =0;
    private String name;
    private ArrayList currentPieces = new ArrayList();
    private String direction;
    private String word;
    private int wordLength;
    private boolean validWord;
    private int row;
    private int col;

    public Player(String n){
	name = n;
	firstPieces();
	System.out.println(currentPieces);
    }

    public void firstPieces(){
	for(int i = 0; i<7; i++){
	    super.scramble();
	    String s = (String)super.hundredPieces.get(2);
	    super.hundredPieces.remove(2);
	    currentPieces.add(i,s);
	}
    }

    public void draw(){
	for(int i = 0; i < 7; i++){
	    currentPieces.remove(0);
	}
	firstPieces();
    }

    public boolean letterChecker(String inputWord){
	boolean validLetters= true;
	for(int i = 0;i < word.length(); i ++){
	    int check = 0;
	    for(int t = 0; t < currentPieces.size(); t++){
		if( !(Character.toString(inputWord.charAt(i)).equals((String)currentPieces.get(t))) ){
		    check ++;
		}
	    }
	    if (check >= 7){
		validLetters = false;
		break;
	    }
	}
	return validLetters;
    }

    //for the first word that is inputted
    public void firstWord() {

	//allow user to build from a certain direction
	System.out.println("Please type 'r' to build your word right to left or 'u' to build your word up to down");
        direction = Keyboard.readWord();

	
	System.out.println("Please type the word you want to input");
        word = Keyboard.readWord();
	word = word.toLowerCase();
        wordLength = word.length(); //calculate the length of the word
	int wordIndex = 0;
        validWord = Dictionary.wordChecker(word); //check if word is valid

	//place the letter of the first word at the center of the board (8,8)
	if (validWord) {
	    row = 8;
	    col = 8;

	    //place each letter in the slots (from right to left or up to down)
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

	    //check if direction user inputs is valid
	    else {
		System.out.println("Sorry, this direction is not valid.");
		firstWord();
		return;
	    }
	}

	//user inputs invalid word
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    firstWord();
	}
	draw();
    }
    
    //for every word after the first word that is input
    public void input(){
	System.out.println(currentPieces);

	//check if input for row is valid.
	System.out.println("Please input the row index.");
        row = Keyboard.readInt();
	while (row > 15 || row == 0) {
	    System.out.println("Sorry, this row index is not valid. Please input the row index.");
	    row = Keyboard.readInt();
	}

	//check if input for col is valid.
	System.out.println("Please input the column index.");
        col = Keyboard.readInt();
	while (col > 15 || col == 0) {
	    System.out.println("Sorry, this column index is not valid. Please input the column index.");
	    col = Keyboard.readInt();
	}

	// direction word is being built
        System.out.println("Please type 'r' to build your word right to left or 'u' to build your word up to down");
        direction = Keyboard.readWord();

	// figure out the word the user wants to place on the board and the length of the word
	System.out.println("Please type the word you want to input");
        word = Keyboard.readWord();
	word = word.toLowerCase();
        wordLength = word.length();

	// check if word is long enough to be placed on the board
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

	//check if the letters in the word you input can be built off of an exisiting word on the board
	if (direction.equals("r")) {
	    int numExistingLs = 0;
	    for (int i = 0; i < wordLength; i++) {
		if (!scrabbleBoard[row][col + i].equals("| ")) {
		    numExistingLs += 1;
		    if (!scrabbleBoard[row][col + i].substring(1).equals(word.substring(i,i+1))) {
			System.out.println("Sorry, that play is invalid.");
			input();
			return;
		    }
		}
	    }
	    if (numExistingLs == 0) {
		System.out.println("Sorry, that play is invalid.");
		input();
		return;
	    }
	}

	//check if the letters in the word you input can be built off of an exisiting word on the board
	else if (direction.equals("u")) {
	    int numExistingLs = 0;
	    for (int i = 0; i < wordLength; i++) {
		if (!scrabbleBoard[row + i][col].equals("| ")) {
		    numExistingLs += 1;

		    if (!scrabbleBoard[row + i][col].substring(1).equals(word.substring(i,i+1))) {
			System.out.println("Sorry, that play is invalid.");
			input();
			return;
		    }
		}
	    }
	    if (numExistingLs == 0) {
		System.out.println("Sorry, that play is invalid.");
		input();
		return;
	    }
	}
	
	int wordIndex = 0;
        validWord = Dictionary.wordChecker(word);

	//place letters into the board
	if (validWord && letterChecker(word)) {
	    if (direction.equals("r")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);

		    //check if local words are valid as letetr are inputted
		    String localWord = "";
		    int tempRow1 = row;
		    int tempRow2 = row;
		    if ((!scrabbleBoard[row-1][col].equals("| ")) || (!scrabbleBoard[row-1][col].equals("|" + col))){
			while (!(scrabbleBoard[(tempRow1)-1][col].equals("| ") || (scrabbleBoard[(tempRow1)-1][col].equals("|" + col)))){
			    localWord = scrabbleBoard[(tempRow1)-1][col].substring(1) + localWord;
			    (tempRow1)-=1;
			}
			localWord+=scrabbleBoard[row][col].substring(1);
			if(!scrabbleBoard[row+1][col].equals("| ")){
			    while (!scrabbleBoard[(tempRow2)+1][col].equals("| ")){
				localWord+= scrabbleBoard[(tempRow2)+1][col].substring(1);
				(tempRow2)+=1;
			    }
			}
		    }
		    if (Dictionary.wordChecker(localWord)){
			col+=1;
			wordIndex+=1;
		    }
		    else{
			System.out.println("Sorry, this word can not be placed here because doing so would create new words that are invalid");
			input();
			return;
		    }
		}

	    }
	    else if (direction.equals("u")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);

		    //check if local words are valid as letters are added.
		    String localWord = "";
		    int tempCol1 = col;
		    int tempCol2 = col;
		    if ((!scrabbleBoard[row][col-1].equals("| ")) || (!scrabbleBoard[row][col-1].equals("|" + row))){
			while (!(scrabbleBoard[row][(tempCol1)-1].equals("| ") || (scrabbleBoard[row][(tempCol1)-1].equals("|" + row)))){
			    localWord = scrabbleBoard[row][(tempCol1)-1].substring(1) + localWord;
			    (tempCol1)-=1;
			}
			localWord+=scrabbleBoard[row][col].substring(1);
			if (!scrabbleBoard[row][col+1].equals("| ")){
			    while (!scrabbleBoard[row][(tempCol2)+1].equals("| ")){
				    localWord+=scrabbleBoard[row][(tempCol2)+1].substring(1);
				    (tempCol2)+=1;
			    }
			}
		    }

		    if (Dictionary.wordChecker(localWord)){
			row+=1;
			wordIndex+=1;
		    }
		    else{
			System.out.println("Sorry, this word can not be placed here because doing so would create new words that are invalid");
			input();
			return;
		    }			
		}
	    }
	    else {
		System.out.println("Sorry, this direction is not valid.");
		input();
		return;
	    }
	}
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    input();
	    return;
	}
	draw();
    }
    
}
