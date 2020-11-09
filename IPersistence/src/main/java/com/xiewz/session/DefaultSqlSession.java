package com.xiewz.session;

import com.xiewz.pojo.Configuration;
import com.xiewz.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> query = executor.query(configuration, mappedStatement, params);

        return (List<E>) query;
    }

    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if (objects == null || objects.size() != 1) {
            throw new RuntimeException("查询结果为空或结果过多");
        }
        return (T) objects.get(0);
    }

    @Override
    public <T> T getMapper(Class<? extends T> mapperClass) {
        // 使用JDK动态代理 创建dao层的代理对象
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 //根据不同情况，来调用selctList或者selectOne
                // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名：findAll
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                // args 对应参数 user

                // 获取方法的返回值类型，
                Type returnType = method.getGenericReturnType();
                // note： 判断返回值是否含有泛型 如果有泛型，则认为它是list对象
                // 判断是否进行 泛型类型参数化
                if (returnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId);
                    return objects;
                }
                return selectOne(statementId, args);

            }
        });
        return (T) proxyInstance;
    }


}
