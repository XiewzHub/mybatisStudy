package com.xiewz.session;

import com.xiewz.config.XMLConfigBuilder;
import com.xiewz.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    SqlSessionFactory build(InputStream in ) throws PropertyVetoException, DocumentException {
        // 第一：使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        // 第二：创建sqlSessionFactory对象：工厂类：生产sqlSession:会话对象
        SqlSessionFactory sqlSessionFactory = new DefautSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
