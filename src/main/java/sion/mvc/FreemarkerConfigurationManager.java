package sion.mvc;

import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import sion.http.TomcatServerRunner;

public class FreemarkerConfigurationManager { //싱글톤 패턴
	private static Configuration configuration;
	
	private FreemarkerConfigurationManager() {
		
	}
	
	private static void initialize() {
		if (configuration == null) {
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			ClassTemplateLoader loader = new ClassTemplateLoader(TomcatServerRunner.class, "/templates/"); //TomcatServerRunner클래스 기준으로 찾겠다 (위치)
			configuration.setTemplateLoader(loader);
			configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
			configuration.setObjectWrapper(new BeansWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
		}
	}
	
	public static Configuration getInstance() {
		initialize();
		return configuration;
	}
}
