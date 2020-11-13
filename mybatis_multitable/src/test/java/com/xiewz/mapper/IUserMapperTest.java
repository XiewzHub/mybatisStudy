package com.xiewz.mapper;

import com.xiewz.pojo.Order;
import com.xiewz.pojo.Role;
import com.xiewz.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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


}