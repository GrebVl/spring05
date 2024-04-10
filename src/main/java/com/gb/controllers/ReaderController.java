package com.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gb.model.*;
import com.gb.services.ReaderService;

import java.net.URI;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {

    @Autowired
    private ReaderService service;

    @GetMapping("any")
    public String any() {
        return "any";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("reader")
    public String reader() {
        return "reader";
    }

    @GetMapping("auth")
    public String auth() {
        return "auth";
    }
    

    @GetMapping("{id}")
    public ResponseEntity<Reader> findById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Reader>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getReaders());
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader){
        Reader createdReader = service.createReader(reader);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReader.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdReader);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable long id){
        service.deleteReader(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Reader> updateReader(@PathVariable long id, @RequestBody Reader reader){
        return ResponseEntity.ok().body(service.updateReader(id, reader));
    }

}