package com.gb.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gb.model.Reader;
import com.gb.repository.JpaReaderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomReaderDetailService implements UserDetailsService {

    private final JpaReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("Ищем читателя " + username);
        Reader reader = readerRepository.findByLogin(username)
                .orElseThrow(() -> 
                new UsernameNotFoundException("Читатель " + username + " не найден"));
        //System.out.println("нашли" +reader);
        return new User(reader.getLogin(), reader.getPassword(), List.of(
            new SimpleGrantedAuthority(reader.getRole())
        ));
    }

}
