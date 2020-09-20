package com.mayikt;

import com.mayikt.config.member.Membeconfig;
import com.mayikt.config.order.OrderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({Membeconfig.class, OrderConfig.class})
public class MultpleDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultpleDataSourceApplication.class, args);
    }
}
