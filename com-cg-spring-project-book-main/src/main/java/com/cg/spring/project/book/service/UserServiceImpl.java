package com.cg.spring.project.book.service;

import java.util.List;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.project.book.exception.UserAlreadyExistsException;
import com.cg.spring.project.book.exception.UserNotFoundException;
import com.cg.spring.project.book.model.Book;
import com.cg.spring.project.book.model.User;
import com.cg.spring.project.book.repository.BookRepository;
import com.cg.spring.project.book.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository UserRepository;
	@Autowired
    private BookRepository bookRepository;
	User loggedInUser;

	public List<User> getAllUsers() {
		List<User> userList = UserRepository.findAll();
		if (userList.isEmpty()) {
			String exceptionMessage = "Users don't exist in the database.";
			logger.warn(exceptionMessage);
			throw new UserNotFoundException(exceptionMessage);
		} else {
			logger.info("List returned successfully.");
			return userList;
		}
	}

	public User registerUser(User user) {
		logger.info(user.toString());
		Optional<User> userOptional = UserRepository.findById(user.getUserid());
		if (userOptional.isEmpty()) {
			return UserRepository.save(user);
		} else {
			String exceptionMessage = "User with username " + user.getUsername() + " already exists.";
			throw new UserAlreadyExistsException(exceptionMessage);
		}
	}

//        public User loginUser(User user) {
//			logger.info(user.toString());
//			Optional<User> userOptional = UserRepository.findById(user.getUserid());
//			if (userOptional.isPresent()) {
//				if (user.equals(userOptional.get())) {
//					logger.info(userOptional.get().toString());
//					loggedInUser = user; // successful login
//					return user;
//				} else {
//					String exceptionMessage = "Wrong password!";
//					logger.warn(exceptionMessage);
//					throw new UserNotFoundException(exceptionMessage);
//				}
//			} else {
//				String exceptionMessage = "Wrong username!";
//				logger.warn(exceptionMessage);
//				throw new UserNotFoundException(exceptionMessage);
//			}
//		}

	public String logoutUser(String username)  {
		if (loggedInUser.getUsername().equals(username)) {

			loggedInUser = null;
			return null;
		} else {
			String exceptionMessage = "User with username " + username + " is not logged in.";
			logger.warn(exceptionMessage);
		//	throw new Exception(exceptionMessage);
		}
		return username;
		
	}

	public User updateUser(User user) {
		Optional<User> userOptional = UserRepository.findById(user.getUserid());
		if (userOptional.isPresent()) {
			logger.info(userOptional.get().toString());
			return UserRepository.save(user);
		} else {
			String exceptionMessage = "User with username " + user.getUsername() + " not found!";
			logger.warn(exceptionMessage);
			throw new UserNotFoundException(exceptionMessage);
		}
	}

	@Override
		public User loginUser(User user) {
			// TODO Auto-generated method stub
		logger.info(user.toString());
		Optional<User> userOptional = UserRepository.findById(user.getUserid());
		if (userOptional.isPresent()) {
			if (user.equals(userOptional.get())) {
				logger.info(userOptional.get().toString());
				loggedInUser = user; // successful login
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
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> bookList = bookRepository.findAll();
		if (bookList.isEmpty()) {
			String exceptionMessage = "Users don't exist in the database.";
			logger.warn(exceptionMessage);
			throw new UserNotFoundException(exceptionMessage);
		} else {
			logger.info("List returned successfully.");
			return bookList;
		
		
		}
	}

}
			  