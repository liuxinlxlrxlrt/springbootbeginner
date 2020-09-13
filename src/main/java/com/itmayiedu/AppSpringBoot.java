package com.itmayiedu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication =@SpringBootConfiguration，+ @EnableAutoConfiguration，+ @ComponentScan
 */
/**
 *@EnableAutoConfiguration 是springboot实现自动化配置的核心注解，通过这个注解把spring应用所需的bean注入容器中．
 * @EnableAutoConfiguration 源码通过@Import注入了一个ImportSelector的实现类AutoConfigurationImportSelector,
 * 这个ImportSelector最终实现根据我们的配置，动态加载所需的bean.
 * @EnableAutoConfiguration 默认只扫描当前类的请求
 */

/**
 * spring里有四大注解：@Service,@Repository,@Component,@Controller用来定义一个bean.
 * @ComponentScan 注解就是用来自动扫描被这些注解标识的类，最终生成ioc容器里的bean
 * 等价于<context:component-scan>的xml配置文件中的配置项。
 */

/**
 * @SpringBootConfiguration: 标注当前类是配置类，这个注解继承自@Configuration。
 * 并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到srping容器中，并且实例名就是方法名。
 */

//访问静态资源resources/static/d.png：默认为static下的图片，正确为：http://localhost:8080/d.png
//动静分离：后台服务于前台页面图片，静态资源CDN加速

//@ComponentScan(basePackages = {"com.itmayiedu.controller", "com.itmayiedu.service"})
//@EnableAutoConfiguration
@SpringBootApplication
public class AppSpringBoot {
    public static void main(String[] args) {
        //主函数运行springboot项目
        SpringApplication.run(AppSpringBoot.class,args);
    }
}
