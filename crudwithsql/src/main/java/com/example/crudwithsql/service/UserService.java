package com.example.crudwithsql.service;

import com.example.crudwithsql.entity.AppUser;
import com.example.crudwithsql.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService implements UserDetailsService {
    @Autowired
    private userRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) {

        AppUser user = repository.findByUsername(username)
                .orElse(null);

        if(user==null){
            return null;
        }


        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }
}
