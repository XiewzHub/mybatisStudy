package com.xiewz.session;

import com.xiewz.pojo.Configuration;
import com.xiewz.pojo.MappedStatement;

import java.util.List;

public class SimpleExecutor implements Executor {

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 底层执行器使用jdbc进行数据库查寻操作


        return null;
    }
}
