package com.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gb.model.Reader;

public interface JpaReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByName(String name);
}
