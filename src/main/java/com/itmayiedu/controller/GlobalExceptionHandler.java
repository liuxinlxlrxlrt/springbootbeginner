package com.itmayiedu.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常类
 *
 * 问题：全局捕获异常原理通过AOP的哪一种通知？ 异常通知
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // @ResponseBody：如果返回json格式页面，
    // 返回String类型，结果指定为404页面
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> resultError(){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("errorCode","500");
        result.put("errorMsg","系统错误");
        return  result;

    }
}
