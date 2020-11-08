package com.xiewz.jdbc;

import com.xiewz.pojo.User;

import java.sql.*;

public class JdbcDamo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Drver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "root");

            // 定义sql语句?表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
            preparedStatement.setString(1, "tom");
            resultSet = preparedStatement.executeQuery();

            // 遍历查询结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");

                User user = new User();
                user.setId(id);
                user.setUsername(username);

                System.out.println(user);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }

    }
}
