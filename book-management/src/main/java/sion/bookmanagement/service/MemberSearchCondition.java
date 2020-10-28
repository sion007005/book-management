package sion.bookmanagement.service;

public class MemberSearchCondition {
	private String searchType; //TODO 내부클래스로 enum으로 만들기
	private String keyword;
	private int ageFrom;
	private int ageTo;
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
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
