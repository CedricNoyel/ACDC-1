package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class AccessProperties {

	private static AccessProperties SINGLETON = null;

	public static String LOCAL_REPOSITORY = "localRepository";
	public static String DEFAULT_AUTHOR = "defaultAuthor";

	ArrayList<String> result = new ArrayList<>();
	InputStream inputStream;
	FileOutputStream outputStream;

	public AccessProperties() {
		result = getPropValues();
	}

	public static AccessProperties getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new AccessProperties();
		}

		return SINGLETON;
	}

	public String getLocalRepository() {
		return result.get(0);
	}

	public String getDefaultAuthor() {
		return result.get(1);
	}

	public void updateLocalRepository(String value) {
		result.remove(0);
		result.add(0, value);
		this.updatePropertyValue(AccessProperties.LOCAL_REPOSITORY, value);
	}

	public void updateDefaultAuthor(String value) {
		this.updatePropertyValue(AccessProperties.DEFAULT_AUTHOR, value);
	}

	public ArrayList<String> getPropValues() {
		String propFileName = "./ressources/config.properties";
		Properties prop = new Properties();

		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			result.add(prop.getProperty(AccessProperties.LOCAL_REPOSITORY));
			result.add(prop.getProperty(AccessProperties.DEFAULT_AUTHOR));

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Exception: " + e);
			}
		}
		return result;
	}

	private void updatePropertyValue(String key, String value) {
		Properties props = new Properties();

		String propsFileName = "./src" + File.separator + "ressources" + File.separator + "config.properties";
		try {
					
			//first load old one:
			FileInputStream configStream = new FileInputStream(propsFileName);
			props.load(configStream);
			configStream.close();

			//modifies existing or adds new property
			props.setProperty(key, value);

			//save modified property file
			FileOutputStream output = new FileOutputStream(propsFileName);
			props.store(output, null);
			output.close();
			
			result = getPropValues();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}