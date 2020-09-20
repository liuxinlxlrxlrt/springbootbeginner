package com.mayikt.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *@ControllerAdvice 异常切面类
 */
@ControllerAdvice
public class MayiktGlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,String> globalExceptionHandler(){
        Map<String,String> map = new HashMap<>();
        map.put("respCode","500");
        map.put("respMsg","系统错误");
        return map;
    }

    //如何返回统一的错误页面呢？
    //把@ResponseBody去掉，返回String类型跳转到统一的一个页面
}
