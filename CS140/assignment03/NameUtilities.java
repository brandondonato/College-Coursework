package assignment03;



public class NameUtilities{


	public static int countFirstCharacters(char ch, String[] names){
		int numOfStrings = -1;
		if(names != null){
			for(String name : names){
				if(name != null){
					if(name.toLowerCase().charAt(0) == Character.toLowerCase(ch)){
						numOfStrings += 1;
					} 
				}
			}
		}
		return numOfStrings;
	}
	

	public static double averageLength(String[] names){
		double numOfLetters = 0.0;
		double numOfWords = 0.0;
		double average = -1.0;
		if(names != null){
			for(String name : names){
				if(name != null){
					numOfLetters += name.length();
					numOfWords += 1.0;
				}
			}
			average = (numOfLetters/numOfWords);
		}
		return average;
	}

	public static int countSyllables(String text) {
		int count = 0;
		int end = text.length() - 1;
		if (end < 0) { 
			return 0; 
		} // The empty string has no syllables

		// An e at the end of the word doesn't count as a vowel
		char ch = text.charAt(end);
		if (ch == 'e' || ch == 'E') {
			end--; 
		}

		boolean insideVowelGroup = false;
		for (int i = 0; i <= end; i++) {
			ch = text.charAt(i);
			String vowels = "aeiouyAEIOUY";
			if (vowels.indexOf(ch) >= 0) {
				// ch is a vowel
				if (!insideVowelGroup) {
					// Start of new vowel group
					count++; 
					insideVowelGroup = true;
				}
			} else {
				insideVowelGroup = false;
			}
		}

		// Every word has at least one syllable
		if (count == 0) {
			count = 1;
		}

		return count;      
	}

	public static double averageNumberOfSyllables(String[] names){
		double numOfSyllables = 0.0;
		double numOfWords = 0.0;
		double average = -1.0;
		if(names != null){
			for(String name : names){
				if(name != null){
					numOfSyllables += countSyllables(name);
					numOfWords += 1;
				}
			}
			average = (numOfSyllables/numOfWords);
		}
		return average;
	}
}