package com.example.demo.myuser;


import com.example.demo.sys.dao.entities.MyUser;
import com.example.demo.sys.dao.jpas.MyUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserTests {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 *  指定测试用户
	 */
	@WithMockUser
	@Test
	public void mockUser(){

	}

	@Test
	public void testSecurityPasswordEncoder() {

		MyUser user = new MyUser();
		String password = "a123456";
		user.setUsername("测试2");
		user.setPassword(passwordEncoder.encode(password));
		myUserRepository.save(user);

	}

	@Test
	public void contextLoads() {

		MyUser user = new MyUser();
		user.setUsername("测试");
		user.setPassword("a123456");
		myUserRepository.save(user);

	}

}
