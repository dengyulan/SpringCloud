package com.service;

import com.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();
    String queryPhone(String name);
    boolean addUser(User user);
}
