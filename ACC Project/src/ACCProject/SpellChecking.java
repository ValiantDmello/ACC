package ACCProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class SpellChecking {
	// Read the words from the inverted index which already has words
	private static ArrayList<String> dictionaryCreation(HashMap<String, ArrayList<Integer>> invertedIndex) {
		ArrayList<String> arr = new ArrayList<String>();
		//iterating through each and every word and adding it into the dictionary so that we have the dictionary of words.
		Iterator iter = invertedIndex.entrySet().iterator();
		while (iter.hasNext()) {
			HashMap.Entry entry = (HashMap.Entry) iter.next();
			String key = (String) entry.getKey();
			arr.add(key);
		}
		return arr;
	}

	// calculating the edit distance of 
	public static int editDistance(String word1, String word2) {
		int wordLen1 = word1.length();
		int wordLen2 = word2.length();

		// taking array as extra length so that we can take all the values as array last
		// element will be length-1
		int[][] editDistance = new int[wordLen1 + 1][wordLen2 + 1];

		for (int i = 0; i <= wordLen1; i++) {
			editDistance[i][0] = i;
		}

		for (int j = 0; j <= wordLen2; j++) {
			editDistance[0][j] = j;
		}

		for (int i = 0; i < wordLen1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < wordLen2; j++) {
				char c2 = word2.charAt(j);

				// if last two chars equal
				if (c1 == c2) {
					// update edit distance value of length plus 1
					editDistance[i + 1][j + 1] = editDistance[i][j];
				} else {
					int replacing = editDistance[i][j] + 1;
					int insertion = editDistance[i][j + 1] + 1;
					int deletion = editDistance[i + 1][j] + 1;

					int min = replacing > insertion ? insertion : replacing;
					min = deletion > min ? min : deletion;
					editDistance[i + 1][j + 1] = min;
				}
			}
		}

		return editDistance[wordLen1][wordLen2];
	}

	public static ArrayList<String> wordCorrectionSuggestion(String word,
			HashMap<String, ArrayList<Integer>> invertedIndex) {
		ArrayList<String> wordCorrector = new ArrayList<String>();

		try {
			ArrayList<String> temp = new ArrayList<String>();
			int i = 0;
			int distance = 0;
			//Storing the dictionary of words as array list.
			temp = dictionaryCreation(invertedIndex);

			if (!temp.contains(word)) {

				for (i = 0; i < temp.size(); i++) {
					// Finding the difference in distance between the words
					distance = SpellChecking.editDistance(word, temp.get(i));
					// Adding a alternate word if the distance is equal to 1
					if (distance == 1) {
						wordCorrector.add(temp.get(i));
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//returning word corrector after correcting it
		return wordCorrector;

	}
}
