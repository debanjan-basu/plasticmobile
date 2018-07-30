package HtmlScraper.Demo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.JsonProcessingException;

import HtmlScrapper.Common.PropertyLoader;
import HtmlScrapper.Filter.ApartmentFilter;
import HtmlScrapper.Filter.Filter;
import HtmlScrapper.URLBuilder.ApartmentQueryBuilder;

public class SearchManager implements SearchResults, SearchExecuter {

	String searchCategory;
	String url;
	Document doc;
	Filter filter;

	public SearchManager(String searchCat) {
		this.searchCategory = searchCat;
		if (searchCat.equalsIgnoreCase(
				PropertyLoader.getProperty("housing.key", "apartments"))) {

			this.url = new ApartmentQueryBuilder().createUrl(searchCat);
			this.filter = new ApartmentFilter(
					PropertyLoader.getProperty("housing.result.tablename", ""), 
					PropertyLoader.getProperty("housing.result.rowname", ""));
		}
	}

	@Override
	public SearchManager executeSearch(String url) {

		try {
			this.doc = Jsoup.connect(this.url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.filter.filter(this.doc);

		return this;

	}

	@Override
	public String getResults() {
		try {
			return filter.getFilteredJson();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
