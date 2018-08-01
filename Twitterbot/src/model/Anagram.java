package model;

/**
 * @author James Schlaudraff
 * @version 20180724
 */
public class Anagram {

	
	/**
	 * Constructor.
	 */
	public Anagram() {
		//Empty constructor
	}
	
	/**
	 * Scramble the letters of the word.
	 */
	public String scrambleWord(String word) {
		char[] wordArray = word.toCharArray();
		int rand;
		char temp;
		for(int i = wordArray.length - 1; i > 0; i--) {
			do {
				rand = (int) ((i + 1) * Math.random());
			} while (rand > i);
				temp = wordArray[i - 1];
				wordArray[i - 1] = wordArray[rand];
				wordArray[rand] = temp;		
		}
		return String.valueOf(wordArray).toLowerCase();
	}


	public static void main(String[] args) {
		Anagram a = new Anagram();
		System.out.println(a.scrambleWord("Abcdef"));
		System.out.println(a.scrambleWord("Abcdef"));
		System.out.println(a.scrambleWord("Abcdef"));
		System.out.println(a.scrambleWord("Abcdef"));
	}
	
	
}
