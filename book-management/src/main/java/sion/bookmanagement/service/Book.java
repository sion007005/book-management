package sion.bookmanagement.service;

import java.util.Date;

public class Book { 
	private Integer id;
	private Integer categoryId;
	private String title;
	private String author;
	private Integer stock;
	private Integer year;
	private Integer price;
	private Category category;
	private Date createdAt;
	private Date updatedAt;
	
	public Book() {}

	public Book(Integer categoryId, String title, String author, Integer stock, 
			Integer year, Integer price) {
		this.categoryId = categoryId;
		this.title = title;
		this.author = author;
		this.stock = stock;
		this.year = year;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
