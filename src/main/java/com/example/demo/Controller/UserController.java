package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Service.BookService;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping("/register")
	public String addUser(@Valid @RequestBody User user) {
		String msg = userService.register(user);
		return msg;
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		String msg = userService.login(user);
		return msg;
	}

	@PutMapping("/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User user) {
		User existingUser = userService.getUserById(id);
		if (existingUser == null) {
			return "Id not found";
		}
		user.setId(id);
		String addUser = userService.updateUser(user);
		return addUser;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		User existingUser = userService.getUserById(id);
		if (existingUser == null) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUserById(id);
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{userId}/books")
	public List<Book> getBooksByUserId(@PathVariable int userId) {
		return userService.getBooksByUserId(userId);
	}

	@PostMapping("/{userId}/books")
	public User addBooksToUser(@PathVariable int userId, @RequestBody List<Integer> bookIds) {
		return userService.addBooksToUser(userId,bookIds);
	}

}
