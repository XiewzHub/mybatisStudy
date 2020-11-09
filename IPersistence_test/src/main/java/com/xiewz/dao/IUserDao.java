package com.xiewz.dao;

import com.xiewz.pojo.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll() throws Exception;

    User findByCondition(User query) throws Exception;


}
