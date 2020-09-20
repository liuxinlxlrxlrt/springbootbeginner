package com.mayikt.server;

import com.mayikt.member.MemberMapper;
import com.mayikt.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    OrderMapper orderMapper;

    /**
     * http://localhost:8080/addUser?name=%E5%BC%A0%E7%BE%8E%E6%9B%A6&age=0
     * age=0时，int j = 1/age;报错了，也能写到数据库中，肯定不对
     *
     * 采用实物底层原理采用aop技术，增强，
     *
     * @Transactional 作用于方法上，必须方法执行完毕，没有报错误的情况下才提交事务，否则进行回滚
     * transactionManager = "memberTransactionManager"：指明member事务管理器
     *
     * @param name
     * @param age
     * @return
     */
    @Transactional()
    public int addUser(String name,int age){
        //操作会员数据库
        int i = memberMapper.addUser(name,age);
        //操作订单的数据库
        int orderJ =orderMapper.insertOrder(name);
        //程序一旦抛出情况下，orderMapper会不会进行回滚？没有进行回滚
        int j = 1/age;
        return i;
    }
}
