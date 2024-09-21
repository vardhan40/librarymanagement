package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Librarian;
import com.example.demo.Repository.LibrarianRepository;

@Service
public class LibrarianService {
	@Autowired
	LibrarianRepository librarianRepo;

	public List<Librarian> getAllLibrarians() {
		return librarianRepo.findAll();
	}

	public Librarian getLibrarianById(int id) {
		return librarianRepo.findById(id).orElse(null);
	}

	public Librarian saveOrUpdateLibrarian(Librarian librarian) {
		return librarianRepo.save(librarian);
	}

	public void deleteLibrarianById(int id) {
		librarianRepo.findById(id).orElse(null);
		librarianRepo.deleteById(id);
	}
}
