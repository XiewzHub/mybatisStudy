package com.xiewz.jdbc;

import com.xiewz.pojo.User;

import java.sql.*;

public class JdbcDamo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /**
             * 问题一：频繁获取/释放数据库连接，影响数据库和应用性能
             * 解决：数据库连接池技术，C3P0,DRUID（阿里巴巴荣誉出品，号称前无古人后无来者世界最强没有之一）
             */
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8", "root", "root");

            /**
             * 问题二：sql语句硬编码，后期难以维护
             * 解决：若sql语句和java代码分离，比如sql写在配置文件中。Mybatis就是这么干的
             * 问题三：sql语句where条件和占位符一一对应，后期难以维护
             */
            // 定义sql语句?表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
            preparedStatement.setString(1, "tom");
            resultSet = preparedStatement.executeQuery();

            /**
             * 问题四：结果集解析麻烦，查询列硬编码
             * 期望：如果单条记录直接返回实体对象，如果多条记录返回实体的集合
             */
            // 遍历查询结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");

                User user = new User();
                user.setId(id);
                user.setUsername(username);

                System.out.println(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();

        }

    }
}
