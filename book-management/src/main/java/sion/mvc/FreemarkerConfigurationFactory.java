package sion.mvc;

import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class FreemarkerConfigurationFactory { //싱글톤 패턴
	private static Configuration configuration;
	
	private FreemarkerConfigurationFactory() {
	}
	
	private static void initialize() {
		if (configuration == null) {
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			ClassTemplateLoader loader = new ClassTemplateLoader(ServerRunner.class, "/templates/"); //Runner클래스 기준으로 찾겠다 (위치)
			configuration.setTemplateLoader(loader);
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setObjectWrapper(new BeansWrapper());
		}
	}
	
	public static Configuration getInstance() {
		initialize();
		return configuration;
	}
}
