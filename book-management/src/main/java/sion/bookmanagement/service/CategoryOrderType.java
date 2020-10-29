package sion.bookmanagement.service;

public enum CategoryOrderType {
	ID("카테고리 ID", "categoryId"),
	NAME("카테고리 이름", "categoryName");
	
	private String name;
	private String columnName;
	
	private CategoryOrderType(String name, String columnName) {
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
