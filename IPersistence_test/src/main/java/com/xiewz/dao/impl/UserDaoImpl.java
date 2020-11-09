package com.xiewz.dao.impl;

import com.xiewz.dao.IUserDao;
import com.xiewz.io.Resources;
import com.xiewz.pojo.User;
import com.xiewz.session.SqlSession;
import com.xiewz.session.SqlSessionFactory;
import com.xiewz.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() throws Exception{
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsSteam);
        SqlSession sqlSession = build.openSession();

        return sqlSession.selectList("user.selectOne");
    }

    @Override
    public User findByCondition(User query) throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsSteam);
        SqlSession sqlSession = build.openSession();


        User userOne = sqlSession.selectOne("user.selectOne", query);
        return userOne;
    }
}
