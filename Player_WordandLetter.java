import java.util.ArrayList;
import cs1.Keyboard;

public class Player extends Woo{
    private int points =0;
    private String name;
    private ArrayList currentPieces = new ArrayList();

    public Player(String n){
	name = n;
	firstPieces();
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

    public boolean letterChecker(word){
	boolean validLetters= true;
	for(int i = 0;i < word.length(); i ++){
	    int check = 0;
	    for(int t = 0; t < currentPieces.size(); t++){
		if( !(Character.toString(word.charAt(i)).equals((String)currentPieces.get(t))) ){
		    check ++;
		}
	    }
	    if (check >= 7){
		validLetters = false;
		break;
	    }
	}	
    }

    public void firstWord() {
	System.out.println("Please enter the word length");
	int wordLength = Keyboard.readInt();
	int wordIndex = 0;
	System.out.println("Please type 'right' to build your word right to left or 'up' to build your word up to down");
	String direction = Keyboard.readWord();
	System.out.println("Please type the word you want to input");
	String word = Keyboard.readWord();
	boolean validWord = Dictionary.wordChecker(word);
	if (validWord) {
	    int row = 7;
	    int col = 7;
	    if (direction.equals("right")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    col+=1;
		    wordIndex+=1;
		}
	    }
	    if (direction.equals("up")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    row+=1;
		    wordIndex+=1;
		}
	    }
	}
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    firstWord();
	}
    }
    

    public void input(){
	System.out.println(currentPieces);

	
	System.out.println("Please enter the word length");
	int wordLength = Keyboard.readInt();
	int wordIndex = 0;
	System.out.println("Please input the row index.");
	int row = Keyboard.readInt();
	System.out.println("Please input the column index.");
	int col = Keyboard.readInt(); //try-catch later?
	System.out.println("Please type 'right' to build your word right to left or 'down' to build your word up to down");
	String direction = Keyboard.readWord();
	System.out.println("Please type the word you want to input");
	String word = Keyboard.readWord();
	
	if (letterChecker(word) && Dictionary.wordChecker(word)) {
	    if (direction.equals("right")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    col+=1;
		    wordIndex+=1;
		}
		draw();
	    }
	    if (direction.equals("down")){
		while (wordIndex < wordLength){
		    scrabbleBoard[row][col] = "|" + word.substring(wordIndex, wordIndex+1);
		    row+=1;
		    wordIndex+=1;
	    }
		draw();
	}
	else{
	    System.out.println("Sorry, the word you input is not valid. Please type another word. \n ");
	    input();
	}
	}
    }
}
