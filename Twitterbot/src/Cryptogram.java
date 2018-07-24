import java.util.ArrayList;

/**
 * Cryptogram uses capital characters only.
 * @author James Schlaudraff
 * @version 20180627
 *
 */
public class Cryptogram {

	private ArrayList<Character> letters = new ArrayList<>();
	private ArrayList<Character> alphabet = new ArrayList<>();
	private ArrayList<Character> lalphabet = new ArrayList<>();
	private ArrayList<Character> puzzle = new ArrayList<>();
	
	/**
	 * Constructor.
	 */
	public Cryptogram() {
		for(int i = 0; i < 26; i++) {
			letters.add((char) ('A' + i));
			alphabet.add((char) ('A' + i));
			lalphabet.add((char) ('a' + i));
		}
	}
	
	/**
	 * Get the answer key.
	 * @return a string of the answer key.
	 */
	public String getLetters() {
		String s = "";
		for(int i = 0; i < letters.size(); i++) {
			s += letters.get(i) + " ";
		}
		return s;
	}
	
	/**
	 * Replace each letter with a corresponding letter of the alphabet.
	 */
	public void scrambleLetters() {
		int rand;
		ArrayList<Character> other = new ArrayList<>();
		for(int i = 26; i > 0; i--) {	// Swap a letter for 
			do {	//Algorithm fails if rand = i.
				rand = (int) (i * Math.random());
			} while(rand == i);
			other.add(letters.get(rand));
			letters.remove(rand);
		}
		
		letters = other;
	}
	
	/**
	 * Take a new puzzle.
	 * @param string is the message to be scrambled.
	 * @return String of encrypted message.
	 */
	public void input(String string) {
		puzzle.clear();
		string.toUpperCase();
		char[] c = string.toCharArray();
		for(int i = 0; i < string.length(); i++) {	// input message into puzzle.
//			System.out.println(c[i]);
			puzzle.add(c[i]);
			if(alphabet.contains(c[i])) {	// encrypt the message.
//				System.out.println("stamp encrypt");
				puzzle.set(i, letters.get(alphabet.indexOf(c[i])));
			} else if(lalphabet.contains(c[i])) {
				puzzle.set(i, letters.get(lalphabet.indexOf(c[i])));
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < puzzle.size(); i++) {
			s += puzzle.get(i);
		}
		return s;
		
	}
	
	/**
	 * accept a message to encrypt.
	 * @param string is the message.
	 * @return the encrypted message.
	 */
	public String scramble(String string) {
		scrambleLetters();
		input(string);
		return toString();
	}
}
