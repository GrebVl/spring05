package com.gb.services;

import lombok.RequiredArgsConstructor;


import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import com.gb.controllers.IssueRequest;
import com.gb.model.*;
import com.gb.repository.*;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Service
public class IssueService{

    private final JpaIssueRepository repository;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabase() {
        repository.save(new Issue(0, 1));
        repository.save(new Issue(1, 0));
        repository.save(new Issue(2, 2));
    }

    public List<Issue> getIssues() {
        Iterable<Issue> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Issue getByBook(long idBook) {
        return repository.findByIdBook(idBook);
    }

    public Issue getByReader(long idReader) {
        return repository.findByIdReader(idReader);
    }

    public Issue getById(long id){
        return repository.findById(id).orElseThrow();
    }

    public void deleteIssue(long id){
        repository.delete(getById(id));
    }

    @Transactional
    public void updateIssue(long id, long newIdBook, long newIdReader) {
        Issue issue = repository.findById(id).orElseThrow();

        issue.setIdBook(newIdBook);
        repository.save(issue);

        issue.setIdReader(newIdReader);
        repository.save(issue);
    }

    public void createIssue(long idBook, long idReader){
        repository.save(new Issue(idBook, idReader));
    }
}
