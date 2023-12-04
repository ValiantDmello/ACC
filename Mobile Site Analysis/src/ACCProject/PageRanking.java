package ACCProject;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import javafx.util.Pair;


//Need JavaFX library to run
public class PageRanking {
	public static PriorityQueue<Pair<Integer, Integer>> pageRank(String [] words, HashMap<String,ArrayList<Integer>> invertedIndex) {
		PriorityQueue<Pair<Integer, Integer>> pageRank = new PriorityQueue<>((a, b) -> b.getKey() - a.getKey());	//Maximum heap to store page ranks
		for(int i=0; i<invertedIndex.get(words[0]).size(); i++) {	//iterate through inverted Indexes for each page
			int score = 0;
			for(String word: words) {	
				word = word.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");
				score =  score + invertedIndex.get(word).get(i);	//update score		
			}
			pageRank.add(new Pair<Integer, Integer>(score, i));	//get score for each page and add to heap
		}
		return pageRank;
	}
	
	public static void getTopNpages(int n, PriorityQueue<Pair<Integer, Integer>> pageRank, String fileNames[], String dir) throws IOException {
		for(int i=0; i<n; i++) {	//iterate n times
			Pair<Integer, Integer> pair = pageRank.poll();	//get root and delete from heap
			int score = pair.getKey();	//score of the root node
			int fileIndex = pair.getValue();	//file index
			
			BufferedReader reader = new BufferedReader(new FileReader(dir+fileNames[fileIndex]));
			String link = reader.readLine();	//read link stored on first line of txt file
			System.out.println("Rank "+(i+1)+" of page : "+link+" score : "+score);
		}
	}

}
