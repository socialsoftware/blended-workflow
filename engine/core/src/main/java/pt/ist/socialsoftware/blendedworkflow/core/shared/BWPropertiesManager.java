package pt.ist.socialsoftware.blendedworkflow.core.shared;

import java.io.IOException;
import java.util.Properties;

public class BWPropertiesManager {

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(BWPropertiesManager.class
					.getResourceAsStream("/build.properties"));

		} catch (IOException e) {
			throw new RuntimeException("Unable to load properties files.", e);
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static String getProperty(final String key) {
		System.out.println("XXXXXXXXXX" + properties.getProperty(key));
		return properties.getProperty(key);
	}

	public static boolean getBooleanProperty(final String key) {
		return Boolean.parseBoolean(properties.getProperty(key));
	}

	public static Integer getIntegerProperty(final String key) {
		return Integer.valueOf(properties.getProperty(key));
	}

	public static void setProperty(final String key, final String value) {
		properties.setProperty(key, value);
	}
}
