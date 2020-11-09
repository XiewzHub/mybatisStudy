package com.xiewz.session;

import java.util.List;

/**
 * 核心sql操作接口
 * 1. 提供cuid方法
 * 2. 提供getMapper方法（代理模式）
 */
public interface SqlSession {
    //查询所有
    public <E> List<E> selectList(String statementid, Object... params) throws Exception;

    //根据条件查询单个
    public <T> T selectOne(String statementid,Object... params) throws Exception;

    public <T> T getMapper(Class<? extends T> mapperClass);
}
