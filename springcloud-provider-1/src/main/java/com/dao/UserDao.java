package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    List<User> queryAll();
    List<User> queryAll2();
    String queryPhone(String name);
    boolean addUser(User user);
}
