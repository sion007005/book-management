package sion.bookmanagement.service.category;

import java.util.List;

import sion.bookmanagement.repository.category.CategoryRepository;

public class CategoryService {
	private static CategoryService categoryService = new CategoryService();
	private CategoryRepository categoryRepository = CategoryRepository.getInstance();
	
	private CategoryService() {
	}
	
	public static CategoryService getInstance() {
		return categoryService;
	}
	
	public int create(Category category) {
		return categoryRepository.create(category);
	}
	
	public void update(Category category) {
		categoryRepository.update(category);
	}
	
	public void remove(Integer categoryId) {
		Category category = categoryRepository.findOneById(categoryId);
		
		if (category == null) {
			throw new RuntimeException("존재하지 않는 카테고리");
		}
		
		categoryRepository.deleteById(categoryId);
	}
	
	public List<Category> findAll(CategoryOrderType orderType) {
		List<Category> categoryList = categoryRepository.findAll(orderType);
		return categoryList;
	}

	public Category findOneById(int categoryId) {
		return categoryRepository.findOneById(categoryId);
	}

	public List<Category> search(CategorySearchCondition condition) {
		List<Category> categoryList = categoryRepository.search(condition);
		return categoryList;
	}
}
