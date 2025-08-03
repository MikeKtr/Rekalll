package com.MikeKtr.KindleSorter.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.MikeKtr.KindleSorter.model.Quote;
import com.MikeKtr.KindleSorter.model.Role;
import com.MikeKtr.KindleSorter.model.User;
import com.MikeKtr.KindleSorter.repository.UserRepository;
import com.MikeKtr.KindleSorter.service.DBService;
import com.MikeKtr.KindleSorter.service.UploadService;
import com.MikeKtr.KindleSorter.service.UserService;





@Controller
public class UploadControler {

	private final UploadService uploadService;
	private final DBService dbService;
	private final UserRepository userRepo;
	private final UserService userService;
	

	public UploadControler(UploadService uploadService,DBService dbService, UserRepository userRepo,UserService userService){
		this.uploadService = uploadService;
		this.dbService = dbService;
		this.userRepo = userRepo;
		this.userService = userService;
	}

	@PostMapping(path = "/add")
	@ResponseBody
	public String addNewUser(@RequestParam String name, @RequestParam String password){
		userService.addUser(name, password, Role.USER);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	  return userRepo.findAll();
	}

	@GetMapping("/login")
	public String login(){
		return "login.html";
	}

	

	@GetMapping("/api/register")
	public String register(){
		return "register.html";
	}


	@GetMapping("/main")
	public String index(){
		return "index.html";
	}

	@PostMapping("/")
	@ResponseBody
	public ResponseEntity<String> FileUpload(@RequestParam("file") MultipartFile file) throws IOException{
		return uploadService.readClippingFromFolder(file);
	}


	@GetMapping("/home")
    public String home() {
        return "Zalogowano!";
    }

	@GetMapping("/main/books")
	@ResponseBody
	public List<Quote> GetAllBooks(){
		return dbService.GetAllQuotes();
	}

}
