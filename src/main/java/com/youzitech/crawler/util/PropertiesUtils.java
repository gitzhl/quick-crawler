package com.youzitech.crawler.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 配置文件工具类
 *
 * @author heliang.zhu
 * @since 1.0, 2015-06-17
 */
public class PropertiesUtils {

	private static Logger logger = Logger.getLogger(PropertiesUtils.class);

	private static String[] PROPERTIES;

	private static Properties properties = null;

	private static String CONFIG_PATH = null;

	private static void init() {
		if (PROPERTIES == null) {
			PROPERTIES = new String[] { "httpProxool.properties","fileOperations.properties" };
		}
		properties = new Properties();
		try {
			if (StringUtils.isBlank(CONFIG_PATH)) {
				for (String str : PROPERTIES) {
					load(getResourceAsStream(str), properties);
				}
			} else {
				if (!StringUtils.endsWith(CONFIG_PATH, File.separator)) {
					CONFIG_PATH += File.separator;
				}
				for (String str : PROPERTIES) {
					load(FileUtils.openInputStream(new File(CONFIG_PATH + str)), properties);
				}
			}
			if (logger.isInfoEnabled()) {
				logger.info("config init path=" + CONFIG_PATH + " ,info=" + properties.toString());
			}
		} catch (IOException e) {
			logger.error("配置文件初始化失败！", e);
		}
	}

	static {
		init();
	}

	private static void load(InputStream inStream, Properties properties) throws IOException {
		properties.load(inStream);
		IOUtils.closeQuietly(inStream);
	}

	private PropertiesUtils() {
	}

	/**
	 * 获取MQ配置
	 *
	 * @return
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	private static ClassLoader getContextClassLoader() {
		ClassLoader classLoader = null;

		if (classLoader == null) {
			try {
				Method method = Thread.class.getMethod("getContextClassLoader", (Class[]) null);
				try {
					classLoader = (ClassLoader) method.invoke(Thread.currentThread());
				} catch (IllegalAccessException e) {
					; // ignore
				} catch (InvocationTargetException e) {

					if (e.getTargetException() instanceof SecurityException) {
						; // ignore
					} else {
						throw new RuntimeException("Unexpected InvocationTargetException", e.getTargetException());
					}
				}
			} catch (NoSuchMethodException e) {
				// Assume we are running on JDK 1.1
				; // ignore
			}
		}

		if (classLoader == null) {
			classLoader = PropertiesUtils.class.getClassLoader();
		}

		// Return the selected class loader
		return classLoader;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static InputStream getResourceAsStream(final String name) {
		return (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				ClassLoader threadCL = getContextClassLoader();

				if (threadCL != null) {
					return threadCL.getResourceAsStream(name);
				} else {
					return ClassLoader.getSystemResourceAsStream(name);
				}
			}
		});
	}

	public static void setConfigPath(String config, String... files) {
		CONFIG_PATH = config;
		if (null != files && files.length > 0) {
			PROPERTIES = new String[files.length + 1];
			PROPERTIES[0] = "channel-mq.properties";
			int index = 1;
			for (String string : files) {
				PROPERTIES[index] = string;
				index++;
			}
		}
		init();
	}
	
	public static void main(String[] args) {
		System.out.println(PropertiesUtils.getProperty("keyspool.max"));
	}

}