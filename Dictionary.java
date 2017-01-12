import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary{

    public static boolean wordChecker(String s){
	try {
            BufferedReader in = new BufferedReader(new FileReader("words.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.indexOf(s) != -1) {
                    return true;
                }
            }
            in.close();
        } catch (IOException e) {
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordChecker("hello"));
	System.out.println(wordChecker("perpetual"));
	System.out.println(wordChecker("dog"));
	System.out.println(wordChecker("cat"));
	System.out.println(wordChecker("ourgdb"));
	System.out.println(wordChecker("blown"));
	System.out.println(wordChecker("lake"));
	System.out.println(wordChecker("ciursbdfub"));
    }
}
