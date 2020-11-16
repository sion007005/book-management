package sion.http;

import sion.bookmanagement.util.StringUtils;

public class Cookie {
	private String name;
	private String value;
	private String path;
	private String domain;
	private String expires;
	
	public String generateCookieValue() {
		//sid=memberId; Path=/; Domain=localhost; Expires=Sun, 07 Nov 2021 11:43:50 GMT
		//TODO expires null인 경우
		String cookieValue = StringUtils.getNullToEmpty(this.name)+"="+StringUtils.getNullToEmpty(this.value)
									+"; Path="+StringUtils.getNullToEmpty(this.path)+"; Domain="+StringUtils.getNullToEmpty(this.domain)
									+"; Expires="+this.expires;
		
		return cookieValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String cookieExpires) {
		this.expires = cookieExpires;
	}
}
