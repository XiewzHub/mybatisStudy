package com.xiewz.lagoumpspringboot;

//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiewz.mapper.UserMapper;
import com.xiewz.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LagouMpSpringbootApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		List<User> userList = userMapper.selectList(null);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test
	public void testSelectById() {
		User user1 = userMapper.findById(1L);
		System.out.println();
		System.out.println(user1);
	}

	@Test
	public void testSelectPage() {

		Page<User> userPage = new Page<>(1, 3);
		IPage<User> userIPage = userMapper.selectPage(userPage, new QueryWrapper<User>().gt("age", 18));
//		for (User user : userList) {
//			System.out.println(user);
//		}
		System.out.println();
		System.out.println("总数："+userIPage.getTotal());
		System.out.println("页数："+userIPage.getSize());
		System.out.println("当前页数据："+userIPage.getRecords());
	}

}
