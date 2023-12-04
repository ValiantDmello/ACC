package ACCProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

//Refrence: https://www.youtube.com/watch?v=giiaIofn31A&ab_channel=MichaelMuinos

class Node{
	public char c;	//node character
	public boolean isWord;	//to check if word completes at this node
	public SortedMap<Character,Node> children;	//SortedMap of character and nodes for the children of this node
	
	public Node(char c) {	//constructor  for Node
		this.c = c;
		isWord = false;
		children = new TreeMap<>();	
	}
}

public class Trie {	
	private static Node root;	//root node for Trie
	
	//Trie Constructor
	public Trie() {		
		root = new Node('\0');	//root node empty node
	}
	
	//method to add node to trie
	public void add(String word) {		
		Node curr = root;	//assign current node to root node
		for(int i=0; i<word.length(); i++) {	//iterate throught each character of word
			char c = word.charAt(i);
			if (curr.children.get(c)==null) {	//check if node for char c exists
				curr.children.put(c, new Node(c));	//create node if doesnt exit
			}
			curr = curr.children.get(c);	//travel to next child of the char of word	
		}
		curr.isWord = true;	
	}
	
	//method to return same string if word exists in trie, return completed word if trie finds one, else return empty string
	public String checkTrie(String word) {
		Node node = getNode(word);	//get node where the word end
		if(node!=null && node.isWord) {	//word found in trie
			return word;
		}
		if(node!=null) {	//incomplete word found in trie
			String s = getWord(node);	//get complete word
			return word+s;	//return complete word
		}
		return "";	//if trie can find the input string
	}
	
	//return node of word'd last char
	public static Node getNode(String word) {
		Node curr = root;	//current node to root node
		for(int i=0; i<word.length(); i++) {	
			char c = word.charAt(i);		//iterate through characters of word
			if(!curr.children.containsKey(c)) {	//if char not in children node then return null
				return null;
			}
			curr = curr.children.get(c);	//travel to child corresponding to character of word
		}		
		return curr;	//return node where traversal ended
	}
	
	//method to return string continue from incomplete word
	public static String getWord(Node curr) {
		String s = "";	
		while(!curr.isWord) {	//loop until word is found 
			char key = curr.children.firstKey();	//get char of first child
			s = s+key;	//at that char to string s
			curr = curr.children.get(key);	//travel to child of first node 
		}
		return s;	//return complete word
	}
}
