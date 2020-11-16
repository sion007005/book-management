package sion.bookmanagement.service.book;

import java.util.List;

import sion.bookmanagement.repository.book.BookRepository;
import sion.bookmanagement.repository.category.CategoryRepository;
import sion.bookmanagement.service.category.Category;

public class BookService {
	private static BookService bookService = new BookService();
	
	private BookRepository bookRepository = BookRepository.getInstance();
	private CategoryRepository categoryRepository = CategoryRepository.getInstance();
	
	private BookService() {
	}
	
	public static BookService getInstance() {
		return bookService;
	}
	
	public int create(Book book) {
		return bookRepository.create(book);
	}
	
	public void update(Book book) {
		bookRepository.update(book);
	}
	
	public void remove(Integer bookId) {
		Book book = bookRepository.findOneById(bookId);
		
		if (book == null) {
			throw new RuntimeException("존재하지 않는 데이터");
		}
		
		bookRepository.deleteById(bookId);
	}
	
	public List<Book> findAll(BookOrderType orderType) {
		List<Book> bookList = bookRepository.findAll(orderType);
		return bookList;
	}
	
	public List<Book> getCategoryBookList(Integer categoryId) {
		List<Book> bookList = bookRepository.findAllCategoryBooks(categoryId);
		return bookList;
	}

	public Book findOneById(int id) {
		Book book = bookRepository.findOneById(id);
		Category category = categoryRepository.findOneById(book.getCategoryId());
		book.setCategory(category);
		return book;
	}
	
	public List<Book> search(BookSearchCondition condition) {
		List<Book> bookList = bookRepository.search(condition);
		return bookList;
	}
}


