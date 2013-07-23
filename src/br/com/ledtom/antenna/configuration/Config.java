package br.com.ledtom.antenna.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static final String PROPERTIES_FILE = "antenna.properties";
	
	public Config() {}
	
	public static Properties loadConfig(){		
		try {
			Properties config = new Properties();
			config.load(Config.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
			return config;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getApplicationName() {
		return loadConfig().getProperty("application.name");
	}
	
	public static String getApplicationVersion() {
		return loadConfig().getProperty("application.version");
	}
	
	public static String getLoginErrorMessage() {
		return loadConfig().getProperty("antenna.login.error.message");
	}
	
	public static String getVideoRepositoryPath() {
		return loadConfig().getProperty("antenna.videos.repository");
	}
}
