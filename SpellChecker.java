
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		
		//base case: string is empty
		if (word1.isEmpty()) return word2.length();
		if (word2.isEmpty()) return word1.length();
		
		//variables to use in the function.
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		String tail1 = tail(word1);
		String tail2 = tail(word2);
		/// end of variables

		if (word1.charAt(0) == word2.charAt(0)) // if first char is identical.
			return levenshtein(tail1, tail2);
		
		return 1 + Math.min(levenshtein(tail1, word2), Math.min(levenshtein(word1, tail2), levenshtein(tail1, tail2)));

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) 
			dictionary[i] = in.readLine();

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		
		int minDistance = levenshtein(word, dictionary[0]);
		String correction = dictionary[0];

		for (int i = 0; i < dictionary.length; i++) {
			int curDistance = levenshtein(word, dictionary[i]); 
			if (curDistance < minDistance) {
				minDistance = curDistance;
				correction = dictionary[i];
			}
		}

		if (minDistance <= threshold)
			return correction;
		
		return word;
		
	}

}