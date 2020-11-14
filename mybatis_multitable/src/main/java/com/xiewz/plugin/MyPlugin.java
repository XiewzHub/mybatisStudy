package com.xiewz.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;
@Intercepts({
        // 拦截StatementHandler.prepare(Connection,Integer)
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {
    /**
     * 拦截方法： 只要被拦截的目标对象的目标方法被执行时，每次都会执行intercept方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截了方法  增强。。。");
        // 原方法执行
        return invocation.proceed();
    }

    /**
     * 主要是为了吧当前的拦截器生成的代理存到拦截器链中
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取到的配置文件的参数是："+properties);
    }
}
