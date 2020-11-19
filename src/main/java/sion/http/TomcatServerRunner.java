package sion.http;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class TomcatServerRunner {

	public static void main(String[] args) throws Exception {
		TomcatServerRunner runner = new TomcatServerRunner();
		runner.serverStart();
	}

	private void serverStart() throws LifecycleException {
		String webappDirLocation = "webapp"; // webapp 폴더 밑을 root로 쓸거야
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(3000);
		Connector connector = tomcat.getConnector();
		connector.setURIEncoding("UTF-8");
		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

		// Declare an alternative location for your "WEB-INF/classes" dir
		// Servlet 3.0 어노테이션 작동
		// http://stackoverflow.com/questions/11669507/embedded-tomcat-7-servlet-3-0-annotations-not-working
		
		// 컴파일된 클래스파일이 있는 위치를 알려주고, 그걸 20번째 줄에 /WEB-INF/classes에 넣어주는 것.
		File additionWebInfClasses = new File("bin/main"); 
		WebResourceRoot resources = new StandardRoot(ctx);
		// /WEB-INF/classes 밑으로 컴파일된 클래스 파일을 넣어주는 것이 servlet의 스펙
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);
		
		tomcat.start();
		tomcat.getServer().await();
	}
}
