package com.xiewz.mapper;

import com.xiewz.pojo.Order;
import com.xiewz.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderMapper {

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "total",column = "total"),
            @Result(property = "user",column = "uid",javaType = User.class,
                    one=@One(select = "com.xiewz.mapper.IUserMapper.findUserById"))
    })
    @Select("select * from orders")
    public List<Order> findUserAndOrder();

    @Select("select * from orders where uid = #{uid}")
    public List<Order> findOrderByUid(Integer uid);
}
