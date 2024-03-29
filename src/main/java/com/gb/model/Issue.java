package com.gb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "issues")
@NoArgsConstructor
public class Issue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "idReader")
    private long idReader;

    @Column(name = "idBook")
    private long idBook;

    @Column(name = "date")
    private LocalDateTime time;

    public Issue(long idReader, long idBook){
        this.idBook = idBook;
        this.idReader = idReader;
        time = LocalDateTime.now();
    }

}
