package com.xiewz.service.impl;

import com.xiewz.pojo.User;
import com.xiewz.service.UserService;
import org.junit.Test;

import java.util.List;


public class UserServiceImplTest {



    @Test
    public void findList() {
        UserService userService = new UserServiceImpl();
        User query = new User();
        query.setId(1);
        query.setUsername("lucy");

        List<User> list = userService.findList(query);
        for (User user : list) {
            System.out.println(user);
        }
    }
}