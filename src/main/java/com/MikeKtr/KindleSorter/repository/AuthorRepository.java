package com.MikeKtr.KindleSorter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MikeKtr.KindleSorter.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer>{

    Optional<Author> findByName(String authorName);
	
}
