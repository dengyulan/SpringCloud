package com.service;

import com.dao.UserDao;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class UserServiceImpl2 implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll2();
    }

    @Override
    public String queryPhone(String name) {
        return userDao.queryPhone(name);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }
}
