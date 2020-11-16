package sion.bookmanagement.service.member;

public class MemberSearchCondition {
	private SearchType searchType; 
	private String keyword;
	private int ageFrom;
	private int ageTo;
	
	public enum SearchType {
		NAME("이름", "name"),
		EMAIL("이메일", "email"),
		PHONE("휴대폰", "phone");
		
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
	
	public int getAgeFrom() {
		return ageFrom;
	}
	
	public void setAgeFrom(int ageFrom) {
		this.ageFrom = ageFrom;
	}
	
	public int getAgeTo() {
		return ageTo;
	}
	
	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}
	
}
