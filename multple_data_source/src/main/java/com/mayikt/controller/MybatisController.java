package com.mayikt.controller;

import com.mayikt.member.MemberMapper;
import com.mayikt.order.OrderMapper;
import com.mayikt.server.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderMapper orderMapper;

    @Value("${mayikt.userName}")
    private String userName;

    @RequestMapping("/addUser")
    public String addUser(String name,int age){

        int j=1/age;

        return memberService.addUser(name,age)>0?"success":"fail";
    }

    @RequestMapping("/insertOrder")
    public String insertOrder(String number){
        return orderMapper.insertOrder(number)>0?"success":"fail";
    }

    @RequestMapping("/getUserName")
    public String getUserName(){
        return userName;
    }
}
