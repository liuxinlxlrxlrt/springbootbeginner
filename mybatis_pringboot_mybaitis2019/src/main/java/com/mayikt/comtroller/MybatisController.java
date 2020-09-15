package com.mayikt.comtroller;

import com.mayikt.entity.User;
import com.mayikt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisController {
        @Autowired
        private UserMapper userMapper;

        @RequestMapping("/addUser")
        public String addUser(String name,int age){
            return userMapper.insert(name,age)>0?"success":"fail";
        }

}
