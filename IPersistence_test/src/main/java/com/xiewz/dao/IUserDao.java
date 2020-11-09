package com.xiewz.dao;

import com.xiewz.pojo.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    User findByCondition();


}
