package sion.bookmanagement.service;

public enum BookOrderType {
	TITLE("제목", "title"),
	AUTHOR("저자", "author"),
	PRICE("가격", "price"),
	YEAR("출판년도", "year"),
	STOCK("재고", "stock");
	
	private String name; 
	private String columnName;
	
	private BookOrderType(String name, String columnName) {
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
