package sion.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.util.StringUtils;
import sion.http.ServerRunnerException;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.InterceptorRegistry;
@Slf4j
public class ApplicationContext {
	private static final int DEFAULT_PORT = 3434;
	private static final int DEFAULT_POOL_SIZE = 3;
	private static final String NAME_SERVER_PORT = "server.port";
	private static final String NAME_CONTROLLER_FACTORY_CLASS = "controller.factory.class";
	private static final String NAME_INTERCEPTOR_REGISTRY_CLASS = "interceptor.registry.class";
	private static final String NAME_DB_URL = "db.url";
	private static final String NAME_DB_CLASS_NAME = "db.class.name";
	private static final String NAME_DB_ID = "db.id";
	private static final String NAME_DB_PASSWORD = "db.password";
	private static final String NAME_DB_TEST_QUERY = "db.test.query";
	private static final String NAME_DB_CONNECTION_POOL = "db.connection.pool.name";
	private static final String SIZE_DB_CONNECTION_POOL = "db.connection.pool.size";
	private static final String TIME_DB_CONNECTION = "db.connection.time";
	private static final String NAME_CHARSET = "charset.name";
	private static final String NAME_VIEW_FILE = "view.file.extension.name";
	private static final String NAME_STATIC_RESOURCES_PATH = "static.resources.path";
	private static final String NAME_IMAGE_FILE_TYPE = "image.file.type";
	
	private static Properties properties = new Properties();
	private static List<String> staticResourcesPathList;
	private static List<String> imageFileTypeList;

	public static void addProperties(Properties properties) {
		properties.forEach((k, v) -> {
			 String value = StringUtils.trim((String) v);
			 ApplicationContext.properties.put(k, value);
		});
	}
	// 특별한 로직이 없는 경우에는, 이 메소드를 활용하고 따로 만들 필요 없음(getDbUrl과 같은 메소드는 불필요)
	public static String getProperty(String key) {
		return ApplicationContext.properties.getProperty(key);
	}

	public static int getPort() {
		String portValue = ApplicationContext.properties.getProperty(NAME_SERVER_PORT);

		if (portValue == null || portValue.length() == 0) {
			return DEFAULT_PORT;
		}
		return Integer.parseInt(portValue);
	}

	public static ControllerFactory getControllerFactory() {
		try {
			String className = ApplicationContext.properties.getProperty(NAME_CONTROLLER_FACTORY_CLASS);
//			//new 없이 객체 생성하는 방법
			ControllerFactory object = (ControllerFactory) Class.forName(className).newInstance();

			return object;
		} catch (Exception e) {
			throw new ServerRunnerException(e);
		}
	}
	
	public static String getDbUrl() {
		return ApplicationContext.properties.getProperty(NAME_DB_URL);
	}
	
	public static String getDbClassName() {
		return ApplicationContext.properties.getProperty(NAME_DB_CLASS_NAME);
	}
	
	public static String getDbId() {
		return ApplicationContext.properties.getProperty(NAME_DB_ID);
	}
	
	public static String getDbPassword() {
		String dbPassword = ApplicationContext.properties.getProperty(NAME_DB_PASSWORD);

		if (dbPassword == null || dbPassword.length() == 0) {
			return null;
		}
		return dbPassword;
	}
	
	public static String getCharsetType() {
		return ApplicationContext.properties.getProperty(NAME_CHARSET);
	}
	
	public static String getViewFileType() {
		return ApplicationContext.properties.getProperty(NAME_VIEW_FILE);
	}

	public static InterceptorRegistry getInterceptorRegistry() {
		try {
			String className = ApplicationContext.properties.getProperty(NAME_INTERCEPTOR_REGISTRY_CLASS);
			InterceptorRegistry object = (InterceptorRegistry) Class.forName(className).getConstructor().newInstance();
			
			return object;
		} catch (Exception e) {
			throw new ServerRunnerException(e);
		}
	}
	
	public static List<String> getStaticResourcePathList() {
		if (staticResourcesPathList == null) {
			staticResourcesPathList = new ArrayList<>();

			String pathString = ApplicationContext.properties.getProperty(NAME_STATIC_RESOURCES_PATH);
			String[] pathes = pathString.split(",");
			
			if (pathes != null) {
				for (String path : pathes) {
					staticResourcesPathList.add(path.trim());
				}
			}
		}

		return staticResourcesPathList;
	}
	
	public static List<String> getImageFileTypeList() {
		if (imageFileTypeList == null) {
			imageFileTypeList = new ArrayList<>();
		}
		
		String typeString = ApplicationContext.properties.getProperty(NAME_IMAGE_FILE_TYPE);
		String[] types = typeString.split(",");
		
		if (types != null) {
			for (String type : types) {
				imageFileTypeList.add(type.trim());
			}
		}
		
		return imageFileTypeList;
	}
	
	public static String getDbTestQuery() {
		return ApplicationContext.properties.getProperty(NAME_DB_TEST_QUERY);
	}
	
	public static String getDbConnectionPoolName() {
		return ApplicationContext.properties.getProperty(NAME_DB_CONNECTION_POOL);
	}

	public static int getDbConnectionPoolSize() {
		String poolSize = ApplicationContext.properties.getProperty(SIZE_DB_CONNECTION_POOL);
		
		if (poolSize == null || poolSize.length() == 0) {
			return DEFAULT_POOL_SIZE;
		}
		
		return Integer.parseInt(poolSize);
	}
	
	public static int getDbConnectionTime() {
		return Integer.parseInt(properties.getProperty(TIME_DB_CONNECTION));
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		ApplicationContext.properties = properties;
	}
}
