package com.urwoo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableHystrix
@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@Slf4j
public class UserApplication {

    public static void main( String[] args ) {
        log.info("=================start user-service ...=================\n");
        SpringApplication.run(UserApplication.class, args);
        log.info("=================end user-service ...=================\n");
    }
}
