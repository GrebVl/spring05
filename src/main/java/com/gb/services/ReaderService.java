package com.gb.services;

import lombok.RequiredArgsConstructor;

import org.hibernate.event.spi.RefreshEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gb.model.*;
import com.gb.repository.JpaReaderRepository;


import java.util.List;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Service
public class ReaderService {

    public static final String NOT_FOUND_MESSAGE = "Не удалось найти читателя с заданным значением = ";


     private final JpaReaderRepository repository;

    public List<Reader> getReaders() {
        Iterable<Reader> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }


    public Reader getById(long id){
        Reader reader = repository.findById(id).orElse(null);
        if (reader == null){
            throwNotFoundExceptionById(id);
        }
        return reader;
    }

    public void deleteReader(long id){
        checkExistsById(id);
        repository.delete(getById(id));
    }

    public Reader updateReader(long id, Reader reader) {
        checkExistsById(id);
        reader.setId(id);
        return repository.save(reader);
    }

    public Reader createReader(Reader reader){
        return repository.save(reader);
    }

    private void checkExistsById(long id){
        if(!repository.existsById(id)){
            throwNotFoundExceptionById(id);
        }
    }

    private void throwNotFoundExceptionById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + id);
    }

    private void throwNotFoundExceptionByName(String name){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + name);
    }

    @EventListener(ContextRefreshedEvent.class)
    private void createdStarterDate(){
        System.out.println("test");

        Reader reader1 = new Reader("admin", 20);
        reader1.setLogin("admin");
        reader1.setPassword("admin");
        reader1.setRole("admin");

        Reader reader2 = new Reader("user", 20);
        reader2.setLogin("user");
        reader2.setPassword("user");
        reader2.setRole("user");

        createReader(reader1);
        createReader(reader2);

    }
   
}
