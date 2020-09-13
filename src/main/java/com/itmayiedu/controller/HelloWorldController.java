package com.itmayiedu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @RestController 表示该接口全部返回json格式,
 * @RestController 注解相当于@ResponseBody ＋ @Controller合在一起的作用
 */

/**
 * @Controller 用于标记在一个类上，使用它标记的类就是一个SpringMvc Controller对象，分发处理器会扫描使用该注解的类的方法，
 * 并检测该方法是否使用了@RequestMapping注解。
 * @Controller 只是定义了一个控制器类，而使用@RequestMapping注解的方法才是处理请求的处理器。
 * @Controller 标记在一个类上还不能真正意义上说它就是SpringMvc的控制器，应为这个时候Spring还不认识它，
 * 这个时候需要把这个控制器交给Spring来管理
 * 在spring项目中由控制器负责将用户发来的URL请求转发到对应的服务接口
 *
 * @RequestMapping： 提供路由信息，负责URL到Controller中的具体函数的映射。
 */

/**
 * @ResponseBody 的作用其实是将java对象转为json格式的数据。
 *
 * @responseBody 注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
 * 写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
 */

/**
 * SpringMvc三层架构注解详解@Controller、@Service和@Repository【 [rɪˈpɑːzətɔːri] ，仓库】
 */

/**
 * Spring注解@Component【 [kəmˈpoʊnənt]组件】、@Repository、@Service、@Controller区别
 *
 *  @Controller 控制层，就是我们的action层
 *
 * @Service 业务逻辑层，就是我们的service或者manager层
 *
 * @Repository 持久层，就是我们常说的DAO层

 * @Component  （字面意思就是组件），它在你确定不了事哪一个层的时候使用。
 */



@RestController
public class HelloWorldController {

    @RequestMapping("/index")
    public String index(){
        return "success";
    }

    @RequestMapping("/getMap")
    public Map<String,Object> getMap(){

        Map<String,Object> result = new HashMap<String, Object>();
        result.put("errorCode","200");
        result.put("errorMsg","成功..。");
        return result;

    }


}
