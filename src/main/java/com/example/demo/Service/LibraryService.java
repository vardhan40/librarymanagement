package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Library;
import com.example.demo.Repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	LibraryRepository libraryRepo;

	public List<Library> getAllLibrarys() {
		return libraryRepo.findAll();
	}

	public Library getLibraryById(int id) {
		return libraryRepo.findById(id).orElse(null);
	}

	public Library saveOrUpdateLibrary(Library library) {
		return libraryRepo.save(library);
	}

	public void deleteLibraryById(int id) {
		libraryRepo.findById(id).orElse(null);
		libraryRepo.deleteById(id);
	}

}
