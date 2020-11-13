package com.xiewz.mapper;

import com.xiewz.pojo.Order;
import com.xiewz.pojo.Role;
import com.xiewz.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserMapper {

    public List<User> findAll();

    // 查询用户与角色信息
    List<User> findAllUserAndRole();

    // 添加用户
    @Insert("insert into user values(#{id},#{username},#{password},#{birthday})")
    void addUser(User user);

    //更新用户
    @Update("update user set username = #{username} where id = #{id}")
    public void updateUser(User user);

    //查询用户
    @Select("select * from user")
    public List<User> selectUser();

    //删除用户
    @Delete("delete from user where id = #{id}")
    public void deleteUser(Integer id);

    //根据id查询用户
//    @Options(useCache = true)
    @Select({"select * from user where id = #{id}"})
    public User findUserById(Integer id);

}
