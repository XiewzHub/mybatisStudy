package com.xiewz.service.impl;

import com.xiewz.dao.IUserDao;
import com.xiewz.io.Resources;
import com.xiewz.pojo.User;
import com.xiewz.service.UserService;
import com.xiewz.session.SqlSession;
import com.xiewz.session.SqlSessionFactory;
import com.xiewz.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
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

    @Test
    public void test() throws Exception {
        // 配置文件流
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsSteam);
        SqlSession sqlSession = build.openSession();

        User query = new User();
        query.setId(1);
        query.setUsername("lucy");


        /*User userOne = sqlSession.selectOne("user.selectOne", query);
        System.out.println(userOne);

        List<User> users = sqlSession.selectList("user.selectList");
        for (User user : users) {
            System.out.println(user);
        }*/

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        User user = mapper.findByCondition(query);
        System.out.println(user);

        System.out.println("--------------------");
        List<User> all = mapper.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}