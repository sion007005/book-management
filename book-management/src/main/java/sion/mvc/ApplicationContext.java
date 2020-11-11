package sion.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sion.bookmanagement.util.StringUtils;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.InterceptorRegistry;

public class ApplicationContext {
	private static final int DEFAULT_PORT = 3434;
	private static final String NAME_SERVER_PORT = "server.port";
	private static final String NAME_CONTROLLER_FACTORY_CLASS = "controller.factory.class";
	private static final String NAME_INTERCEPTOR_REGISTRY_CLASS = "interceptor.registry.class";
	private static final String NAME_DB_URL = "db.url";
	private static final String NAME_DB_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String NAME_DB_ID = "db.id";
	private static final String NAME_DB_PASSWORD = "db.password";
	private static final String NAME_CHARSET = "charset.name";
	private static final String NAME_VIEW_FILE = "view.file.extension.name";
	private static final String NAME_STATIC_RESOURCES_PATH = "static.resources.path";
	
	private static Properties properties = new Properties();
	private static List<String> staticResourcesPathList;

	public static void addProperties(Properties properties) {
		properties.forEach((k, v) -> {
			 String value = StringUtils.trim((String) v);
			 ApplicationContext.properties.put(k, value);
		});
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static int getPort() {
		String portValue = properties.getProperty(NAME_SERVER_PORT);

		if (portValue == null || portValue.length() == 0) {
			return DEFAULT_PORT;
		}
		return Integer.parseInt(portValue);
	}

	public static ControllerFactory getControllerFactory() {
		try {
			String className = properties.getProperty(NAME_CONTROLLER_FACTORY_CLASS);
			//new 없이 객체 생성하는 방법
			ControllerFactory object = (ControllerFactory) Class.forName(className).getConstructor().newInstance();
			
			return object;
		} catch (Exception e) {
			throw new ServerRunnerException(e);
		}
	}
	
	public static String getDbUrl() {
		return properties.getProperty(NAME_DB_URL);
	}
	
	public static String getDbClassName() {
		return properties.getProperty(NAME_DB_CLASS_NAME);
	}
	
	public static String getDbId() {
		return properties.getProperty(NAME_DB_ID);
	}
	
	public static String getDbPassword() {
		String dbPassword = properties.getProperty(NAME_DB_PASSWORD);

		if (dbPassword == null || dbPassword.length() == 0) {
			return null;
		}
		return dbPassword;
	}
	
	public static String getCharsetType() {
		return properties.getProperty(NAME_CHARSET);
	}
	
	public static String getViewFileType() {
		return properties.getProperty(NAME_VIEW_FILE);
	}

	public static InterceptorRegistry getInterceptorRegistry() {
		try {
			String className = properties.getProperty(NAME_INTERCEPTOR_REGISTRY_CLASS);
			InterceptorRegistry object = (InterceptorRegistry) Class.forName(className).getConstructor().newInstance();
			
			return object;
		} catch (Exception e) {
			throw new ServerRunnerException(e);
		}
	}
	
	public static List<String> getStaticResourcePathList() {
		if (staticResourcesPathList == null) {
			staticResourcesPathList = new ArrayList<>();

			String pathString = properties.getProperty(NAME_STATIC_RESOURCES_PATH);
			String[] pathes = pathString.split(",");
			
			if (pathes != null) {
				for (String path : pathes) {
					staticResourcesPathList.add(path.trim());
				}
			}
		}

		return staticResourcesPathList;
	}
}
