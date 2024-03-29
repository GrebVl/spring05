package com.gb.services;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.stereotype.Service;
import com.gb.model.Book;
import com.gb.repository.CrudBookRepository;
import com.gb.repository.JpaBookRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class BookService {

    private final JpaBookRepository repository;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabase() {
        repository.save(new Book("Дневник мага", 2015));
        repository.save(new Book("Алхимик", 2022));
        repository.save(new Book("Пятая гора", 2018));
        repository.save(new Book("Мактуб", 2023));
        repository.save(new Book("Заир", 2019));
    }

    public List<Book> getBooks() {
        Iterable<Book> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Book getByName(String name) {
        return repository.findByName(name);
    }

    public Book getById(long id){
        return repository.findById(id).orElseThrow();
    }

    public void deleteBook(long id){
        repository.delete(getById(id));
    }

    @Transactional
    public void updateBook(long id, String newName, long newReleaseDate) {
        Book book = repository.findById(id).orElseThrow();

        book.setName(newName);
        repository.save(book);

        book.setReleaseDate(newReleaseDate);
        repository.save(book);
    }

    public void createBook(String name, long date){
        repository.save(new Book(name, date));
    }


}
