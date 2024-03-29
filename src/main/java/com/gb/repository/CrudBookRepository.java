package com.gb.repository;

import org.springframework.data.repository.CrudRepository;

import com.gb.model.Book;

public interface CrudBookRepository extends CrudRepository<Book, Long>{

}