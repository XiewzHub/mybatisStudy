package com.xiewz.dao;

import com.xiewz.pojo.User;

import java.util.List;

public interface IUserDao {
    //多值查询：演示foreach
    public List<User> findByIds(int[] ids);

    /**
     * 查所有用户
     * @return
     */
    public List<User> findAll();

    List<User> findByCondition(User user1);
}
