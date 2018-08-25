/*
 * Word Puzzle Time Twitter Bot
 * July 2018
 * Andrea Moncada
 */
package View;
import java.io.IOException;
import model.SQLiteConnection;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Word Puzzle Time Twitterbot Main class
 * @author Andrea Moncada, August 2018
 *
 */
public class TweetMain {
	private static TwitterFactory tf = new TwitterFactory();
	private static Twitter twitter = tf.getInstance();
	private static SQLiteConnection sqlite = new SQLiteConnection("jdbc:sqlite:", xx, xx);
	
	
	private static String answerLink(String phpPath, int id) {
		return phpPath + "?id=" + id; 
	}
	
	
	public static void main(String[] args) throws TwitterException, InterruptedException, IOException {
		int id = 0;
		String puzzle = "";
		String type = "";
		String phpPath = xx;
		String table = xx;

		while(true){	        
			id = sqlite.selectRandomPuzzleId(table);
			puzzle = sqlite.getPuzzle(id, table);
			type = sqlite.getPuzzleType(id, table);
			
			//Tweet puzzle
	        twitter.updateStatus(type + " is: " + puzzle 
	        		+ " " + answerLink(phpPath, id));
	        //Flag as published
	        sqlite.flagAsPubilshed(id, table);
		        
		    //Timed loop for tweets  
		    Thread.sleep(302400000); //3.5*24*60*60*1000 = 302400000: Approx. twice a week
		}//end while
		
	}//end main

}//end TwitterMain
