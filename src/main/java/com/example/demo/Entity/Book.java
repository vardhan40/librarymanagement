package com.example.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;

	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "books_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "books_librarys", joinColumns = { @JoinColumn(name = "books_id") }, inverseJoinColumns = {
			@JoinColumn(name = "librarys_id") })
	private List<Library> librarys;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String title, List<com.example.demo.Entity.User> users, List<Author> authors,
			List<Library> librarys) {
		super();
		this.id = id;
		this.title = title;
		this.users = users;
		this.authors = authors;
		this.librarys = librarys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Library> getLibrarys() {
		return librarys;
	}

	public void setLibrarys(List<Library> librarys) {
		this.librarys = librarys;
	}

}
