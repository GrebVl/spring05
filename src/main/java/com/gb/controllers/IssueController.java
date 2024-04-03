package com.gb.controllers;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gb.model.Issue;
import com.gb.services.IssueService;
import org.springframework.stereotype.Controller;


@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;
@GetMapping("{id}")
    public ResponseEntity<Issue> findById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Issue>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue){
        Issue createdIssue = service.saveIssue(issue);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdIssue.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdIssue);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Issue> returnBook(@PathVariable long id){
        return ResponseEntity.ok().body(service.returnBook(id));
    }
}
