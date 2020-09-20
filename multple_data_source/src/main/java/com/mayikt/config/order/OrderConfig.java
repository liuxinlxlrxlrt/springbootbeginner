package com.mayikt.config.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.order")
@Data
public class OrderConfig {
    //会将application.yml配置文件中读到的内容加载到配置文件类中
    private String url;
    private String username;
    private String password;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxLifetime;
    private int maxIdleTime;
    private int minPoolSize;
    private int maxPoolSize;
    private String testQuery;

    private String uniqueRresorceName;
}
