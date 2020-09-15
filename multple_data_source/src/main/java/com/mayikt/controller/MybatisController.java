package com.mayikt.controller;

import com.mayikt.member.MemberMapper;
import com.mayikt.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisController {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/addUser")
    public String addUser(String name,int age){
        return memberMapper.addUser(name,age)>0?"success":"fail";
    }

    @RequestMapping("/insertOrder")
    public String insertOrder(String number){
        return orderMapper.insertOrder(number)>0?"success":"fail";
    }
}
