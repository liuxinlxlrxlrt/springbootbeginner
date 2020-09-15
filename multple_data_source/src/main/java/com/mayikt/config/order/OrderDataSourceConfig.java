package com.mayikt.config.order;

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

@Configuration
@MapperScan(basePackages = "com.mayikt.order",sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDataSourceConfig {

    /**
     * 创建datasource
     * @return
     */
    @Bean("orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSource orderDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(orderDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建订单管理器
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("orderTransactionManager")
    public DataSourceTransactionManager orderTransactionManager(@Qualifier("orderDataSource") DataSource dataSource) throws Exception{
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建sqlSession模板
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws  Exception{
        return  new SqlSessionTemplate(sqlSessionFactory);
    }

}
