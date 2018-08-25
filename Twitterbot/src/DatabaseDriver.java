/*
 * Word Puzzle Time Twitter Bot
 * July 2018
 * Andrea Moncada
 */
package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* DatabaseDriver populates Sqlite database using line-delimited text files passed into command line args[].
*/


public class DatabaseDriver {

	private static SQLiteConnection sqlite = new SQLiteConnection("jdbc:sqlite:", xx, xx);
	private static Anagram anagram = new Anagram();
	private static Cryptogram crypto = new Cryptogram();
	
	
	private DatabaseDriver() {
		//Private constructor to prevent instantiation
	}
	
	/**
	 * Input a file name and format by stripping file extension and adding spaces between words.
	 */
	private static String formatTheme(String fileName) {
		String s = fileName;
		s = s.replace(".txt", "");	//Strip file extension
		//s = s.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"); //Insert spaces before uppercase letters
		//s = s.replaceAll("_", " "); //Replace all underscores with spaces
		return s;
	}
	
	
	/**
	 * Reads all lines from given anagram file and inputs into database
	 * @param textFile
	 * @throws FileNotFoundException
	 */
	public static void inputAnagramFile(String type, File textFile) throws FileNotFoundException {
	String answer = "";
	String hint = formatTheme(textFile.getName());
	Scanner s = new Scanner(textFile);
		while(s.hasNextLine()) {
			answer = s.nextLine();
			sqlite.insertPuzzleRow(type, anagram.scramble(answer), answer, hint , 0, "");
		}	
	s.close();
	
	}
	
	/**
	 * Reads all lines from given cryptogram file and inputs into database
	 * @param textFile
	 * @throws FileNotFoundException
	 */
	public static void inputCryptoFile(String type, File textFile) throws FileNotFoundException {
	String answer = "";
	String hint = formatTheme(textFile.getName());
	Scanner s = new Scanner(textFile);
		while(s.hasNextLine()) {
			answer = s.nextLine();
			sqlite.insertPuzzleRow(type, crypto.scramble(answer), answer, hint , 0, "");
		}	
	s.close();
	
	}
	
	

	public static void main(String[] args) throws FileNotFoundException {
		//if (args[0] == "a") {
		String s = args[0];
			if (s.equals("a")) {
				for (int i = 1; i < args.length; i++) {
					inputAnagramFile("Anagram", new File ("./" + args[i]));
				}//end for loop	
				
			}
			else if (s.equals("c")) {
				for (int i = 1; i < args.length; i++) {
					inputCryptoFile("Cryptogram", new File ("./" + args[i]));
				}//end for loop	
			} else {
				System.out.println("Missing or Not valid args...");
			}
		
	}//end main
}//end DatabaseDriver class
