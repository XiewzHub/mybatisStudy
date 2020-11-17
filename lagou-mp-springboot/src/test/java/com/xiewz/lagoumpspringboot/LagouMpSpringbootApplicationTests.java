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
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void testInsert(){
		User user = new User();
		user.setAge(35);
		user.setName("lisi");
		user.setEmail("fde@123.com");
		userMapper.insert(user);
		System.out.println("--------------");
		System.out.println(user.getId());

		User user1 = userMapper.selectById(user.getId());
		System.out.println("--------------");
		System.out.println(user1);

		int i = userMapper.deleteById(user.getId());
		System.out.println("--------------");
		System.out.println("删除："+(i>0));

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

	/**
	 * 测试条件构建器
	 */
	@Test
	public void testAlleq(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name","Sandy");
		map.put("age",null);
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		// and链接，全部查询
//		queryWrapper.allEq(map);
		// 去掉name条件
		queryWrapper.allEq((k,v)->!"name".equals(k),map);

		List<User> users = userMapper.selectList(queryWrapper);
		System.out.println("\n查询结果是否为空：");
		System.out.println(CollectionUtils.isEmpty(users));
		for (User user : users) {
			System.out.println();
			System.out.println(user);
		}
	}

}
