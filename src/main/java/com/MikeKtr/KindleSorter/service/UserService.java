package com.MikeKtr.KindleSorter.service;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MikeKtr.KindleSorter.model.Role;
import com.MikeKtr.KindleSorter.model.User;
import com.MikeKtr.KindleSorter.repository.UserRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepo,PasswordEncoder passwordEncoder){
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public User addUser(String username,String password,Role role){
		User u = new User(username,passwordEncoder.encode(password),role);
		return userRepo.save(u);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

		List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
        .collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
				authorities
        );
	}
	
}
