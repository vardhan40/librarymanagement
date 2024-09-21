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

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Library;
import com.example.demo.Service.AuthorService;
import com.example.demo.Service.BookService;
import com.example.demo.Service.LibraryService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@Autowired
	LibraryService libraryService;

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> authors = bookService.getAllBooks();
		return ResponseEntity.ok(authors);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(book);
	}

	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		List<Author> authors = new ArrayList<Author>();
		for (Author author : book.getAuthors()) {
			Author foundAuthor = authorService.getAuthorById(author.getId());
			if (foundAuthor == null) {
				return ResponseEntity.notFound().build();
			}
			authors.add(foundAuthor);
		}
		book.setAuthors(authors);

		List<Library> librarys = new ArrayList<Library>();
		for (Library library : book.getLibrarys()) {
			Library foundLibrary = libraryService.getLibraryById(library.getId());
			if (foundLibrary == null) {
				return ResponseEntity.notFound().build();
			}
			librarys.add(foundLibrary);
		}
		book.setLibrarys(librarys);
		Book createdbook = bookService.saveOrUpdateBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdbook);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updatebook(@PathVariable int id, @RequestBody Book book) {
		Book existingBook = bookService.getBookById(id);
		if (existingBook == null) {
			return ResponseEntity.notFound().build();
		}
		book.setId(id);
		List<Author> authors = new ArrayList<Author>();
		for (Author author : book.getAuthors()) {
			Author foundAuthor = authorService.getAuthorById(author.getId());
			if (foundAuthor == null) {
				return ResponseEntity.notFound().build();
			}
			authors.add(foundAuthor);
		}
		book.setAuthors(authors);

		List<Library> librarys = new ArrayList<Library>();
		for (Library library : book.getLibrarys()) {
			Library foundLibrary = libraryService.getLibraryById(library.getId());
			if (foundLibrary == null) {
				return ResponseEntity.notFound().build();
			}
			librarys.add(foundLibrary);
		}
		book.setLibrarys(librarys);
		Book createdbook = bookService.saveOrUpdateBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdbook);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletebook(@PathVariable int id) {
		Book existingAuthor = bookService.getBookById(id);
		if (existingAuthor == null) {
			return ResponseEntity.notFound().build();
		}
		bookService.deleteBookById(id);
		return ResponseEntity.notFound().build();
	}

}
