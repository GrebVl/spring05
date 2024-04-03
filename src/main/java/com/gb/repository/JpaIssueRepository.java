package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.model.Issue;

@Repository
public interface JpaIssueRepository extends JpaRepository<Issue, Long> {
}
