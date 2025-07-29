package com.MikeKtr.KindleSorter.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.MikeKtr.KindleSorter.service.UploadService;





@Controller
public class UploadControler {

	private final UploadService uploadService;

	

	public UploadControler(UploadService uploadService){
		this.uploadService = uploadService;
	}

	@GetMapping("/main")
	public String index(){
		return "index.html";
	}

	@PostMapping("/main")
	@ResponseBody
	public ResponseEntity<String> FileUpload(@RequestParam("file") MultipartFile file) throws IOException{
		// File file = new File("C:\\Users\\micha\\Desktop\\Kindle Sorter\\kindle-sorter-app\\My_Clippings.txt");
		// FileInputStream FIS = new FileInputStream(file);
		// MultipartFile Multifile = new MockMultipartFile("file",file.getName(),"text/plain",FIS);
		return uploadService.readClippingFromFolder(file);
	}

	// @GetMapping("/main/books")
	// public list<Book> getAllBooks(){
	// 	return 
	// }

}
