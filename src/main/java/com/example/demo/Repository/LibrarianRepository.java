package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian,Integer>{

}
