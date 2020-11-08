package com.xiewz.session;

import com.xiewz.pojo.Configuration;
import com.xiewz.pojo.MappedStatement;

import java.util.List;

/**
 * 执行器，底层使用jdbc
 */
public interface Executor {

    /**
     * 通用查询方法
     *
     * @param <E>
     * @param configuration 配置器
     * @param mappedStatement mapper配置，sql语句映射等
     * @param params 参数
     * @return
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
