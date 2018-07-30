package HtmlScrapper.Filter;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import HtmlScraper.Results.Apartment;

public class ApartmentFilter extends Filter {

	public ApartmentFilter(String results, String rows) {
		super(results, rows);
		
	}
	
	private Elements apartments = null;
	
	@Override
	public void filter(Document doc ) {

		Element content = doc.getElementById(this.results);
        this.apartments = content.getElementsByClass(this.rows);
	}


	//html dependent section of code
	private void getApartments(Elements apartments, ArrayList<Apartment> finalList) {
		for (Element e : apartments) {
			
			Elements child = e.getElementsByClass("result-info");
			Apartment apt = new Apartment();
			
			for( Element grc : child) {
        		Elements tags = grc.getElementsByTag("a");
        		//System.out.println(tags);
        		for(Element tag : tags) {
        				
        				if (tag.ownText() != null && !tag.ownText().isEmpty()) {
        					apt.setTitle(tag.ownText());
        				}
        		}
        			
        		Elements elems = grc.getElementsByClass("result-date");
        		for(Element elem:elems) {
        			apt.setDate(elem.ownText());
        		}	
        	}
			
			finalList.add(apt);
		}
	}


	@Override
	public String getFilteredJson() throws JsonProcessingException {
		
		ArrayList<Apartment> aparts = new ArrayList<Apartment>();
		this.getApartments(apartments, aparts);
		
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(aparts);
	}

}
