package ACCProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.util.Pair;

public class Main {
	public static void main(String[] args) throws IOException {
		String dir = "pages//";
		File path = new File(dir);
		String fileNames[] = path.list(); //array of string holding all txt files name
		
		//Hash Table that stores inverted Index
		HashMap<String,ArrayList<Integer>> invertedIndex = InvertedIndexing.invertedIndex(fileNames, dir);
		
		//Trie for word Copmletion
		Trie trie = new Trie();
		
		//Add all words to Trie
		Iterator iter = invertedIndex.entrySet().iterator();
		while (iter.hasNext()) {
			HashMap.Entry entry = (HashMap.Entry) iter.next();
			String key =  (String) entry.getKey();
			trie.add(key);
		}
		
		
		ArrayList<String> inputWords = new ArrayList<>();	//to store input words
		
		Search keywords = new Search();	//Object to store search frequency
		while(true) {
			Scanner sc= new Scanner(System.in);
			System.out.println("please enter the keyword to search : ");
			String input = sc.nextLine();	//input word
			input.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");	//convert to lower space and remove symbols
			System.out.println("inputs = "+input);	
			
			String s = WordCompletion.wordCompletion(trie, input);	//output from word completion
			if(s==input) {	//word exists in trie
				inputWords.add(s);
				keywords.searchUpdate(s);	//Add to search map
			}else if(s!="") {	//a completed word returned //input word was incomplete and trie found a complete word and replace input word
				inputWords.add(s);
				keywords.searchUpdate(s);	//Add to search map
			}else {	//Trie couldnt find a word
				ArrayList<String> wordSuggestions = SpellChecking.wordCorrectionSuggestion(input, invertedIndex);	//run word correction for word suggestions	
				System.out.println(wordSuggestions);
				System.out.println("You mean the above words? Let's try re-enter ");	//word not added in input list continue to renter word
				continue;
			}
			
			System.out.println("You want to search again. Please type N to exit and enter Y key continue");	//input continuation or exit
			if(sc.nextLine().toUpperCase().equals("Y"))
			{
				continue;
			} else {
				break;
			}
			
		}
		
		System.out.println("The input words are :\n"+inputWords);	//prints all input words
		String[] inputArr = new String[inputWords.size()];	//input word converted to String Array for input in pageRank
		int i=0;
		for(String t: inputWords) {	//Add input words to inputArr
			inputArr[i] = t;
			i++;
		}
		
		System.out.println("Search History : "+ keywords.searchMap);	//Show search history and frequency

		PriorityQueue<Pair<Integer,Integer>> pageRank =  PageRanking.pageRank(inputArr, invertedIndex);	//Implement page Rank t rank pages
		PageRanking.getTopNpages(10, pageRank, fileNames, dir);	//display top 10 pages
	}
}
