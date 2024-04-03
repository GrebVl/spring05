package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.model.Book;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long>{
    Book findByName(String name);
}
