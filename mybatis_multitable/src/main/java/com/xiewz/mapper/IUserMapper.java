package com.xiewz.mapper;

import com.xiewz.pojo.Order;
import com.xiewz.pojo.Role;
import com.xiewz.pojo.User;

import java.util.List;

public interface IUserMapper {

    public List<User> findAll();

    // 查询用户与角色信息
    List<User> findAllUserAndRole();
}
