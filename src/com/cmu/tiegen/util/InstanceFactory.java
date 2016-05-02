package com.cmu.tiegen.util;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * 
 * @ Ruhua Wu
 * 
 */
public class InstanceFactory {

	private static InstanceFactory instance = new InstanceFactory();

	private InstanceFactory() {
	}

	public static InstanceFactory getInstance() {
		return instance;
	}

	private static Properties prop = null;
	static {
		prop = new Properties();
		try {
			prop.load(InstanceFactory.class.getClassLoader()
					.getResourceAsStream("instanceFactory.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public <T> T createInstance(Class<T> t) {
		String key = t.getSimpleName();
		String value = prop.getProperty(key);
		try {
			return (T) Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
