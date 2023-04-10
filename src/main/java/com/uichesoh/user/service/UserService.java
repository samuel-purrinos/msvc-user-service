package com.uichesoh.user.service;

import com.uichesoh.user.entities.User;

import java.util.List;
public interface UserService {

    User save(User user);
    List<User> findAll();

    User getUserById(String id);
}
