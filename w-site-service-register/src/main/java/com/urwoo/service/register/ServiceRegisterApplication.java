package com.urwoo.service.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class ServiceRegisterApplication {

    public static void main(String[] args) {
        log.info("=================start w-site-service-register ...=================\n");
        SpringApplication.run(ServiceRegisterApplication.class, args);
        log.info("=================end w-site-service-register ...=================\n");
    }
}
