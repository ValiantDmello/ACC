package ACCProject;

public class WordCompletion {
	public static String wordCompletion(Trie trie, String word) {
		String s = trie.checkTrie(word);	//check for the word in Trie
		if(s==word) {	//Word found in Trie
			return word;	//return same word
		}
		if(s=="") {	//Word not found in Trie
			System.out.println("\"word\" "+word+" doesnt exist in dictionary");
			return "";	//return empty string
		}	//Incomplete word found in Trie
		System.out.println("\"word\" "+word+" doesnt exist in dictionary using \""+s+"\" instead");
		return s;	//return complete word
	}
}
