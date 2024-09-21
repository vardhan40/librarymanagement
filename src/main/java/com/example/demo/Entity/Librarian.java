package com.example.demo.Entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "librarians")
public class Librarian {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@ManyToMany(mappedBy = "librarians")
	@JsonIgnore
	private List<Library> librarys;

	public Librarian() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Librarian(int id, String name, List<Library> librarys) {
		super();
		this.id = id;
		this.name = name;
		this.librarys = librarys;
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

	public List<Library> getLibrarys() {
		return librarys;
	}

	public void setLibrarys(List<Library> librarys) {
		this.librarys = librarys;
	}

}
