package sion.http;

public enum HttpStatus {
	OK(200),
	MOVED_PERMANENTLY(301),
	FORBIDDEN(403),
	NOT_FOUND(404),
	INTERNAL_SERVER_ERROR(500);
	
	private int code;
	
	private HttpStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
