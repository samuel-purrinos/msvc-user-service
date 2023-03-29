package com.uichesoh.user.service;

import com.uichesoh.user.entity.User;
import com.uichesoh.user.exception.ResourceNotFoundException;
import com.uichesoh.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
