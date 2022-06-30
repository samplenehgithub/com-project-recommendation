package com.cg.spring.project.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.project.book.model.Book;
import com.cg.spring.project.book.service.BookService;
import com.cg.spring.project.book.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
@RestController
@RequestMapping("/admin")  
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:9998")
public class AdminController {

	// Use ResponseEntity to all the methods in controller classes.

	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// http://localhost:9998/emp/get-all-emps
	@GetMapping("/get-all-book")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> bookList = userService.getAllBooks();
		for (Book bookTemp : bookList)
		logger.info(bookTemp.toString());
		@SuppressWarnings("unused")
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<List<Book>> response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	// http://localhost:9998/emp/get-book-by-id/{userid}
	@GetMapping("/get-user-by-id/{userid}")
	public ResponseEntity<Book> getById(@PathVariable(name = "userid") int userid) {
		logger.info(Integer.toString(userid));
		@SuppressWarnings("unused")
		Book book = bookService.getBookById(userid);
		@SuppressWarnings("unused")
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<Book> response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	// http://localhost:9998/emp/get-book-by-name/{firstName}
	@GetMapping("/get-book-by-name/{Name}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable(name = "Name") String Name) {
		logger.info(Name);
		@SuppressWarnings("unused")
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<List<Book>> response = new ResponseEntity<>(HttpStatus.OK);
				
		return response;
	}

@SuppressWarnings("null")
@PostMapping("/books")
public ResponseEntity<Book> addBook(@RequestBody Book book) {
	BookService bookservice = null;
	Book b =bookservice.addBook(book);
	if(b!=null) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	System.out.println(b);
	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
}

@SuppressWarnings("null")
@DeleteMapping("/books/{id}")
public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {
	try {
	   BookService bookservice = null;
	bookservice.deleteBook(id);		
	   return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			e.printStackTrace();
	return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}

}
}






	
	    
	    
