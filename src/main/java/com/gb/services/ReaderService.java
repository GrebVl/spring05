package com.gb.services;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gb.model.*;
import com.gb.repository.JpaReaderRepository;

import jakarta.transaction.Transactional;

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

    public Reader getByName(String name) {
        Reader reader = repository.findByName(name);
        if (reader == null){
            throwNotFoundExceptionByName(name);
        }
        return reader;
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
        return repository.save(new Reader(reader.getName(), reader.getAge()));
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
   
}
