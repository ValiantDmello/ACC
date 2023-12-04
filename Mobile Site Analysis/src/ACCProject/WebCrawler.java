package ACCProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


//Reference: https://www.youtube.com/watch?v=wrFXBV4MwvI&ab_channel=CodingWithTim
public class WebCrawler {
	public static HashSet<String> webCrawler(String url, int maxDepth, String regex) {
		HashSet<String> visitedPages = new HashSet<>();	//Set to store all pages links
		webCrawl(0, url, visitedPages, maxDepth, regex);	// Start web crawl with initial depth 0
		return visitedPages;		
	}
	
	public static Document request(String url, HashSet<String> visitedPages) {
		try {
			Connection con = Jsoup.connect(url);	//connect to url
			Document doc = con.get();
			
			if (con.response().statusCode()==200){	//if succeful response
				System.out.println("link: "+ url);	//use link 
				//System.out.println(doc.title());
				visitedPages.add(url);
				
				return doc;
			}else {
				return null;
			}
			
		}catch(IOException e) {
			return null;
		}
	}
	
	public static void webCrawl(int depth, String url, HashSet<String> visitedPages, int maxDepth, String regex) {
		if(depth<=maxDepth) {
	        Pattern pattern = Pattern.compile(regex);	//regex to let crawl only in bestbuy website
	        Matcher matcher = pattern.matcher(url);	
	        if(matcher.find()) {
				Document doc = request(url, visitedPages);
				if(doc!=null) {
					for(Element link: doc.select("a[href]")) {
						String nextLink = link.absUrl("href");	
						if(!visitedPages.contains(nextLink)) {	//if page not already visited
							webCrawl(depth++, nextLink, visitedPages, maxDepth, regex);		//recursively crawl in each link with increasing depth
						}
					}
				}
	        }
		}
	}
}
