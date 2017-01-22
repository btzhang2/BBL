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

    public String getName() {
	return name;
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
	String tempWord = "";
	String wordHolder = word;
        while (wordHolder.length() > 0) {
	    if (existingLs.size() == 0) {
		tempWord = word;
		wordHolder = "";
	    }
	    else {
		for (int i = 0; i < existingLs.size(); i++) {
		    if (wordHolder.substring(0,1).equals(existingLs.get(i))) {
			wordHolder = wordHolder.substring(1);
			break;
		    }
		    else {
			tempWord += wordHolder.substring(0,1);
			wordHolder = wordHolder.substring(1);
		    }
		}
	    }
	} 
        while (tempWord.length() > 0) {
	    for (int i = 0; i < currentPieces.size(); i++) {
		if (tempWord.substring(0,1).equals(currentPieces.get(i))) {
		    currentPieces.remove(i);
		    tempWord = tempWord.substring(1);
		    break;
		}
	    }
	}
	for (int i = currentPieces.size(); i < 7; i++) {
	    if (hundredPieces.size() > 0) {
		super.scramble();
		String s = (String)super.hundredPieces.get(2);
		super.hundredPieces.remove(2);
		currentPieces.add(i,s);
	    }
	}
    }

    //creates an ArrayList of existing letters on the board
    //that will be used in the word, if valid
    public void existingLetters(String inputWord) {
	for (int i = 0; i < existingLs.size(); i++) {
	    existingLs.remove(i);
	}
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

	//asks if the player wants to skip
	System.out.println("Do you want to skip your turn? Type 'y' to skip and 'n' for no");
	String skip = Keyboard.readWord();
	if (skip.equals("y")) {
	    skipCounter -= 1;
	    return;
	}

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

	//asks if the player wants to skip
	System.out.println("Do you want to skip your turn? Type 'y' to skip and 'n' for no");
	String skip = Keyboard.readWord();
	if (skip.equals("y")) {
	    skipCounter -= 1;
	    return;
	}

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

		    //check if local words are valid as letter are inputted
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
	int fixedRow = row;
	int tempRow = row;
	int fixedCol = col;
	int tempCol = col;
	int points = 0;
	if (direction.equals("r")){
	    tempCol-=inputWord.length();
	    fixedCol-=inputWord.length();
	}
	if (direction.equals("u")){
	    tempRow-=inputWord.length();
	    fixedRow-=inputWord.length();
	}
	while (tempWord.length() > 0) {
	    String tempChar = tempWord.substring(0,1);
	    if (tempChar.equals("a") || tempChar.equals("e") || tempChar.equals("o") || tempChar.equals("n")
		|| tempChar.equals("r") || tempChar.equals("t") || tempChar.equals("l")
		|| tempChar.equals("s") || tempChar.equals("u") || tempChar.equals("i")) {
	        points+=boardPts(tempRow, tempCol, 1);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
		
	    }
	    else if (tempChar.equals("d") || tempChar.equals("g")) {
	        points+=boardPts(tempRow, tempCol, 2);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
	    }
	    else if (tempChar.equals("b") || tempChar.equals("c")
		     || tempChar.equals("m") || tempChar.equals("p")) {
	        points+=boardPts(tempRow, tempCol, 3);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
	    }
	    else if (tempChar.equals("f") || tempChar.equals("h") || tempChar.equals("v")
		     || tempChar.equals("w") || tempChar.equals("y")) {
	        points+=boardPts(tempRow, tempCol, 4);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
	    }
	    else if (tempChar.equals("k")) {
	        points+=boardPts(tempRow, tempCol, 5);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
	    }
	    else if (tempChar.equals("j") || tempChar.equals("x")) {
	        points+=boardPts(tempRow, tempCol, 8);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
	    }
	    else if (tempChar.equals("q") || tempChar.equals("z")) {
	        points+=boardPts(tempRow, tempCol, 10);
		System.out.println("current Points " + points);
		System.out.println("tempRow: " + tempRow);
		System.out.println("tempCol: " + tempCol);
	    }	
	    tempWord = tempWord.substring(1);
	    if (direction.equals("r")){
		tempCol+=1;
	    }
	    else if (direction.equals("u")){
		tempRow+=1;
	    }
	}
	if (direction.equals("r")){
	    tempCol-=inputWord.length();
	}
	if (direction.equals("u")){
	    tempRow-=inputWord.length();
	}
	points=boardPts1(tempRow, tempCol, fixedRow, fixedCol, points, inputWord);
	return points;
    }

    public int boardPts(int tempRow, int tempCol, int oPoints){
	    if (direction.equals("r")){
		    if (tempRow == 1 || tempRow == 15){
			if (tempCol == 4 || tempCol == 12){
			    return (2*oPoints);
			}
		    }
		    else if (tempRow == 2 || tempRow == 14){
			if (tempCol == 6 || tempCol == 10){
			    return (3*oPoints);
			}
		    }
		    else if (tempRow == 3 || tempRow == 13){
			if (tempCol == 7 || tempCol == 9){
			    return (2*oPoints);
			}
		    }
		    else if (tempRow == 4 || tempRow == 12){
			if (tempCol == 8){
			    return (2*oPoints);
			}
		    }
		    else if (tempRow == 6 || tempRow == 10){
			if (tempCol == 2 || tempCol == 6 || tempCol == 10 || tempCol == 14){
			    return (3*oPoints);
			}
		    }
		    else if (tempRow == 7 || tempRow == 9){
			if (tempCol == 3 || tempCol == 7 || tempCol == 9 || tempCol == 13){
			    return (2*oPoints);
			}
		    }
		    else if (tempRow == 8){
			if (tempCol == 4 || tempCol == 12){
			    return (2*oPoints);
			}
		    }
	    }
	    if (direction.equals("u")){
		    if (tempCol == 1 || tempCol == 15){
			if (tempRow == 4 || tempRow == 12){
			   return (2*oPoints);
			}
		    }
		    else if (tempCol == 2 || tempCol == 14){
			if (tempRow == 6 || tempRow == 10){
			    return(3*oPoints);
			}
		    }
		    else if (tempCol == 3 || tempCol == 13){
			if (tempRow == 7 || tempRow == 9){
			    points+=(2*oPoints);
			}
		    }
		    else if (tempCol == 4 || tempCol == 12){
			if (tempRow == 8){
			    return (2*oPoints);
			}
		    }
		    else if (tempCol == 6 || tempCol == 10){
			if (tempRow == 2 || tempRow == 6 || tempRow == 10 || tempRow == 14){
			    return (3*oPoints);
			}
		    }
		    else if (tempCol == 7 || tempCol == 9){
			if (tempRow == 3 || tempRow == 7 || tempRow == 9 || tempRow == 13){
			    return (2*oPoints);
			}
		    }
		    else if (tempCol == 8){
			if (tempRow == 4 || tempRow == 12){
			    return (2*oPoints);
			}
		    }
	    }
	    return oPoints;
    }

    public int boardPts1(int tempRow, int tempCol, int fixedRow, int fixedCol, int points, String inputWord){
	if (direction.equals("r")){
	    if (tempRow == 1 || tempRow == 15){
		while (tempCol < fixedCol + inputWord.length()){
		    if (tempCol == 1 || tempCol == 8 || tempCol == 15){
			return (3 * points);
		    }
		    tempCol+=1;
		}
	    }
	    else if (tempRow == 2 || tempRow == 14){
		while (tempCol < fixedCol + inputWord.length()){
		    if (tempCol == 2 || tempCol == 14){
			return (2 * points);
		    }
		    tempCol+=1;
		}
	    }
	    else if (tempRow == 3 || tempRow == 13){
		while (tempCol < fixedCol + inputWord.length()){
		    if (tempCol == 3 || tempCol == 13){
			return (2 * points);
		    }
		    tempCol+=1;
		}
	    }
	    else if (tempRow == 4 || tempRow == 12){
		while (tempCol < fixedCol + inputWord.length()){
		    if (tempCol == 4 || tempCol == 12){
			return (2 * points);
		    }
		    tempCol+=1;
		}
	    }
	    else if (tempRow == 5 || tempRow == 11){
		while (tempCol < fixedCol + inputWord.length()){
		    if (tempCol == 5 || tempCol == 11){
			return (2 * points);
		    }
		    tempCol+=1;
		}
	    }
	    else if (tempRow == 8){
		while (tempCol < fixedCol + inputWord.length()){
		    if (tempCol == 1 || tempCol == 15){
			return (3 * points);
		    }
		    else if (tempCol == 8){
			return (2 * points);
		    }
		    tempCol+=1;
		}
	    }
	}
	if (direction.equals("u")){
	    if (tempCol == 1 || tempCol == 15){
		while (tempRow < fixedRow + inputWord.length()){
		    if (tempRow == 1 || tempRow == 8 || tempRow == 15){
			return (3 * points);
		    }
		    tempRow+=1;
		}
	    }
	    else if (tempCol == 2 || tempCol == 14){
		while (tempRow < fixedRow + inputWord.length()){
		    if (tempRow == 2 || tempRow == 14){
			return (2 * points);
		    }
		    tempRow+=1;
		}
	    }
	    else if (tempCol == 3 || tempCol == 13){
		while (tempRow < fixedRow + inputWord.length()){
		    if (tempRow == 3 || tempRow == 13){
			return (2 * points);
		    }
		    tempRow+=1;
		}
	    }
	    else if (tempCol == 4 || tempCol == 12){
		while (tempRow < fixedRow + inputWord.length()){
		    if (tempRow == 4 || tempRow == 12){
			return (2 * points);
		    }
		    tempRow+=1;
		}
	    }
	    else if (tempCol == 5 || tempCol == 11){
		while (tempRow < fixedRow + inputWord.length()){
		    if (tempRow == 5 || tempRow == 11){
			return (2 * points);
		    }
		    tempRow+=1;
		}
	    }
	    else if (tempCol == 8){
		while (tempRow < fixedRow + inputWord.length()){
		    if (tempRow == 1 || tempRow == 15){
			return (3 * points);
		    }
		    else if (tempRow == 8){
			return (2 * points);
		    }
		    tempRow+=1;
		}
	    }
	}
	return points;
    }

    //adds points to player
    public void pointsAdd() {
	if (validWord) {
	    points += pointsCalculator(word);
	    word = "";
	}
    }

    //displays points
    public int getPoints() {
	return points;
    }
    
}
