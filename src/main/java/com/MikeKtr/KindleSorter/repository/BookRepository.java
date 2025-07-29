package com.MikeKtr.KindleSorter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MikeKtr.KindleSorter.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	Optional<Book> findByTitle(String title);

}
