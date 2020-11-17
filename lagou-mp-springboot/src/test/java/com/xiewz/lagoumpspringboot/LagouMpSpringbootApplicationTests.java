package com.xiewz.lagoumpspringboot;

import com.xiewz.com.xiewz.mapper.UserMapper;
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

}
