package sion.bookmanagement.service.book;

public class BookSearchCondition {
	public static final int PAGE_SIZE = 10;
	private SearchType searchType; 
	private String keyword;
	private int limit;
	private int offset;
	
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
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public enum SearchType {
		TITLE("제목", "title"),
		AUTHOR("저자", "author"),
		ALL("전체", "");
		
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
