package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gb.model.Issue;

public interface JpaIssueRepository extends JpaRepository<Issue, Long> {
}
