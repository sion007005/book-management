package sion.bookmanagement.service.member;

// 뷰 - 서버 연결시켜줌. (정해진 키워드가 아닌 문자가 오면 실행되지 않도록 함)
public enum MemberOrderType {
	NAME("이름", "name"),
	AGE("나이", "age"),
	EMAIL("이메일", "email"),
	PHONE("휴대폰", "phone");
	
	private String name; 
	private String columnName;
	
	private MemberOrderType(String name, String columnName) {
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
