package sion.bookmanagement.service.book;

public class BookSearchCondition {
	private SearchType searchType; 
	private String keyword;
	
	public enum SearchType {
		TITLE("제목", "title"),
		AUTHOR("저자", "author");
		
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
