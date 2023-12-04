package ACCProject;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParser {
	public static String htmlParser(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();	// connect to url
		String text = doc.title().concat(doc.body().text());	//Extract content from site to String text
		return text;
	}	
}
