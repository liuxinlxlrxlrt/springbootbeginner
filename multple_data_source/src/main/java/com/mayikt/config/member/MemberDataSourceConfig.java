package com.mayikt.config.member;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

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
     * 创建Datasource 将我们的数据源统一的交给我们的全局xa事务去管理
     * @ConfigurationProperties是springboot 提供读取配置文件的一个注解
     * @ConfigurationProperties 注解支持属性文件和javabean的映射
     * @return
     */
    //@Configuration == MemberDataSource.xml
    @Bean("memberDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.member")
    public DataSource memberDataSource(Membeconfig membeconfig) throws SQLException {

        //1.创建xaDataSource接口，专门把当前的DataSource注册到全局事务上去
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(membeconfig.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(membeconfig.getPassword());
        mysqlXADataSource.setUser(membeconfig.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        //2.注册到我们的全局事务上
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName(membeconfig.getUniqueRresorceName());

        xaDataSource.setMinPoolSize(membeconfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(membeconfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(membeconfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(membeconfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(membeconfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(membeconfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(membeconfig.getMaxIdleTime());
        xaDataSource.setTestQuery(membeconfig.getTestQuery());

        //创建datasource
//        return DataSourceBuilder.create().build();

        return xaDataSource;
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
//        sqlSessionFactoryBean.setDataSource(memberDataSource());
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();

    }

    /**
     * 创建会员管理器
     * @param dataSource
     * @return
     */
//    @Bean("memberTransactionManager")
//    public DataSourceTransactionManager memberTransactionManager(@Qualifier("memberDataSource") DataSource dataSource)throws Exception{
//        return new DataSourceTransactionManager(dataSource);
//    }

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
