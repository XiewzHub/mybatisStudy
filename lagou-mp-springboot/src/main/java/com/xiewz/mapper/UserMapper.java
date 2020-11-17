package com.xiewz.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiewz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//@Component
//@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User findById(Long id);
}
