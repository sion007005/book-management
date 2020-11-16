package sion.bookmanagement.service.category;

public enum CategoryOrderType {
	ID("카테고리 ID", "category_id"),
	NAME("카테고리 이름", "category_name");
	
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
