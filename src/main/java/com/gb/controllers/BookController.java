package com.gb.controllers;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gb.model.Book;
import com.gb.services.BookService;
import org.springframework.ui.Model;



@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService service;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestParam Book book) {
        Book createdBook = service.createBook(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBook);
    }
    

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBook(Model model){
        return ResponseEntity.ok().body(service.getBooks());
    }

    @GetMapping("{name}")
    public ResponseEntity<Book> getName(@RequestParam String name) {
        return ResponseEntity.ok().body(service.getByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getId(@RequestParam Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }
    

    @PutMapping()
    public ResponseEntity<Book> getUpdate(@RequestParam long id, @RequestParam Book book) {
        return ResponseEntity.ok().body(service.updateBook(id, book));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@RequestParam long id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
