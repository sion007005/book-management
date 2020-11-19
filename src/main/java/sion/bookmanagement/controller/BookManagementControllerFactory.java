package sion.bookmanagement.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.FileNotFoundException;
import sion.mvc.dispatcher.GetMapper;
import sion.mvc.dispatcher.PostMapper;

@Slf4j
public class BookManagementControllerFactory implements ControllerFactory {
	Map<String, Controller> controllers = new HashMap<>();
	
	public BookManagementControllerFactory() {
		initialize();
		initializeOfAnnotation();
	}

	private void initializeOfAnnotation() {
		// annotation을 가지고 와서 controller 실행하는 로직 구현
		Controller controller = null;
		String requestUri = "";
		
		
		// TODO(방법찾기) 1. 모든 controller class를 가지고 온다.
		String[] controllerList = {
				"LoginFormController",
				"LoginProcessController",
				"LogoutProcessController",
				"MemberCreateController",
				"MemberListController",
				"MemberFormController",
				"MemberInfoController",
				"MemberSearchController",
				"MemberUpdateController",
				"MemberRemoveController",
				"BookListController",
				"BookCreateController",
				"BookFormController",
				"BookInfoController",
				"BookRemoveController",
				"BookSearchController",
				"BookUpdateController",
				"CategoryCreateController",
				"CategoryFormController",
				"CategoryInfoController",
				"CategoryListController",
				"CategoryRemoveController",
				"CategorySearchController",
				"CategoryUpdateController",
		};
		
		// 2. command 메소드에 request mapping annotaion을 찾아서 controllers map(13번 줄)을 생성한다.
		for (String cont : controllerList) {
			if (cont.startsWith("Member")) {
				controller = getController("member", cont);
				requestUri = getPath(controller);
			} else if (cont.startsWith("Book")) {
				controller = getController("book", cont);
				requestUri = getPath(controller);
			} else if (cont.startsWith("Category")) {
				controller = getController("category", cont);
				requestUri = getPath(controller);
			} else {
				// TODO login 케이스 추가  
				// 1. login패키지 만들고, login관련 컨트롤러를 넣는다. 
				// 2. 전체적으로 다른 방법... 
			}
			
			controllers.put(requestUri, controller);
		}
	}
	
	private String getPath(Controller controller) {
		PostMapper post = null;
		GetMapper get = null;
		String path = "";
		String method = "";
		
		try {
			Method command = controller.getClass().getMethod("command", HttpServletRequest.class, HttpServletResponse.class);
			post = command.getDeclaredAnnotation(PostMapper.class);
			get = command.getDeclaredAnnotation(GetMapper.class);
		} catch (Exception e) {
			// TODO exception 던지기 
			log.error("", e);
		} 
		
		if (post != null) {
			path = post.value();
			method = "POST";
		} else if (get != null) {
			path = get.value();
			method = "GET";
		}
		
		return path +"&" + method;
	}
	
	private Controller getController(String type, String cont) {
		try {
			return (Controller) Class.forName("sion.bookmanagement.controller." + type + "." + cont).newInstance();
		} catch (Exception e) {
			log.error("", e);
		}
		
		return null;
	}
	
	private void initialize() {
		controllers.put("/login/form&GET", new LoginFormController());
		controllers.put("/login&POST", new LoginProcessController());
		controllers.put("/logout&GET", new LogoutProcessController());
		
//		controllers.put("/categories/intro&GET", new CategoryListController());
//		controllers.put("/categories/form&GET", new CategoryFormController());
//		controllers.put("/categories/create&POST", new CategoryCreateController());
//		controllers.put("/categories/list&GET", new CategoryListController());
//		controllers.put("/categories/info&GET", new CategoryInfoController());
//		controllers.put("/categories/update&GET", new CategoryFormController());
//		controllers.put("/categories/update&POST", new CategoryUpdateController());
//		controllers.put("/categories/remove&POST", new CategoryRemoveController());
//		controllers.put("/categories/search&GET", new CategorySearchController());
//		
//		controllers.put("/books/intro&GET", new BookListController());
//		controllers.put("/books/form&GET", new BookFormController());
//		controllers.put("/books/search&GET", new BookSearchController());
//		controllers.put("/books/update&GET", new BookFormController());
//		controllers.put("/books/create&POST", new BookCreateController());
//		controllers.put("/books/update&POST", new BookUpdateController());
//		controllers.put("/books/remove&POST", new BookRemoveController());
//		controllers.put("/books/list&GET", new BookListController());
//		controllers.put("/books/info&GET", new BookInfoController());
		
//		controllers.put("/members/intro&GET", new MemberListController());
//		controllers.put("/members/update&GET", new MemberFormController());
//		controllers.put("/members/info&GET", new MemberInfoController());
//		controllers.put("/members/form&GET", new MemberFormController());
//		controllers.put("/members/search&GET", new MemberSearchController());
//		controllers.put("/members/list&GET", new MemberListController());
//		controllers.put("/members/create&POST", new MemberCreateController());
//		controllers.put("/members/update&POST", new MemberUpdateController());
//		controllers.put("/members/remove&POST", new MemberRemoveController());
	}
	
	@Override
	public Controller getInstance(String key) {
		Controller controller = controllers.get(key);
		
		if (controller == null) {
			throw new FileNotFoundException("해당하는 controller가 없습니다.");
		}
		
		return controllers.get(key);
	}
	
	@Override 
	public String getKey(HttpServletRequest request) {
		return request.getRequestURI() + "&" + request.getMethod();
	}
}
