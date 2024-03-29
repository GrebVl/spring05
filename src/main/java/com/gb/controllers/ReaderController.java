package com.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gb.model.*;
import com.gb.services.ReaderService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("ui")
@RequiredArgsConstructor
public class ReaderController {

    @Autowired
    private ReaderService service;

    @GetMapping("newReader")
    public void createReader(@RequestParam String name, @RequestParam int age) {
        service.createReader(name, age);
    }
    

    @GetMapping("readers")
    public List<Reader> getAllReader(Model model){
        return service.getReaders();
    }

    @GetMapping("readerName")
    public Reader getReader(@RequestParam String name) {
        return service.getByName(name);
    }

    @GetMapping("readerId")
    public Reader getId(@RequestParam Long id) {
        return service.getById(id);
    }
    

    @GetMapping("updateReader")
    public void getUpdate(@RequestParam long id, @RequestParam String name, @RequestParam int age) {
        service.updateReader(id, name, age);
    }

    @GetMapping("deleteReader")
    public String deleteReader(@RequestParam long id) {
        service.deleteReader(id);
        return "reader delete";
    }

}
