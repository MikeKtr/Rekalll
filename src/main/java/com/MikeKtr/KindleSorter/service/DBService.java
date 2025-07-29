package com.MikeKtr.KindleSorter.service;

import java.util.List;

import com.MikeKtr.KindleSorter.model.Book;
import com.MikeKtr.KindleSorter.repository.AuthorRepository;
import com.MikeKtr.KindleSorter.repository.BookRepository;
import com.MikeKtr.KindleSorter.repository.QuoteRepository;

public class DBService {
	private final AuthorRepository authorRepo;
	private final BookRepository bookRepo;
	private final QuoteRepository quoteRepo;

	public DBService(AuthorRepository ar,BookRepository br,QuoteRepository qr){
		this.authorRepo = ar;
		this.bookRepo = br;
		this.quoteRepo = qr;
	}

	public List<Book> GetAllBooks(){
		return bookRepo.findAll();
	}


}
