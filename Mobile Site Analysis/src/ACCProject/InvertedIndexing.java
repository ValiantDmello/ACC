package ACCProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class InvertedIndexing {
	public static HashMap<String,ArrayList<Integer>> invertedIndex(String fileNames[], String dir) throws IOException {
		HashMap<String,ArrayList<Integer>> invertedIndex = new HashMap<>();	//Hash table to store inverted indexes

		
		for(int i =0; i<fileNames.length; i++) {//iterate through all .txt files
            BufferedReader reader = new BufferedReader(new FileReader(dir+fileNames[i]));	//read file
            String text = "", line;
            String link = reader.readLine();	//website link that is stored in first line of .txt file
            while((line = reader.readLine())!=null) {	
            	text = text + line;			//add all text from file to text String
            }
            for(String word : text.split(" ")) {	//iterate throught each word
            	word = word.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");
            	if(word!="") {
	            	if(invertedIndex.containsKey(word)) {	//if word exist in map
	            		ArrayList<Integer> list = invertedIndex.get(word);
	            		int freq = list.get(i);	
	            		list.set(i, freq+1);	//increament frequency
	            		invertedIndex.put(word, list);
	            		
	            	}else {
	            		ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(fileNames.length, 0));
	            		list.set(i,1);	//in list make frequency of word for ith index of list 1
	            		invertedIndex.put(word, list);	//Add word with list
	            	}
	            }
            }          
		}
		return invertedIndex;
	}
}
