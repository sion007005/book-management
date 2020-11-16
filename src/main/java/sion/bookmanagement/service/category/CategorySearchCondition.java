package sion.bookmanagement.service.category;

public class CategorySearchCondition {
	private SearchType searchType; 
	private String keyword;
	
	public enum SearchType {
		NAME("카테고리 이름", "category_name");
		
		private String name;
		private String columnName;
		
		private SearchType(String name, String columnName) {
			this.name = name;
			this.columnName = columnName;
		}
		
		public String getName() {
			return name;
		}
		
		public String getColumnName() {
			return columnName;
		}
	}
	
	public SearchType getSearchType() {
		return searchType;
	}
	
	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
