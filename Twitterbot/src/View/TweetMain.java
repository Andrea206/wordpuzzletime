/*
 * Word Puzzle Time Twitter Bot
 * July 2018
 */
package View;
import java.io.IOException;
import model.SQLiteConnection;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


/**
 * Driver for Word Puzzle Time Twitterbot
 * @author Andrea Moncada
 *
 */
public class TweetMain {
	private static TwitterFactory tf = new TwitterFactory();
	private static Twitter twitter = tf.getInstance();
	private static SQLiteConnection sqlite = new SQLiteConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/shire/sqlite/", "twBot.db");
	
	public static void main(String[] args) throws TwitterException, InterruptedException, IOException {
		int id = 0;
		String wordPuzzle = "";
		String theme = "";
		while(true){	        
			id = sqlite.selectRandomWord();
			wordPuzzle = sqlite.getShuffledWord(id);
			theme = sqlite.getTheme(id);
			
			//Tweet puzzle
	        twitter.updateStatus("Today's anagram is: " + wordPuzzle + "  Hint: " + theme);
	        sqlite.flagAsPubilshed(id);
		        
		    //Timed loop for tweets  
		    Thread.sleep(1*60*1000); //One minute delay for testing
		}//end while
		

	}//end main

}//end TwitterMain
