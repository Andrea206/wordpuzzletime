public class Test {

	public static void main(String[] args) {
		System.out.println("Anagram");
		Anagram ana = new Anagram();
		System.out.println(ana.scramble("callibrations"));
		System.out.println();

		System.out.println("Cryptogram");
		Cryptogram crypt = new Cryptogram();
		System.out.println(crypt.scramble("Can it wait for a bit? I'm in the middle of some calibrations."));
	}

}
