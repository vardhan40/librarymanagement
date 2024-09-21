package com.example.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	BookRepository bookRepo;

	public String register(User user) {
		User validEmail = userRepo.findByEmail(user.getEmail());
		if (validEmail == null) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String encryptedPwd = bcrypt.encode(user.getPassword());
			user.setPassword(encryptedPwd);
			User savedUser = userRepo.save(user);
			return savedUser.getUsername() + " added to the database successfully";
		} else {
			return "User already Exist";
		}
	}

	public User addUser(User user) {
		return userRepo.save(user);
	}

	public String login(User user) {
		User validEmail = userRepo.findByEmail(user.getEmail());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if (validEmail != null) {
			if (bcrypt.matches(user.getPassword(), validEmail.getPassword())) {
				return "Welcome to login page " + validEmail.getUsername();
			} else {
				return "Incorrect password";
			}
		} else {
			return "Incorrect credentials";
		}
	}

	public List<Book> getBooksByUserId(int userId) {
		Optional<User> optionalUser = userRepo.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new RuntimeException("User not found");
		}
		User user = optionalUser.get();
		return user.getBooks();
	}

	public User addBooksToUser(int userId, List<Integer> bookIds) {
		Optional<User> optionalUser = userRepo.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new RuntimeException("User not found");
		}

		User user = optionalUser.get();
		Set<Book> books = bookIds.stream()
				.map(bookId -> bookRepo.findById(bookId)
						.orElseThrow(() -> new RuntimeException("Book not found: " + bookId)))
				.collect(Collectors.toSet());

		user.getBooks().addAll(books);
		return userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User getUserById(int id) {
		return userRepo.findById(id).orElse(null);
	}

	public String updateUser(User user) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPwd = bcrypt.encode(user.getPassword());
		user.setPassword(encryptedPwd);
		User savedUser = userRepo.save(user);
		return savedUser.getUsername() + " updated to the database successfully";
	}

	public void deleteUserById(int id) {
		userRepo.findById(id).orElse(null);
		userRepo.deleteById(id);
	}

}
