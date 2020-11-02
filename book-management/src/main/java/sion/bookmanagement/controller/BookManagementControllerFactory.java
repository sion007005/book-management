package sion.bookmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import sion.mvc.HttpRequest;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.FileNotFoundException;

public class BookManagementControllerFactory implements ControllerFactory {
	Map<String, Controller> controllers = new HashMap<>();
	
	public BookManagementControllerFactory() {
		controllers.put("/categories/intro&GET", new CategoryListController());
		controllers.put("/categories/form&GET", new CategoryFormController());
		controllers.put("/categories/create&POST", new CategoryCreateController());
		controllers.put("/categories/list&GET", new CategoryListController());
		controllers.put("/categories/info&GET", new CategoryInfoController());
		controllers.put("/categories/update&GET", new CategoryUpdateFormController());
		controllers.put("/categories/update&POST", new CategoryUpdateController());
		controllers.put("/categories/remove&POST", new CategoryRemoveController());
		controllers.put("/categories/search&GET", new CategorySearchController());
		
		controllers.put("/books/intro&GET", new BookListController());
		controllers.put("/books/form&GET", new BookFormController());
		controllers.put("/books/search&GET", new BookSearchController());
		

		controllers.put("/books/create&POST", new BookCreateController());
		controllers.put("/books/update&POST", new BookUpdateController());
		controllers.put("/books/update&GET", new BookUpdateFormController());
		controllers.put("/books/remove&POST", new BookRemoveController());
		controllers.put("/books/list&GET", new BookListController());
		controllers.put("/books/info&GET", new BookInfoController());
		
		controllers.put("/members/intro&GET", new MemberListController());
		controllers.put("/members/update&GET", new MemberUpdateFormController());
		controllers.put("/members/info&GET", new MemberInfoController());
		controllers.put("/members/form&GET", new MemberFormController());
		
		controllers.put("/members/create&POST", new MemberCreateController());
		controllers.put("/members/update&POST", new MemberUpdateController());
		controllers.put("/members/remove&POST", new MemberRemoveController());
		controllers.put("/members/search&GET", new MemberSearchController());
		controllers.put("/members/list&GET", new MemberListController());
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
	public String getKey(HttpRequest httpRequest) {
		return httpRequest.getUrl() + "&" + httpRequest.getMethod();
	}
}
