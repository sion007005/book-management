package sion.bookmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sion.bookmanagement.controller.book.BookCreateController;
import sion.bookmanagement.controller.book.BookFormController;
import sion.bookmanagement.controller.book.BookInfoController;
import sion.bookmanagement.controller.book.BookListController;
import sion.bookmanagement.controller.book.BookRemoveController;
import sion.bookmanagement.controller.book.BookSearchController;
import sion.bookmanagement.controller.book.BookUpdateController;
import sion.bookmanagement.controller.category.CategoryCreateController;
import sion.bookmanagement.controller.category.CategoryFormController;
import sion.bookmanagement.controller.category.CategoryInfoController;
import sion.bookmanagement.controller.category.CategoryListController;
import sion.bookmanagement.controller.category.CategoryRemoveController;
import sion.bookmanagement.controller.category.CategorySearchController;
import sion.bookmanagement.controller.category.CategoryUpdateController;
import sion.bookmanagement.controller.member.MemberCreateController;
import sion.bookmanagement.controller.member.MemberFormController;
import sion.bookmanagement.controller.member.MemberInfoController;
import sion.bookmanagement.controller.member.MemberListController;
import sion.bookmanagement.controller.member.MemberRemoveController;
import sion.bookmanagement.controller.member.MemberSearchController;
import sion.bookmanagement.controller.member.MemberUpdateController;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.ControllerFactory;
import sion.mvc.dispatcher.FileNotFoundException;

public class BookManagementControllerFactory implements ControllerFactory {
	Map<String, Controller> controllers = new HashMap<>();
	
	public BookManagementControllerFactory() {
		initialize();
//TODO		initializeOfAnnotation();
	}

	private void initializeOfAnnotation() {
		// annotation을 가지고 와서 controller 실행하는 로직 구현
		// 1. 모든 controller class를 가지고 온다. 
		// 2. command 메소드에 request mapping annotaion을 찾아서 controllers map(13번 줄)을 생성한다.
	}
	
	private void initialize() {
		controllers.put("/login/form&GET", new LoginFormController());
		controllers.put("/login&POST", new LoginProcessController());
		controllers.put("/logout&GET", new LogoutProcessController());
		
		controllers.put("/categories/intro&GET", new CategoryListController());
		controllers.put("/categories/form&GET", new CategoryFormController());
		controllers.put("/categories/create&POST", new CategoryCreateController());
		controllers.put("/categories/list&GET", new CategoryListController());
		controllers.put("/categories/info&GET", new CategoryInfoController());
		controllers.put("/categories/update&GET", new CategoryFormController());
		controllers.put("/categories/update&POST", new CategoryUpdateController());
		controllers.put("/categories/remove&POST", new CategoryRemoveController());
		controllers.put("/categories/search&GET", new CategorySearchController());
		
		controllers.put("/books/intro&GET", new BookListController());
		controllers.put("/books/form&GET", new BookFormController());
		controllers.put("/books/search&GET", new BookSearchController());
		

		controllers.put("/books/create&POST", new BookCreateController());
		controllers.put("/books/update&POST", new BookUpdateController());
		controllers.put("/books/update&GET", new BookFormController());
		controllers.put("/books/remove&POST", new BookRemoveController());
		controllers.put("/books/list&GET", new BookListController());
		controllers.put("/books/info&GET", new BookInfoController());
		
		controllers.put("/members/intro&GET", new MemberListController());
		controllers.put("/members/update&GET", new MemberFormController());
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
	public String getKey(HttpServletRequest request) {
		return request.getRequestURI() + "&" + request.getMethod();
	}
	
}
