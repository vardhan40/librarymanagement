package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NotEmpty
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String username;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(min = 8, message = "Password should have at least 8 characters")
	private String password;
	@ManyToMany
	@JoinTable(name = "users_books", joinColumns = { @JoinColumn(name = "users_id") }, inverseJoinColumns = {
			@JoinColumn(name = "books_id") })
	private List<Book> books;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id,
			@NotEmpty @Size(min = 2, message = "user name should have at least 2 characters") String username,
			@Email @NotEmpty String email,
			@NotEmpty @Size(min = 8, message = "Password should have at least 8 characters") String password,
			List<Book> books) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
