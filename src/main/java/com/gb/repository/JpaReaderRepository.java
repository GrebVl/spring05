package com.gb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.model.Reader;


@Repository
public interface JpaReaderRepository extends JpaRepository<Reader, Long> {
    Optional<Reader> findByLogin(String login);
}
