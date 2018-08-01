package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Scanner;

public class DatabaseDriver {
	
	/**
	 * Local file path for text files. 
	 */
	private static String workingPath = "/eclipse-oxy/CodeProjects/WordPuzzleTime/src/";
	
	/**
	 * 
	 */
	private static SQLiteConnection sqlite = new SQLiteConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/shire/sqlite/", "twBot.db");

	
	/**
	 * 
	 */
	private static Anagram anagram = new Anagram();
	
	
	
	private DatabaseDriver() {
		//Empty constructor
	}
	
	
	
	
	/**
	 * Get working path for project
	 */
	public String getWorkingPath() {
		return workingPath;
	}
	
	/**
	 * Created String of absolute file path for a given file. 
	 * Local file path must be set in class fields.
	 * @param String fileName
	 * @return the String file path.
	 */
	public static String filePathString(String fileName) {
		String home = System.getProperty("user.home");
		return home + workingPath + fileName;
	}//end filePathString
	
	/**
	 * Input a file name and format by stripping file extension and adding spaces between words.
	 */
	private static String formatTheme(String fileName) {
		String s = fileName;
		s = s.replace(".txt", "");	//Strip file extension
		//s = s.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"); //Insert spaces before uppercase letters
		s = s.replaceAll("_", " "); //Replace all underscores with spaces
		return s;
	}
	
	
	/**
	 * Reads all lines from given file and inputs into ANAGRAM table of twBot.db
	 * @param textFile
	 * @throws FileNotFoundException
	 */
	public static void inputFileIntoAnagramTable(File textFile) throws FileNotFoundException {
	File words = textFile;
	String word;
	String fileName = textFile.getName();
	Scanner s = new Scanner(words);
		while(s.hasNextLine()) {
			word = s.nextLine();
			sqlite.insertAnagram(word, anagram.scrambleWord(word), formatTheme(fileName), 0, "");
		}	
	s.close();
	
	}
	
	

	public static void main(String[] args) throws FileNotFoundException {
//		File inputFile = new File(filePathString("US_States.txt"));
//		File inputFile2 = new File(filePathString("Fruits_And_Veggies.txt"));
//		inputFileIntoAnagramTable(inputFile);
//		inputFileIntoAnagramTable(inputFile2);
		
//		int id = sqlite.selectRandomWord();
//		System.out.println("Random word is " + id);
//		System.out.print(" " + sqlite.getWord(id));
//		System.out.print(" " + sqlite.getShuffledWord(id));
//		System.out.print(" " + sqlite.getTheme(id));
//		sqlite.flagAsPubilshed(id);
		

		

	}

}
