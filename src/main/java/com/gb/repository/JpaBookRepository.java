package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gb.model.Book;

public interface JpaBookRepository extends JpaRepository<Book, Long>{
    Book findByName(String name);
}
