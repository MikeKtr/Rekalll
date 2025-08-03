package com.MikeKtr.KindleSorter.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
	@Id
	@GeneratedValue
	private int Id;
	private String title;
	
	
	@OneToMany
	@JoinColumn(name = "quote_id")
	private List<Quote> quotes = new ArrayList();
	
	@ManyToOne
	private Author author;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Book() {
	}
	public Book(String title, Author author) {
		
		this.title = title;
		this.author = author;
	}
	
	

}
