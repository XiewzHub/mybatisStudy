package com.xiewz.session;

import com.xiewz.config.BoundSql;
import com.xiewz.pojo.Configuration;
import com.xiewz.pojo.MappedStatement;
import com.xiewz.util.GenericTokenParser;
import com.xiewz.util.ParameterMapping;
import com.xiewz.util.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {

    /**
     * 使用jdbc进行数据库查询操作
     * 1. 获取jdbc的相关对象
     * 2. 解析sql语句，用？代替参数#{},同时解析#{}内部参数名并存储
     * 3. 根据参数名匹配参数值并设置
     * 4. 执行并返回
     *
     * @param configuration 配置器
     * @param mappedStatement mapper配置，sql语句映射等
     * @param params 参数
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 底层执行器使用jdbc进行数据库查寻操作
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();

        // 2. 获取sql语句 : select * from user where id = #{id} and username = #{username}
        //转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 4. 设置参数
        //获取到了参数的全路径
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);

        }


        // 5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);

        ArrayList<Object> objects = new ArrayList<>();

        // 6. 封装返回结果集
        while (resultSet.next()){
            Object o =resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {

                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);


            }
            objects.add(o);

        }
        return (List<E>) objects;

    }



    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if(paramterType!=null){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;

    }


    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql,parameterMappings);
        return boundSql;

    }



}
