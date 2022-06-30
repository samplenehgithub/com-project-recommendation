package com.cg.spring.project.book.service;

import java.util.List;


import java.util.Optional;
//import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.project.book.dto.BookDTO;
import com.cg.spring.project.book.exception.BookNotFoundException;
import com.cg.spring.project.book.exception.UserNotFoundException;
import com.cg.spring.project.book.model.Book;
import com.cg.spring.project.book.model.User;
import com.cg.spring.project.book.repository.BookRepository;
import com.cg.spring.project.book.repository.UserRepository;

@Service
public class BookServiceImpl implements BookService{
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	BookRepository bookRepository;

	@Autowired
    UserRepository userRepository;

	@Autowired
	UserService userService;

	private  final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
//	public List<BookDTO> getAllBooks() {
//		logger.info("getAllBooks");
//		List<Book> bookList = bookRepository.findAll();
//	//	return bookList.stream().map(book -> new Book(book.getBookId(),book.getBookName(),book.getPrice(),book.getAuthor(),book.getBookName(),book.getRating()).collect(Collectors.toList()));
//		return null;
//		
//	}
	public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            String exceptionMessage = "AppUsers don't exist in the database.";
            logger.warn(exceptionMessage);
            throw new UserNotFoundException(exceptionMessage);
        } else {
            logger.info("depList returned successfully.");
            return userList;
        }
	}

	public Book getBookById(int bookId) {
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		Book book = null;
		if (bookOptional.isPresent()) {
			book =bookOptional.get();
			logger.info(book.toString());
			return book;
		} else {
			String errorMessage = "Book with id " + bookId + " does not exist.";
			logger.error(errorMessage);
			throw new BookNotFoundException(errorMessage);
		}
	}

	public List<Book> getBookByName(String name) {
		logger.info(name);
		@SuppressWarnings("unchecked")
		List<Book> bookList  = (List<Book>) bookRepository.FindBookByName(name);
		if (null != bookList)
			return bookList;
		String errorMessage = "Book with Name " + name + " does not exist.";
		throw new BookNotFoundException(errorMessage);
	}

	public Book addBook(Book book) {
		logger.info(book.toString());
		if(book.getAuthor()!= null)
			
		AuthorService.getAuthorByBookName(book.getAuthor().getBookName());
	
		return bookRepository.save(book);
	}

	public Book updateBook(Book book) {
		logger.info(book.toString());
		BookServiceImpl bookService = new BookServiceImpl();
		bookService.getBookById(book.getBookId());
		
		return bookRepository.save(book);
	}

	public Book deleteBook(int id) { 
		logger.info(Integer.toString(id));
		Book bookToDelete = this.getBookById(id);
		bookRepository.delete(bookToDelete);
		return bookToDelete;
	}

	@Override
	public User loginUser(User user) {
		// TODO Auto-generated method stub
		logger.info(user.toString());
		Optional<User> userOptional = userRepository.findById(user.getUserid());
		if (userOptional.isPresent()) {
			if (user.equals(userOptional.get())) {
				logger.info(userOptional.get().toString());
				@SuppressWarnings("unused")
				User loggedInUser = user; // successful login
				return user;
			} else {
				String exceptionMessage = "Wrong password!";
				logger.warn(exceptionMessage);
				throw new UserNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "Wrong username!";
			logger.warn(exceptionMessage);
			throw new UserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public List<BookDTO> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}

