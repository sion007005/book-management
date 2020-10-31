package sion.mvc;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

public class FreemarkerConfigurationFactory { //싱글톤 패턴
	private static Configuration configuration;
	
	private FreemarkerConfigurationFactory() {
	}
	
	private static void initialize() {
		if (configuration == null) {
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			ClassTemplateLoader loader = new ClassTemplateLoader(ServerRunner.class, "/templates/"); //Runner클래스 기준으로 찾겠다 (위치)
			configuration.setTemplateLoader(loader);
		}
	}
	
	public static Configuration getInstance() {
		initialize();
		return configuration;
	}
}
