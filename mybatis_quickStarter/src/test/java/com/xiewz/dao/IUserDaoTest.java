package com.xiewz.dao;

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

public class IUserDaoTest {

    @Test
    public void test1() throws IOException {
        // 1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.生产sqlSession
        // 默认开启一个事务，但是该事务不会自动提交
        //在进行增删改操作时，要手动提交事务
        SqlSession sqlSession = sessionFactory.openSession();

        // 4.sqlSession调用方法：查询所有selectList 查询单个：selectOne 添加：insert  修改：update 删除：delete
        List<User> users = sqlSession.selectList("com.xiewz.dao.IUserDao.findAll");

        for (User user : users) {
            System.out.println(user);
        }

        // 5. 释放资源
        sqlSession.close();
    }


    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4);
        user.setUsername("lucy");
        sqlSession.update("com.xiewz.dao.IUserDao.updateUser",user);
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        // 1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        SqlSession sqlSession = sessionFactory.openSession();

        int[] ids = {1,2};
        // 获取动态代理对象 代理实现了IUserDao接口
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> userList = mapper.findByIds(ids);

        for (User user : userList) {
            System.out.println(user);
        }

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        User user1 = new User();
        user1.setId(4);
        user1.setUsername("lucy");


        List<User> all = mapper.findByCondition(user1);
        for (User user : all) {
            System.out.println(user);
        }


    }

}