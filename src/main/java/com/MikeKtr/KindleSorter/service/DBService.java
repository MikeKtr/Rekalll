package com.MikeKtr.KindleSorter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MikeKtr.KindleSorter.model.Book;
import com.MikeKtr.KindleSorter.model.Quote;
import com.MikeKtr.KindleSorter.repository.AuthorRepository;
import com.MikeKtr.KindleSorter.repository.BookRepository;
import com.MikeKtr.KindleSorter.repository.QuoteRepository;
import com.MikeKtr.KindleSorter.repository.UserRepository;
@Service
public class DBService {
	private final AuthorRepository authorRepo;
	private final BookRepository bookRepo;
	private final QuoteRepository quoteRepo;
	private final UserRepository userRepo;

	public DBService(AuthorRepository ar,BookRepository br,QuoteRepository qr,UserRepository ur){
		this.authorRepo = ar;
		this.bookRepo = br;
		this.quoteRepo = qr;
		this.userRepo = ur;
	}

	public List<Book> GetAllBooks(){
		return bookRepo.findAll();
	}

	public List<Quote> GetAllQuotes(){
		return quoteRepo.findAll();
	}


}
