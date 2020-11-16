package sion.mvc.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.util.StringUtils;
import sion.http.ServerRunnerException;
@Slf4j
public class PropertiesLoader {
	public static final String FILE_NAME = "/application.properties"; // src/main/resources 부터
	
	public Properties load() {
		String fileName = System.getProperty("application.properties");
		if (StringUtils.isEmpty(fileName)) {
			fileName = PropertiesLoader.FILE_NAME;
		}
		return load(fileName);
	}
	
	public Properties load(String fileName) {
		Properties properties = new Properties();
		
		  try {
			//외부에서 들어올 때는 inputStream (네트워크 통해서, 파일을 통해서 등등 들어올 수 있다) 
		   //getClass() : 현재 클래스 정보를 가져옴. 현재는 classPath를 알기위해서 (너의 root가 어디니하고...)
			log.debug("fileName: {}",fileName);
			log.debug("getClass(): {}",getClass());
			log.debug("inputStream: {}",getClass().getResourceAsStream(fileName));
			InputStream inputStream = getClass().getResourceAsStream(fileName); //클래스 path로 부터 파일을 찾아와라
		   // application properties에 있는 key, value 값을 properties 객체로 (읽어서) 바꿔준다.
			properties.load(inputStream);
			log.info("properties.load  후 properties: {}", properties);
		   return properties;
		  } catch (IOException e) {
		   throw new ServerRunnerException(e);
		  }
	}
}
