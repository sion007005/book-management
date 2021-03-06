package sion.bookmanagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sion.mvc.dispatcher.Controller;

public class AnnotationBookManagementControllerFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void isControllerInclude() {
		AnnotationBookManagementControllerFactory factory = new AnnotationBookManagementControllerFactory();
		// 기대하는값 / 실제값을 테스트해서 의도하는 대로 나오는지 확인한다.
		Controller memberListController = factory.getController("/members/list&GET");
		assertEquals("sion.bookmanagement.controller.member.MemberListController", memberListController.getClass().getCanonicalName());
		
		Controller bookListController = factory.getController("/books/list&GET");
		assertEquals("sion.bookmanagement.controller.book.BookListController", bookListController.getClass().getCanonicalName());
	}
}
