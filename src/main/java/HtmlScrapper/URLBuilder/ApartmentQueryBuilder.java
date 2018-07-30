package HtmlScrapper.URLBuilder;

import HtmlScrapper.Common.PropertyLoader;

public class ApartmentQueryBuilder implements Url{

	

	public String createUrl(String category) {
		
		return new StringBuilder()
				.append(urlbase)
				.append(PropertyLoader.getProperty("housing.subject", ""))
				.append(category)
				.toString();
	}
	
	

}
