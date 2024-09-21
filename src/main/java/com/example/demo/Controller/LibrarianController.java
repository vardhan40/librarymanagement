package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Librarian;
import com.example.demo.Service.LibrarianService;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {
	@Autowired
	LibrarianService librarianService;

	@GetMapping
	public ResponseEntity<List<Librarian>> getAllLibrarians() {
		List<Librarian> Librarians = librarianService.getAllLibrarians();
		return ResponseEntity.ok(Librarians);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Librarian> getLibrarianById(@PathVariable int id) {
		Librarian librarian = librarianService.getLibrarianById(id);
		if (librarian == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(librarian);
	}

	@PostMapping
	public ResponseEntity<Librarian> saveLibrarian(@RequestBody Librarian librarian) {
		Librarian createdLibrarian = librarianService.saveOrUpdateLibrarian(librarian);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrarian);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Librarian> updateLibrarian(@PathVariable int id, @RequestBody Librarian librarian) {
		Librarian existingLibrarian = librarianService.getLibrarianById(id);
		if (existingLibrarian == null) {
			return ResponseEntity.notFound().build();
		}
		librarian.setId(id);
		librarianService.saveOrUpdateLibrarian(librarian);
		return ResponseEntity.ok(librarian);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLibrarian(@PathVariable int id) {
		Librarian existingLibrarian = librarianService.getLibrarianById(id);
		if (existingLibrarian == null) {
			return ResponseEntity.notFound().build();
		}
		librarianService.deleteLibrarianById(id);
		return ResponseEntity.notFound().build();
	}
}
