package sion.bookmanagement.service;

import java.util.Date;

public class Member {
	private Integer id;
	private String name;
	private String gender;
	private String email;
	private Integer age;
	private String phone;
	//TODO createdAt으로 바꾸기 
	private Date created;
	private Date updatedAt; 
	
	public Member() {}
	
	public Member(String name, String gender, String email, Integer age, String phone) {
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.age = age;
		this.phone = phone;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
