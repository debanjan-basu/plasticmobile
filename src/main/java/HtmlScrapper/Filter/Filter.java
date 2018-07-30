package HtmlScrapper.Filter;

import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class Filter {
	
	protected String results;
	protected String rows;

	Filter(String results, String rows){
		this.results = results;
		this.rows    = rows;
	}

	public abstract void filter(Document doc);
	public abstract String getFilteredJson() throws JsonProcessingException;

}
