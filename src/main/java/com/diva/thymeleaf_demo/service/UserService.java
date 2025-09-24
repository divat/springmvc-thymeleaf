package com.diva.thymeleaf_demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.diva.thymeleaf_demo.model.User;
import com.diva.thymeleaf_demo.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository repo;

    public UserService(UserRepository repository){
        this.repo = repository;
    }

    public List<User> getAll() { 
        return repo.findAll(); 
    }

    public User get(Long id) { 
        return repo.findById(id).orElse(null); 
    }
    
    public void save(User user) { 
        repo.save(user);
    }

    public void update(User user) { 
        repo.save(user);
    }

    public void delete(Long id) {
         repo.deleteById(id); 
    }
}