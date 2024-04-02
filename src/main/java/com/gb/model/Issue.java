package com.gb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "issues")
@NoArgsConstructor
public class Issue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idReader")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "idBook")
    private Book book;

    @Column(name = "issuedAt")
    private LocalDateTime issuedAt;

    @Column(name = "returnedAt")
    private LocalDateTime returnedAt;

    public Issue(Book book, Reader reader){
        this.reader = reader;
        this.book = book;
        this.issuedAt = LocalDateTime.now();
    }

}
