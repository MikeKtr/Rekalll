package com.MikeKtr.KindleSorter.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User implements UserDetails{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
		@Column(unique = true)
		private String username;
		private String email;
		private String password;

		@Enumerated(EnumType.STRING)
		private Role role;

		@OneToMany(cascade = CascadeType.ALL)
		private List<Author> authors = new ArrayList();

		public User(){}

		public User(String username, String password, Role role) {
			this.username = username;
			this.password = password;
			this.role = role;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return null;
		}

		@Override
		public String getPassword() {
			return this.password;
		}

		@Override
		public String getUsername() {
			return this.username;
		}


		public Set<Role> getRoles(){
			Set<Role> roles = new HashSet<>();
			roles.add(this.role);
			return roles;
		}

		public String getRole() {
			return this.role.name();
		}

}
