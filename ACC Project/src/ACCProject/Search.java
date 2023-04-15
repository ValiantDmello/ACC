package ACCProject;

import java.util.*;
public class Search {
	static Map<String, Integer> searchMap= new HashMap<String, Integer>();;

	//Takes the word for every iteration.
	public static void searchUpdate(String word) {

		//checks if word is not first in the list so that we directly add count to it.
		if(searchMap.keySet().size()>0) {
				// if the word is already present we increment the count.
				if (searchMap.containsKey(word)) {
						searchMap.put(word, searchMap.get(word)+1);
					}
					else {
						searchMap.put(word, 1);
					}
				}
		// if the word is first one we directly put the count as 1
			else {
				searchMap.put(word, 1);
			}
		}
			

	}



