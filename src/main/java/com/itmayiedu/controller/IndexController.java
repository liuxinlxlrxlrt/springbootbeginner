package com.itmayiedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    //全局捕获异常例子
//    @RequestMapping("/indexController")
//    public String indexController(){
//        int i=1/0;
//        return "success";
//    }


    @RequestMapping("/indexController")
    public String indexController(Map<String,Object> result){
        System.out.println("IndexController...index");
        result.put("name","wengzihan");
        result.put("sex",0);
        List<String> list= new ArrayList<String>();
        list.add("fanling");
        list.add("yuanjiamin");
        result.put("userList",list);
        return "index";
    }

}
