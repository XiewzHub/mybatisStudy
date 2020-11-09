package com.xiewz.service.impl;

import com.xiewz.io.Resources;
import com.xiewz.pojo.User;
import com.xiewz.service.UserService;
import com.xiewz.session.SqlSession;
import com.xiewz.session.SqlSessionFactory;
import com.xiewz.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Descriptioin
 * @Author xiewenzhuang
 * @Date 2020/11/9 0009 12:45
 * @Version 1.0
 */

public class UserServiceImpl implements UserService {


    @Override
    public List<User> findList(User user) {
        // 配置文件流
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsSteam);
             sqlSession = build.openSession();
            return  sqlSession.selectList("user.selectList",user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("错误",e);
        }
    }
}
