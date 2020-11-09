package com.xiewz.session;

import com.xiewz.pojo.Configuration;

public class DefautSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration ;

    public DefautSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        SqlSession sqlSession = new DefaultSqlSession(configuration);
        return sqlSession;
    }
}
