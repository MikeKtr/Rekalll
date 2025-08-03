package com.MikeKtr.KindleSorter.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDateTime saveDateTime; 
	private int pageNumber;

	@Column(length = 3000)
	private String quote;
	
	@ManyToOne
	private Book book;

    public Quote() {
    }

	public int getId() {
		return id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Quote(String Quote,Book book,LocalDateTime DateTime,Integer pageNumber){
		this.quote = Quote;
        this.book = book;
		this.saveDateTime = DateTime;
		this.pageNumber = pageNumber;
    }
	

	
}
