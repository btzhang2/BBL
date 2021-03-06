import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{

    protected static String[][] scrabbleBoard = new String[16][16];
    protected static String[][] mscrabbleBoard = new String[16][16];
    protected static ArrayList hundredPieces = new ArrayList(100);
    protected static String[] englishLetters= {"e","e","e","e","e","e","e","e","e","e","e","e","a","a","a","a","a","a","a","a","a","i","i","i","i","i","i","i","i","i","o","o","o","o","o","o","o","o","n","n","n","n","n","n","r","r","r","r","r","r","t","t","t","t","t","t","l","l","l","l","s","s","s","s","u","u","u","u","d","d","d","d","g","g","g","b","b","m","m","c","c","p","p","f","f","h","h","v","v","w","w","y","y","k","k","j","x","q","z"};
    protected static int skipCounter;
    protected static int playerNumber;

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

    //creates a board that displays the multiply areas
    public static void populateMultiplyBoard(){
	//show row numbers, for row numbers 10+, the ones digis of the tens are shown
	for (int r = 0; r < 10; r++) {
	    mscrabbleBoard[r][0] = Integer.toString(r);
	}
	for (int r = 10; r < 16; r++) {
	    mscrabbleBoard[r][0] = Integer.toString(r).substring(1);
	}

	//show col numbers, for col numbers 10+, the ones digis of the tens are shown	
	for (int c = 0; c < 10; c++) {
	    mscrabbleBoard[0][c] = Integer.toString(c) + "| ";
	}
	for (int c = 10; c < 16; c++) {
	    mscrabbleBoard[0][c] = Integer.toString(c).substring(1) + "| ";
	    mscrabbleBoard[0][15] = Integer.toString(15).substring(1);
	}
	for (int r = 1; r < 16; r++){
	    for (int c = 1; c < 16; c++){
		mscrabbleBoard[r][c]="|  ";
	    }
	}	
    }
    
    //prints the board, including all words and the row and column indices
    public static void printBoard() {
	for (int r = 0; r < 16; r++){
	    for (int c = 0; c < 16; c++){
		System.out.print(scrabbleBoard[r][c]);
	    }
	    System.out.print("|\n");
	}
    }

    public static void printBoardM() {
	multiplyBoard();
	for (int r = 0; r < 16; r++){
	    for (int c = 0; c < 16; c++){
		System.out.print(mscrabbleBoard[r][c]);
	    }
	    System.out.print("|\n");
	}
    }

    //Displays which tiles can multiply points
    public static void multiplyBoard(){
	mscrabbleBoard[1][15] = "|3w";
	mscrabbleBoard[2][14] = "|2w";
	mscrabbleBoard[3][13] = "|2w";
	mscrabbleBoard[4][12] = "|2w";
	mscrabbleBoard[5][11] = "|2w";
	mscrabbleBoard[6][10] = "|3L";
	mscrabbleBoard[7][9] = "|2L";
	mscrabbleBoard[8][8] = "|2w";
	mscrabbleBoard[9][7] = "|2L";
	mscrabbleBoard[10][6] = "|3L";
	mscrabbleBoard[11][5] = "|2w";
	mscrabbleBoard[12][4] = "|2w";
	mscrabbleBoard[13][3] = "|2w";
	mscrabbleBoard[14][2] = "|2w";
	mscrabbleBoard[15][1] = "|3w";
	
	mscrabbleBoard[15][15] = "|3w";
	mscrabbleBoard[14][14] = "|2w";
	mscrabbleBoard[13][13] = "|2w";
	mscrabbleBoard[12][12] = "|2w";
	mscrabbleBoard[11][11] = "|2w";
	mscrabbleBoard[10][10] = "|3L";
	mscrabbleBoard[9][9] = "|2L";
	mscrabbleBoard[8][8] = "|2w";
	mscrabbleBoard[7][7] = "|2L";
	mscrabbleBoard[6][6] = "|3L";
	mscrabbleBoard[5][5] = "|2w";
	mscrabbleBoard[4][4] = "|2w";
	mscrabbleBoard[3][3] = "|2w";
	mscrabbleBoard[2][2] = "|2w";
	mscrabbleBoard[1][1] = "|3w";

	mscrabbleBoard[15][8] = "|3w";
	mscrabbleBoard[1][8] = "|3w";
	mscrabbleBoard[15][4] = "|2L";
	mscrabbleBoard[15][12] = "|2L";
	mscrabbleBoard[1][4] = "|2L";
	mscrabbleBoard[1][12] = "|2L";
	mscrabbleBoard[14][6] = "|3w";
	mscrabbleBoard[14][10] = "|3w";
	mscrabbleBoard[2][6] = "|3w";
	mscrabbleBoard[2][10] = "|3w";
	mscrabbleBoard[13][7] = "|2L";
	mscrabbleBoard[13][9] = "|2L";
	mscrabbleBoard[3][7] = "|2L";
	mscrabbleBoard[3][9] = "|2L";
	mscrabbleBoard[12][8] = "|2L";
	mscrabbleBoard[4][8] = "|2L";
	
    }

    // add the English letters to hundredPieces
    public static void hundred(String[] s){
	for(int i = 0; i< s.length; i++){
	    hundredPieces.add(englishLetters[i]);
	}
    }

    // swap hundredPieces to mix up English letters in hundredPieces
    public static void swap( int i, int j ) {
	String x = (String)hundredPieces.get(i);
	String y = (String)hundredPieces.get(j);
	hundredPieces.remove(i);
	hundredPieces.remove(j);
	hundredPieces.add(j, x);
        hundredPieces.add(i, y);
	
    }
    
    // scramble the pieces in hundredPieces
    public static void scramble() {
	for(int i = 0; i<hundredPieces.size(); i++){
	    swap(i, ((int)(Math.random() * hundredPieces.size()-1)));
	}

    }   
       
    //main method
    public static void main (String[] args){
	populate();
	populateMultiplyBoard();
	hundred(englishLetters);

	System.out.println("How many players will there be?");
	playerNumber = Keyboard.readInt();
	while(playerNumber < 2 || playerNumber > 4){
	    System.out.println("There can only be 2-4 players");
	    playerNumber = Keyboard.readInt();
	}
	Player player1 = new Player();
	Player player2 = new Player();
	Player player3 = new Player();
	Player player4 = new Player();
	
	System.out.println("Player number 1 what is your name?");
	String playerName = Keyboard.readWord();
	player1.setName(playerName);
	System.out.println("Player number 2 what is your name?");
	String playerName2 = Keyboard.readWord();
        player2.setName(playerName2);
	skipCounter = 2;
	if(playerNumber >= 3){
	    System.out.println("Player number 3 what is your name?");
	    String playerName3 = Keyboard.readWord();
	    player3.setName(playerName3);
	    skipCounter = 3;
	}
	if(playerNumber >= 4){
	    System.out.println("Player number 4 what is your name?");
	    String playerName4 = Keyboard.readWord();
	    player4.setName(playerName4);
	    skipCounter = 4;
	}

	//player 1's first turn
	System.out.println("This displays the special tiles where point values can be multiplied. For example 2L multiplies the letter points by 2, while 3w multiplies the word points by 3.");
	printBoardM();
	printBoard();
	System.out.println(player1.getName() + " moves");
	player1.firstWord();
	player1.pointsAdd();
	printBoard();
	System.out.println(player1.getName() + " points: " + player1.getPoints());

	if (skipCounter == (playerNumber-1)) {
	    System.out.println(player2.getName() + " moves");
	    player2.firstWord();
	    player2.pointsAdd();
	    printBoard();
	    System.out.println(player2.getName() + " points: " + player2.getPoints());
	    if (playerNumber == 3) {
		System.out.println(player3.getName() + " moves");
		player3.firstWord();
		player3.pointsAdd();
		printBoard();
		System.out.println(player3.getName() + " points: " + player3.getPoints());
	    }
	    if (playerNumber == 4) {
		System.out.println(player4.getName() + " moves");
		player4.firstWord();
		player4.pointsAdd();
		printBoard();
		System.out.println(player4.getName() + " points: " + player4.getPoints());
	    }
	    System.out.println(player1.getName() + " moves");
	    player1.input();
	    player1.pointsAdd();
	    printBoard();
	    System.out.println(player1.getName() + " points: " + player1.getPoints());
	}
	if (skipCounter == (playerNumber-2) && skipCounter > 0) {
	    System.out.println(player3.getName() + " moves");
	    player3.firstWord();
	    player3.pointsAdd();
	    printBoard();
	    System.out.println(player3.getName() + " points: " + player3.getPoints());
	    if (playerNumber == 4) {
		System.out.println(player4.getName() + " moves");
		player4.firstWord();
		player4.pointsAdd();
		printBoard();
		System.out.println(player4.getName() + " points: " + player4.getPoints());
	    }
	    System.out.println(player1.getName() + " moves");
	    player1.input();
	    player1.pointsAdd();
	    printBoard();
	    System.out.println(player1.getName() + " points: " + player1.getPoints());
	}
	if (skipCounter == (playerNumber-3) && skipCounter > 0) {
	    System.out.println(player4.getName() + " moves");
	    player4.firstWord();
	    player4.pointsAdd();
	    printBoard();
	    System.out.println(player4.getName() + " points: " + player4.getPoints());
	    System.out.println(player1.getName() + " moves");
	    player1.input();
	    player1.pointsAdd();
	    printBoard();
	    System.out.println(player1.getName() + " points: " + player1.getPoints());
	}
	
	//if there are two players, play until both have skipped or the pieces have run out
	if (playerNumber == 2){
	    while(skipCounter > 0 && hundredPieces.size() > 0){
		System.out.println(player2.getName() + " moves");
		player2.input();
		player2.pointsAdd();
		printBoard();
		System.out.println(player2.getName() + " points: " + player2.getPoints());
		System.out.println(player1.getName() + " moves");
		player1.input();
		player1.pointsAdd();
		printBoard();
		System.out.println(player1.getName() + " points: " + player1.getPoints());
	    }
	    //displays ending and winner
	    System.out.println("The game is now over.");
	    System.out.println(player1.getName() + " points: " + player1.getPoints());
	    System.out.println(player2.getName() + " points: " + player2.getPoints());
	    if (player1.getPoints() > player2.getPoints()) {
		System.out.println(player1.getName() + " wins!");
	    }
	    else if (player2.getPoints() > player1.getPoints()) {
		System.out.println(player2.getName() + " wins!");
	    }
	    else {
		System.out.println("There was a tie.");
	    }
	}
	//if there are 3 players, play until all have skipped or the pieces have run out
	if (playerNumber == 3){
	    while(skipCounter > 0 && hundredPieces.size() > 0){
		System.out.println(player2.getName() + " moves");
		player2.input();
		player2.pointsAdd();
		printBoard();
		System.out.println(player2.getName() + " points: " + player2.getPoints());
		System.out.println(player3.getName() + " moves");
		player3.input();
		player3.pointsAdd();
		printBoard();
		System.out.println(player3.getName() + " points: " + player3.getPoints());
		System.out.println(player1.getName() + " moves");
		player1.input();
		player1.pointsAdd();
		printBoard();
		System.out.println(player1.getName() + " points: " + player1.getPoints());
	    }
	    //displays ending and winner
	    System.out.println("The game is now over.");
	    System.out.println(player1.getName() + " points: " + player1.getPoints());
	    System.out.println(player2.getName() + " points: " + player2.getPoints());
	    System.out.println(player3.getName() + " points: " + player3.getPoints());
	    if (player1.getPoints() > player2.getPoints() && player1.getPoints() > player3.getPoints()) {
		System.out.println(player1.getName() + " wins!");
	    }
	    else if (player2.getPoints() > player1.getPoints() && player2.getPoints() > player3.getPoints()) {
		System.out.println(player2.getName() + " wins!");
	    }
	    else if (player3.getPoints() > player1.getPoints() && player3.getPoints() > player2.getPoints()) {
		System.out.println(player3.getName() + " wins!");
	    }
	    else {
		System.out.println("There was a tie.");
	    }
	}
	//if there are 4 players, play until all have skipped or the pieces have run out
	if (playerNumber == 4){
	    while(skipCounter > 0 && hundredPieces.size() > 0){
		System.out.println(player2.getName() + " moves");
		player2.input();
		player2.pointsAdd();
		printBoard();
		System.out.println(player2.getName() + " points: " + player2.getPoints());
		System.out.println(player3.getName() + " moves");
		player3.input();
		player3.pointsAdd();
		printBoard();
		System.out.println(player3.getName() + " points: " + player3.getPoints());
		System.out.println(player4.getName() + " moves");
		player4.input();
		player4.pointsAdd();
		printBoard();
		System.out.println(player4.getName() + " points: " + player4.getPoints());
		System.out.println(player1.getName() + " moves");
		player1.input();
		player1.pointsAdd();
		printBoard();
		System.out.println(player1.getName() + " points: " + player1.getPoints());
	    }
	    //displays ending and winner
	    System.out.println("The game is now over.");
	    System.out.println(player1.getName() + " points: " + player1.getPoints());
	    System.out.println(player2.getName() + " points: " + player2.getPoints());
	    System.out.println(player3.getName() + " points: " + player3.getPoints());
	    System.out.println(player4.getName() + " points: " + player4.getPoints());
	    if (player1.getPoints() > player2.getPoints() && player1.getPoints() > player3.getPoints()
		&& player1.getPoints() > player4.getPoints()) {
		System.out.println(player1.getName() + " wins!");
	    }
	    else if (player2.getPoints() > player1.getPoints() && player2.getPoints() > player3.getPoints()
		     && player2.getPoints() > player4.getPoints()) {
		System.out.println(player2.getName() + " wins!");
	    }
	    else if (player3.getPoints() > player1.getPoints() && player3.getPoints() > player2.getPoints()
		     && player3.getPoints() > player4.getPoints()) {
		System.out.println(player3.getName() + " wins!");
	    }
	    else if (player4.getPoints() > player1.getPoints() && player4.getPoints() > player2.getPoints()
		     && player4.getPoints() > player3.getPoints()) {
		System.out.println(player4.getName() + " wins!");
	    }
	    else {
		System.out.println("There was a tie.");
	    }
	}
    }
}
