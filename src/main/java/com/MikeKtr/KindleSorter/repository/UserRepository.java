package com.MikeKtr.KindleSorter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MikeKtr.KindleSorter.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
}
