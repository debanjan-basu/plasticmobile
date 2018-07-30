package HtmlScraper.Demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;



public class TestHtmlScraper {
	
	
	@Test
	public void TestTheWebsite() {
        
        Document doc = null;
		try {
			doc = Jsoup.connect("https://toronto.craigslist.ca/search/hhh?query=apartments").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String title = doc.title();
        assertEquals(title, "toronto housing \"apartments\" - craigslist");
        System.out.println(title);
        
        Element content = doc.getElementById("sortable-results");
        assertTrue(content!=null);
        
        Elements rows = content.getElementsByClass("result-row");
        for (Element row : rows) {
          //String linkHref = link.attr("href");
          //String linkText = link.text();
        	
        	//System.out.println(row.toString());
        	Elements child = row.getElementsByClass("result-info");
        	assertTrue(child!=null);
        	//System.out.println(child);
        	for( Element grc : child) {
        		Elements tags = grc.getElementsByTag("a");
        		//System.out.println(tags);
        		for(Element tag : tags) {
        				//System.out.println(tag.attr("class"));
        				System.out.println(tag.ownText());
        		}
        			
        		Elements elems = grc.getElementsByClass("result-date");
        		for(Element elem:elems) {
        			System.out.println(elem.ownText());
        		}
        		
        	}
        	//break;
        }
	}

}
