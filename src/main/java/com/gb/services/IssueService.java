package com.gb.services;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gb.model.*;
import com.gb.repository.*;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class IssueService{
    
    public static final String NOT_FOUND_MESSAGE = "Не удалось найти запись о выдаче с значение ";
  
    private final JpaIssueRepository issueRepository;
    private final JpaBookRepository bookRepository;
    private final JpaReaderRepository readerRepository;

    public Issue findById(long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue == null) {
            throwNotFoundExceptionById(id);
        }
        return issue;
    }

    public void deleteById(long id) {
        checkExistsById(id);
        issueRepository.deleteById(id);
    }

    public Issue saveIssue(Issue issue) {

        checkBookExists(issue);
        checkReaderExists(issue);

        Issue savedIssue = createIssueAndSave(issue);
        fillIssue(savedIssue);

        return savedIssue;
    }

    public Issue returnBook(long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue == null) {
            throwNotFoundExceptionById(id);
        }

        if (issue.getReturnedAt() == null) {
            issue.setReturnedAt(LocalDateTime.now());
            return issueRepository.save(issue);
        }
        return issue;
    }

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    private void fillIssue(Issue issue) {
        issue.setBook(bookRepository.findById(issue.getBook().getId()).orElse(null));
        issue.setReader(readerRepository.findById(issue.getReader().getId()).orElse(null));
    }

    private Issue createIssueAndSave(Issue issue) {
        return issueRepository.save(new Issue(issue.getBook(), issue.getReader()));
    }

    private void checkExistsById(long id) {
        if (!issueRepository.existsById(id)) {
            throwNotFoundExceptionById(id);
        }
    }


    private void checkReaderExists(Issue issue) {
        if (issue.getReader() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "не указано значение для поля reader");
        }
        if (!readerRepository.existsById(issue.getReader().getId())) {
            throwNotFoundExceptionById(issue.getReader().getId(), ReaderService.NOT_FOUND_MESSAGE);
        }
    }

    private void checkBookExists(Issue issue) {
        if (issue.getBook() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "не указано значение для поля book");
        }
        if (!bookRepository.existsById(issue.getBook().getId())) {
            throwNotFoundExceptionById(issue.getBook().getId(), BookService.NOT_FOUND_MESSAGE);
        }
    }

    private void throwNotFoundExceptionById(long id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + id);
    }

    private void throwNotFoundExceptionById(long id, String message) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message + id);
    }
}
