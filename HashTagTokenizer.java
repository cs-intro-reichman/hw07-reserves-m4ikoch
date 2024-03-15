

public class HashTagTokenizer {

	 
	public static void main(String[] args) {

		String hashTag = args[0];
	/*	/// option to throw exeption in case we need to validate input.

		if (hashTag.charAt(0) != '#') 
			throw new IllegalArgumentException(hashTag + "The provided string is not a valid hashtag. It must start with '#'.");
	 */
			String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag.toLowerCase().substring(1), dictionary);

	}
	
	//this function creates an array the contains the dictionary txt file. 
	//array[i] = word in the i line of the dictionary.
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		
		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) 
			dictionary[i] = in.readLine();

		return dictionary;
	}

	//this function checks if a given word exists in our dictionary.
	public static boolean existInDictionary(String word, String []dictionary) {

		for (int i = 0; i< dictionary.length; i++) 
			if (word.equals(dictionary[i])) return true;
		
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		// Recursive case
        for (int i = 1; i <= N; i++) {
			boolean isWord = existInDictionary(hashtag.substring(0, i), dictionary);
			if(isWord) {
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i), dictionary);
				return;
			}
        }
    }

}