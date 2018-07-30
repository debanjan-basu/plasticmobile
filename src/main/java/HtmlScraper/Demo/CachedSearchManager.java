package HtmlScraper.Demo;

import java.time.Instant;
import java.util.HashMap;

import HtmlScrapper.Common.PropertyLoader;
import HtmlScrapper.Common.ScrapedData;

public class CachedSearchManager extends SearchManager implements SearchExecuter, SearchResults {

	private boolean updateCache = false;
	
	public CachedSearchManager(String searchCat) {
		super(searchCat);
		System.out.println(this.url);
	}

	private  static  HashMap<String, ScrapedData> cache = null;

	public static HashMap<String, ScrapedData> getCache() {
		
		if (null == cache) {
			cache = new HashMap<String, ScrapedData>();
		}
		
		return cache;
	}

	@Override
	public String getResults() {
		//System.out.println(this.updateCache);
		
		if (this.updateCache == true ) {
			
			String results = super.getResults();
			//System.out.println(results);
			
			
			ScrapedData sd = new ScrapedData();
			sd.setUrl(this.url);
			sd.getVal().setTimestamp(Instant.now().toEpochMilli());
			
			
			//System.out.println(results);
			sd.getVal().setVal(results);
			getCache().put(sd.getUrl(), sd);
			
			//System.out.println(results);
			return results;
		}
		else { //serve from cache
			return getCache().get(this.url).getVal().toString();
		}
	
	}

	@Override
	public CachedSearchManager executeSearch(String url) {
		if (getCache().containsKey(this.url)) {
			long now = Instant.now().toEpochMilli();
			
			ScrapedData sd = getCache().get(this.url);
			long then = sd.getVal().getTimestamp();
			if ( Math.abs(now - then) > 
				Integer.parseInt(PropertyLoader.getProperty("cache.timeout.seconds", "60000"))) {
				this.updateCache = true;
				super.executeSearch(this.url);
			}
		}
		else {
			System.out.println("Update cache");
			this.updateCache = true;
			super.executeSearch(this.url);
		}
		return this;
	}
}
