package HtmlScrapper.Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
	
	static {
		new PropertyLoader();
	}
	private static Properties props; 
	
	private PropertyLoader() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		 
		String defaultConfigPath = rootPath + "deafult.properties";
		Properties defaultProps = new Properties();
		try {
			defaultProps.load(new FileInputStream(defaultConfigPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		String appConfigPath = rootPath + "application.properties";
		Properties appProps = new Properties(defaultProps);
		try {
			appProps.load(new FileInputStream(appConfigPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		props = appProps;
	}
	
	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
	

}
