package com.gb.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gb.model.Book;
import com.gb.services.BookService;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("ui")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService service;

    @GetMapping("newBook")
    public void createBook(@RequestParam String name, @RequestParam long date) {
        service.createBook(name, date);
    }
    

    @GetMapping("books")
    public List<Book> getAllBook(Model model){
        return service.getBooks();
    }

    @GetMapping("bookName")
    public Book getBook(@RequestParam String name) {
        return service.getByName(name);
    }

    @GetMapping("bookId")
    public Book getId(@RequestParam Long id) {
        return service.getById(id);
    }
    

    @GetMapping("updateBook")
    public void getUpdate(@RequestParam long id, @RequestParam String name, @RequestParam int newDate) {
        service.updateBook(id, name, newDate);
    }

    @GetMapping("deleteBook")
    public String deleteBook(@RequestParam long id) {
        service.deleteBook(id);
        return "book delete";
    }
}
