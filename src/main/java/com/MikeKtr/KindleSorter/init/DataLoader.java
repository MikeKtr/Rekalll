package com.MikeKtr.KindleSorter.init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.MikeKtr.KindleSorter.model.Role;
import com.MikeKtr.KindleSorter.model.User;
import com.MikeKtr.KindleSorter.repository.UserRepository;


@Component
public class DataLoader implements CommandLineRunner {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	public DataLoader(PasswordEncoder passwordEncoder,UserRepository userRepository){
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.count() == 0){
			User admin = new User("admin",passwordEncoder.encode("admin"),Role.ADMIN);
			User user = new User("user",passwordEncoder.encode("user"),Role.USER);

			userRepository.saveAll(List.of(admin,user));
			System.out.println("Data loaded");
		}
	}
	
}
