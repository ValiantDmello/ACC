package ACCProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

//This java file is for web scraping and html parsing, it save text files in allPages directory

public class webScrapMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Set "pageSet" to store all the websites link
		HashSet <String> pageSet= WebCrawler.webCrawler("https://www.bestbuy.ca/en-ca/category/best-buy-mobile/20006", 30, "https://www.bestbuy.ca/en-ca(\\w+)*");	
		System.out.println("total pages = "+pageSet.size());
			
		int i = 0;
		for(String url : pageSet) {	//iterate through all links
			String text = HTMLParser.htmlParser(url);	//Website content text String 
			BufferedWriter writer = new BufferedWriter(new FileWriter("pages//"+(i++)+".txt"));	//file too save text
			writer.write(url+" \n"+ text);	//add text to .txt file //add website link on first line and content after that
			writer.close();
		}
		
	}
}
