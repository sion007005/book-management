package sion.bookmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import sion.mvc.dispatcher.Commander;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.FileNotFoundException;

@Slf4j
public class BookManagementControllerFactory implements ControllerFactory {
	Map<String, Commander> controllers = new HashMap<>();
	
	public BookManagementControllerFactory() {
		initialize();
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
	public Commander getInstance(String key) {
		Commander controller = controllers.get(key);
		
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
