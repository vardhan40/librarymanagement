package com.example.demo.Controller;

import java.util.ArrayList;
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
import com.example.demo.Entity.Library;
import com.example.demo.Service.LibrarianService;
import com.example.demo.Service.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
	@Autowired
	LibraryService libraryService;

	@Autowired
	LibrarianService librarianService;

	@GetMapping
	public ResponseEntity<List<Library>> getAllLibrarys() {
		List<Library> authors = libraryService.getAllLibrarys();
		return ResponseEntity.ok(authors);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Library> getLibraryById(@PathVariable int id) {
		Library library = libraryService.getLibraryById(id);
		if (library == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(library);
	}

	@PostMapping
	public ResponseEntity<Library> saveLibrary(@RequestBody Library library) {
		List<Librarian> librarians = new ArrayList<Librarian>();
		for (Librarian librarian : library.getLibrarians()) {
			Librarian foundlibrarian = librarianService.getLibrarianById(librarian.getId());
			if (foundlibrarian == null) {
				return ResponseEntity.notFound().build();
			}
			librarians.add(foundlibrarian);
		}
		library.setLibrarians(librarians);
		Library createdLibrary = libraryService.saveOrUpdateLibrary(library);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Library> updateLibrary(@PathVariable int id, @RequestBody Library library) {
		Library existingLibrary = libraryService.getLibraryById(id);
		if (existingLibrary == null) {
			return ResponseEntity.notFound().build();
		}
		library.setId(id);
		List<Librarian> librarians = new ArrayList<Librarian>();
		for (Librarian librarian : library.getLibrarians()) {
			Librarian foundlibrarian = librarianService.getLibrarianById(librarian.getId());
			if (foundlibrarian == null) {
				return ResponseEntity.notFound().build();
			}
			librarians.add(foundlibrarian);
		}
		library.setLibrarians(librarians);
		Library createdLibrary = libraryService.saveOrUpdateLibrary(library);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLibrary(@PathVariable int id) {
		Library existingAuthor = libraryService.getLibraryById(id);
		if (existingAuthor == null) {
			return ResponseEntity.notFound().build();
		}
		libraryService.deleteLibraryById(id);
		return ResponseEntity.notFound().build();
	}
}
