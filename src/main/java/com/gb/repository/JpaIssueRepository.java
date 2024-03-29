package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gb.model.Issue;

public interface JpaIssueRepository extends JpaRepository<Issue, Long> {
    Issue findByIdBook(long idBook);
    Issue findByIdReader(long idReader);
}
