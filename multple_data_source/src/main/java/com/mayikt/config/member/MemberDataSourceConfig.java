package com.mayikt.config.member;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Configuration ==xml
 * @Configuration 用于定义配置类，可替换XML配置文件，被注解的类内部包含一个或多个@Bean注解方法。
 * 可以被AnnotationConfigApplicationContext或者AnnotationConfigWebApplicationContext 进行扫描。
 * 用于构建bean定义以及初始化Spring容器。
 *
 */
@Configuration
@MapperScan(basePackages = "com.mayikt.member",sqlSessionTemplateRef = "memerSqlSessionTemplate")
public class MemberDataSourceConfig {
    /**
     * 创建Datasource
     * @ConfigurationProperties是springboot 提供读取配置文件的一个注解
     * @ConfigurationProperties 注解支持属性文件和javabean的映射
     * @return
     */
    //@Configuration == MemberDataSource.xml
    @Bean("memberDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.member")
    public DataSource memberDataSource(){
        //创建datasource
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("memberSqlSessionFactory")
    public SqlSessionFactory memberSqlSessionFactory(@Qualifier("memberDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(memberDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建会员管理器
     * @param dataSource
     * @return
     */
    @Bean("memberTransactionManager")
    public DataSourceTransactionManager memberTransactionManager(@Qualifier("memberDataSource") DataSource dataSource)throws Exception{
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建订单sqlSession模板
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("memerSqlSessionTemplate")
    public SqlSessionTemplate memerSqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory)throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
