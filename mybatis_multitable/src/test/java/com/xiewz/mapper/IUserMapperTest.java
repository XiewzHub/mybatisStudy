package com.xiewz.mapper;

import com.xiewz.pojo.Order;
import com.xiewz.pojo.Role;
import com.xiewz.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class IUserMapperTest {

    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> userAndRole = mapper.findUserAndOrder();
        System.out.println(userAndRole);
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user.getUsername());
            System.out.println(user.getOrderList());
            System.out.println("==========");
        }
    } @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> allUserAndRole = mapper.findAllUserAndRole();
        for (User user : allUserAndRole) {
            System.out.println(user);
            System.out.println("==========");
        }
    }

    private IUserMapper userMapper ;
    private IOrderMapper orderMapper ;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        userMapper = sqlSession.getMapper(IUserMapper.class);
        orderMapper = sqlSession.getMapper(IOrderMapper.class);
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setId(3);
        user.setUsername("测试数据");
        user.setPassword("123");
        user.setBirthday("2020-01-01");

        userMapper.addUser(user);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(3);
        user.setUsername("修改了测试数据");

        userMapper.updateUser(user);

    }

    @Test
    public void selectUser(){
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void deleteUser(){
        userMapper.deleteUser(3);
    }

    @Test
    public void oneToOne(){
        List<Order> userAndOrder = orderMapper.findUserAndOrder();
        for (Order order : userAndOrder) {
            System.out.println(order);
        }
    }
    @Test
    public void oneToMany(){
        List<User> userList = userMapper.findAll();

        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void manyToMany(){
        List<User> userList = userMapper.findAllUserAndRole();

        for (User user : userList) {
            System.out.println(user);
        }
    }


}