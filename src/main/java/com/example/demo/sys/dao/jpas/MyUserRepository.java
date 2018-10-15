package com.example.demo.sys.dao.jpas;

import com.example.demo.sys.dao.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangrenhao
 * @date 2018/6/6
 */
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findByUsername(String username);
}
