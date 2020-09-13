package com.itmayiedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Service = @Component
 * server的底层就是Component
 *
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createUser(String name,int age){
        int insert=jdbcTemplate.update("insert into users(name,age) values(?,?);",name,age);
        return insert>0 ? true : false;

    }
}
