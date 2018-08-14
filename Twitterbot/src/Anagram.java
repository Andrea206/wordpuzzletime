import java.util.ArrayList;

/**
 * @author James Schlaudraff
 * @version 20180814
 */
public class Anagram {

//	private ArrayList<ArrayList<Character>> words = new ArrayList<>();
	private ArrayList<Character> letters = new ArrayList<>();
	private ArrayList<Character> alphabet = new ArrayList<>();
	
	/**
	 * Constructor.
	 */
	public Anagram() {
		for(int i = 0; i < 26; i++) {
			alphabet.add((char) ('A' + i));
		}
	}
	
	/**
	 * Get the letters
	 * @return the letters as a String.
	 */
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < letters.size(); i++) {
			s += letters.get(i);
		}
		return s;
	}
	
	/**
	 * Store a string into the words array.
	 * @param string
	 */
	public void newWord(String string) {
		ArrayList<Character> other = new ArrayList<>();
		char[] c = string.toCharArray();
		for(int i = 0; i < c.length; i++) {
			other.add(c[i]);
		}
		letters = other;
	}
	
	/**
	 * Scramble the letters of the word.
	 */
	public void scrambleList(ArrayList<Character> l) {
		int rand;
		char temp;
		for(int i = l.size() - 1; i > 0; i--) {
			do {
				rand = (int) ((i + 1) * Math.random());
			} while (rand > i);
			temp = l.get(i - 1);
			l.set(i - 1, l.get(rand));
			l.set(rand, temp);
		}
	}
	
	public void input(String string) {
		string = string.toUpperCase();
		char[] c = string.toCharArray();
		for(int i = 0; i < string.length(); i++) {
			letters.add(c[i]);
		}
		
		scrambleList(letters);
		
	}
	
	/**
	 * Accept a word and shuffle the letters to make an anagram.
	 * @param string is the message.
	 * @return the shuffled message.
	 */
	public String scramble(String string) {
		letters.clear();
		string = string.toUpperCase();
		input(string);
		return toString();
	}
}
