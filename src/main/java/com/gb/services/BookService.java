package com.gb.services;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gb.model.Book;
import com.gb.repository.CrudBookRepository;
import com.gb.repository.JpaBookRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class BookService {

    public static final String NOT_FOUND_MESSAGE = "Не удалось найти книгу с заданным значение = ";

    private final JpaBookRepository repository;


    public List<Book> getBooks() {
        Iterable<Book> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Book getByName(String name) {
        Book book = repository.findByName(name);
        if (book == null){
            throwNotFoundExceptionByName(name);
        }
        return book;
    }

    public Book getById(long id){
        Book book = repository.findById(id).orElse(null);
        if (book == null){
            throwNotFoundExceptionById(id);
        }
        return book;
    }

    public void deleteBook(long id){
        checkExistsById(id);
        repository.deleteById(id);
    }

    public Book updateBook(long id, Book book) {
        checkExistsById(id);
        book.setId(id);
        return repository.save(book);
    }

    public Book createBook(Book book){
        return repository.save(new Book(book.getName(), book.getReleaseDate()));
    }

    private void checkExistsById(long id){
        if(!repository.existsById(id)){
            throwNotFoundExceptionById(id);
        }
    }

    private void throwNotFoundExceptionById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + id);
    }

    private void throwNotFoundExceptionByName(String name){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + name);
    }

}
