package com.mayikt.config.order;

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

@Configuration
@MapperScan(basePackages = "com.mayikt.order",sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDataSourceConfig {

    /**
     * 创建datasource
     * @return
     */
    @Bean("orderDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSource orderDataSource(OrderConfig orderConfig) throws SQLException {
        //1.创建xaDataSource接口，专门把当前的DataSource注册到全局事务上去
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(orderConfig.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(orderConfig.getPassword());
        mysqlXADataSource.setUser(orderConfig.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        //2.注册到我们的全局事务上
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName(orderConfig.getUniqueRresorceName());

        xaDataSource.setMinPoolSize(orderConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(orderConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(orderConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(orderConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(orderConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(orderConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(orderConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(orderConfig.getTestQuery());

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
    @Bean("orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(orderDataSource());
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建订单管理器
     * @param dataSource
     * @return
     * @throws Exception
     */
//    @Bean("orderTransactionManager")
//    public DataSourceTransactionManager orderTransactionManager(@Qualifier("orderDataSource") DataSource dataSource) throws Exception{
//        return new DataSourceTransactionManager(dataSource);
//    }

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
