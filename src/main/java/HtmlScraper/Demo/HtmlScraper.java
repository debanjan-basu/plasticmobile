package HtmlScraper.Demo;



import HtmlScrapper.Common.PropertyLoader;

/**
 * Toronto classifieds Scraper 1.0!
 *
 */


//TODO test cached search manage
public class HtmlScraper 
{
    public static void main( String[] args )
    {     
    		try {
	        	if (Boolean.parseBoolean(PropertyLoader.getProperty("enable.cache", "")) == true) {
	        		CachedSearchManager sm = new CachedSearchManager(args[0]);
	        		sm.executeSearch(null).getResults();
	        		
	        	}
	        	else {
	        		SearchManager sm = new SearchManager(args[0]);
	        		String json = sm.executeSearch(null).getResults();
	        		System.out.println(json);
	        	}
	        }catch(RuntimeException e) {
	        	System.out.println(e.getMessage());
	        }
    }
}