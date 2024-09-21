package com.example.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "librarys")
public class Library {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@ManyToMany
	@JoinTable(name = "librarys_librarians", joinColumns = { @JoinColumn(name = "librarys_id") }, inverseJoinColumns = {
			@JoinColumn(name = "librarians_id") })
	private List<Librarian> librarians;

	@ManyToMany(mappedBy = "librarys")
	@JsonIgnore
	private List<Book> books;

	public Library() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Library(int id, String name, List<Librarian> librarians, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.librarians = librarians;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Librarian> getLibrarians() {
		return librarians;
	}

	public void setLibrarians(List<Librarian> librarians) {
		this.librarians = librarians;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
