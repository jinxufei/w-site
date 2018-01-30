package com.urwoo.article;

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
public class ArticleApplication {

    public static void main( String[] args ) {
        log.info("=================start article-service ...=================\n");
        SpringApplication.run(ArticleApplication.class, args);
        log.info("=================end article-service ...=================\n");
    }
}
