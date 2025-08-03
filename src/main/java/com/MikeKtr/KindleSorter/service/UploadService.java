package com.MikeKtr.KindleSorter.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MikeKtr.KindleSorter.model.Author;
import com.MikeKtr.KindleSorter.model.Book;
import com.MikeKtr.KindleSorter.model.Quote;
import com.MikeKtr.KindleSorter.repository.AuthorRepository;
import com.MikeKtr.KindleSorter.repository.BookRepository;
import com.MikeKtr.KindleSorter.repository.QuoteRepository;

import jakarta.transaction.Transactional;

@Service
public class UploadService {

	private final AuthorRepository authorRepo;
	private final BookRepository bookRepo;
	private final QuoteRepository quoteRepo;

	public UploadService(AuthorRepository ar,BookRepository br,QuoteRepository qr){
		this.authorRepo = ar;
		this.bookRepo = br;
		this.quoteRepo = qr;
	}

	public static LocalDateTime processDateLine(String dateLine){
		dateLine = dateLine.substring(dateLine.lastIndexOf(',') + 2);
        System.out.println(dateLine);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm:ss", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateLine,format);
        return dateTime;
        }

	public static Integer processPageLine(String dateLine){
		Matcher matcher = Pattern.compile("\\d+").matcher(dateLine);
		matcher.find();
		int page = Integer.valueOf(matcher.group());
		return page;
	}

	@Transactional
	public ResponseEntity<String> readClippingFromFolder(MultipartFile file){

		// Map<Book,List<Quote>> books = new HashMap<>();
		// Map<Author,List<Book>> authors = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {

				String titleLine = "";
				String dateLine = "";
				List<String> quoteList = new ArrayList<>();


				while (!line.equals("==========")) {


					if (titleLine.isEmpty()) {
						titleLine = line;

					} else if (dateLine.isEmpty()) {
						dateLine = line;

					} else {
						quoteList.add(line);
					}
					line = reader.readLine();
				}

				String title = titleLine.replaceAll("\\s*\\(.*\\)$", "").trim();
                String authorName = titleLine.replaceAll("^.*\\((.*)\\)$", "$1").trim();

				Author author = authorRepo.findByName(authorName).orElseGet(() -> {
					Author a = new Author();
					a.setName(authorName);
					return authorRepo.save(a);
				});

				Book book = bookRepo.findByTitle(title).orElseGet(() -> {
					Book b = new Book(title,author);
					return bookRepo.save(b);
				});
				String FullQuote = "";
				for(String quoteText : quoteList){
					FullQuote += quoteText;
				}

				LocalDateTime saveData= processDateLine(dateLine);
				Integer page = processPageLine(dateLine);
				Quote q = new Quote(FullQuote,book,saveData,page);
				System.out.println(dateLine);
				quoteRepo.save(q);

				
				


				// Author author = new Author(titleLine);
				// Book book = new Book(titleLine, author);
				// Quote quote = new Quote(quoteList, book);


				// if (books.keySet().stream().noneMatch(key -> key.getTitle().equals(book.getTitle()))) {
				// 	books.put(book, new ArrayList<>());
				// }
				// //Adds quote to book in map
				// Book bookKey = books.keySet().stream().filter(key -> key.getTitle().equals(book.getTitle())).findFirst().orElse(null);
				// books.get(bookKey).add(quote);

				// if (authors.keySet().stream().noneMatch(key -> key.getName().equals(author.getName()))) {
				// 	authors.put(author, new ArrayList<>());
				// }
				// Author authorKey = authors.keySet().stream().filter(key -> key.getName().equals(author.getName())).findFirst().orElse(null);
				// if(!authors.get(authorKey).contains(bookKey)){
				// 	authors.get(authorKey).add(book);
				// }
			}
		}
		catch (IOException e) {
			return ResponseEntity.status(500).body("Error: canno't read file");
		}
		return ResponseEntity.ok("Data saved to Data Base");
	}}
