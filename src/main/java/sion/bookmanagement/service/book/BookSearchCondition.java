package sion.bookmanagement.service.book;

public class BookSearchCondition {
	public static final int PAGE_SIZE = 10;
	private SearchType searchType; 
	private String keyword;
	private int page;
	
	//TODO page == 0 이면 1 리
	public int getPage() {
		return (page == 0)? 1 : page;
	}
	
	public int getLimit() {
		return PAGE_SIZE;
	}
	
	/**
	 * 1page : 0, 2page : 10
	 * select * from aaa limit ${limit} @{offset}
	 */
	public int getOffset() {
		return (getPage() - 1) * PAGE_SIZE;
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
}
