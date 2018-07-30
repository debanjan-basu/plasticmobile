package HtmlScrapper.URLBuilder;

import HtmlScrapper.Common.PropertyLoader;

public interface Url {
	
	//public final static String urlbase = "https://toronto.craigslist.ca/search/";
	
	
	public final static String urlbase = PropertyLoader.getProperty("search.base.url", "");
	String createUrl(String category);
}
