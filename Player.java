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
    private ArrayList existingLs = new ArrayList(); //existing letters on the board that are in word

    public Player(){
	firstPieces();
    }

    public void setName(String Name){
	name = Name;
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
	//for(int i = 0; i < 7; i++){
	//    currentPieces.remove(0);
	//}
	//firstPieces();
	String tempWord = "";
	String wordHolder = word;
	System.out.println(existingLs); //diagnostics
        while (wordHolder.length() > 0) {
	    if (existingLs.size() == 0) {
		tempWord = word;
		wordHolder = "";
	    }
	    for (int i = 0; i < existingLs.size(); i++) {
		if (wordHolder.substring(0,1).equals(existingLs.get(i))) {
		    wordHolder = wordHolder.substring(1);
		}
		else {
		    tempWord += wordHolder.substring(0,1);
		    wordHolder = wordHolder.substring(1);
		}
	    }
	} //this part works
	System.out.println(tempWord); //diagnostics
        while (tempWord.length() > 0) {
	    for (int i = 0; i < currentPieces.size(); i++) {
		if (tempWord.substring(0,1).equals(currentPieces.get(i))) {
		    //System.out.println(tempWord); //diagnostics
		    currentPieces.remove(i);
		    tempWord = tempWord.substring(1);
		    continue;
		}
	    }
	} //works sometimes?????????????????
	for (int i = currentPieces.size(); i < 7; i++) {
	    super.scramble();
	    String s = (String)super.hundredPieces.get(2);
	    super.hundredPieces.remove(2);
	    currentPieces.add(i,s);
	}
    }

    //creates an ArrayList of existing letters on the board
    //that will be used in the word, if valid
    public void existingLetters(String inputWord) {
	if (direction.equals("r")) {
	    for (int i = 0; i < inputWord.length(); i++) {
		if (!scrabbleBoard[row][col+i].equals("| ")) {
		    existingLs.add(scrabbleBoard[row][col+i].substring(1));
		}
	    }
	}
	else if (direction.equals("u")) {
	    for (int i = 0; i < inputWord.length(); i++) {
		if (!scrabbleBoard[row+i][col].equals("| ")) {
		    existingLs.add(scrabbleBoard[row+i][col].substring(1));
		}
	    }
	}
    }
    
    public boolean letterChecker(String inputWord){
	existingLetters(inputWord);
	boolean validLetters= true;
	for(int i = 0;i < word.length(); i ++){
	    int check = 0;
	    for(int t = 0; t < currentPieces.size(); t++){
		if( !(Character.toString(inputWord.charAt(i)).equals((String)currentPieces.get(t))) ){
		    check ++;
		}
	    }
	    for (int n = 0; n < existingLs.size(); n++) {
		if (! Character.toString(inputWord.charAt(i)).equals((String)existingLs.get(n))) {
		    check++;
		}
	    }
	    if (check >= currentPieces.size() + existingLs.size()){
		validLetters = false;
		break;
	    }
	}
	return validLetters;
    }

    //for the first word that is inputted
    public void firstWord() {
	System.out.println(currentPieces);

	row = 8;
	col = 8;

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
	if (validWord && letterChecker(word)) {
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
	    return;
	}
	draw();
    }

    //helper methods to check neighboring words are valid
    
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

    public String addLetter(int row, int col){
	return scrabbleBoard[row][col].substring(1);
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
		    if (!getOrCheckAboveOrBelow(row-1, col)){
			    while (!getOrCheckAboveOrBelow((tempRow1)-1, col)){
				localWord = addUDRLLetter1((tempRow1)-1, col, localWord);
				(tempRow1)-=1;
			}
			localWord+= addLetter(row, col);
			if(!getOrCheckAboveOrBelow(row+1, col)){
			    while (!getOrCheckAboveOrBelow((tempRow2)+1, col)){
				localWord+= addUDRLLetter2((tempRow2)+1, col);
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
		    if (!getOrCheckRightOrLeft(row, col-1)){
			while (!getOrCheckRightOrLeft(row, (tempCol1)-1)){
			    localWord = addUDRLLetter1(row, (tempCol1)-1, localWord);
			    (tempCol1)-=1;
			}
			localWord+= addLetter(row, col);
			if (!getOrCheckRightOrLeft (row, col+1)){
			while (!getOrCheckRightOrLeft(row, (tempCol2)+1)){
			    localWord+= addUDRLLetter2(row, (tempCol2)+1);
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

    //assigns a numerical value to each letter and returns the sum of the numerical values in the parameter
    public int pointsCalculator(String inputWord) {
	String tempWord = inputWord;
	int points = 0;
	while (tempWord.length() > 0) {
	    String tempChar = tempWord.substring(0,1);
	    if (tempChar.equals("a") || tempChar.equals("e") || tempChar.equals("o") || tempChar.equals("n")
		|| tempChar.equals("r") || tempChar.equals("t") || tempChar.equals("l")
		|| tempChar.equals("s") || tempChar.equals("u") || tempChar.equals("i")) {
		points += 1;
	    }
	    else if (tempChar.equals("d") || tempChar.equals("g")) {
		points += 2;
	    }
	    else if (tempChar.equals("b") || tempChar.equals("c")
		     || tempChar.equals("m") || tempChar.equals("p")) {
		points += 3;
	    }
	    else if (tempChar.equals("f") || tempChar.equals("h") || tempChar.equals("v")
		     || tempChar.equals("w") || tempChar.equals("y")) {
		points += 4;
	    }
	    else if (tempChar.equals("k")) {
		points += 5;
	    }
	    else if (tempChar.equals("j") || tempChar.equals("x")) {
		points += 8;
	    }
	    else if (tempChar.equals("q") || tempChar.equals("z")) {
		points += 10;
	    }
	    tempWord = tempWord.substring(1);
	}
	return points;
    }

    //adds points to player
    public void pointsAdd() {
	if (validWord) {
	    points += pointsCalculator(word);
	}
    }

    //displays points
    public int getPoints() {
	return points;
    }
    
}
