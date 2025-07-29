package com.MikeKtr.KindleSorter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MikeKtr.KindleSorter.model.Book;
import com.MikeKtr.KindleSorter.model.Quote;


public interface QuoteRepository extends JpaRepository<Quote, Integer>{
	Optional<Quote> findByBook(Book book);
}
