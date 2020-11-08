package com.xiewz.session;

import com.xiewz.pojo.Configuration;
import com.xiewz.pojo.MappedStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        Executor executor = new   SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> query = executor.query(configuration, mappedStatement, params);

        return (List<E>) query;
    }

    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if(objects == null || objects.size() != 1){
            throw new RuntimeException("查询结果为空或结果过多");
        }
        return (T) objects.get(0);
    }
}
