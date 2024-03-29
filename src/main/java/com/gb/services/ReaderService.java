package com.gb.services;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import com.gb.model.*;
import com.gb.repository.JpaReaderRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Service
public class ReaderService {

     private final JpaReaderRepository repository;

    @EventListener(ContextRefreshedEvent.class)
    public void onCreateDatabase() {
        repository.save(new Reader("Константин", 15));
        repository.save(new Reader("Василий", 18));
        repository.save(new Reader("Евгуний", 51));
        repository.save(new Reader("Алексей", 30));
    }

    public List<Reader> getReaders() {
        Iterable<Reader> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Reader getByName(String name) {
        return repository.findByName(name);
    }

    public Reader getById(long id){
        return repository.findById(id).orElseThrow();
    }

    public void deleteReader(long id){
        repository.delete(getById(id));
    }

    @Transactional
    public void updateReader(long id, String newName, int newAge) {
        Reader reader = repository.findById(id).orElseThrow();

        reader.setName(newName);
        repository.save(reader);

        reader.setAge(newAge);
        repository.save(reader);
    }

    public void createReader(String name, int age){
        repository.save(new Reader(name, age));
    }
   
}
