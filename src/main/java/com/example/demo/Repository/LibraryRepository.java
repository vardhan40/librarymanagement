package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Library;

public interface LibraryRepository extends JpaRepository<Library,Integer>{

}
