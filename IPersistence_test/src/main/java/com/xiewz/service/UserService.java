package com.xiewz.service;

import com.xiewz.pojo.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Descriptioin
 * @Author xiewenzhuang
 * @Date 2020/11/9 0009 12:45
 * @Version 1.0
 */

public interface UserService {
    List<User> findList(User user);
}
