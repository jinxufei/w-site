package com.urwoo.web;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.urwoo"})
@ComponentScan(basePackages = {"com.urwoo"})
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@Slf4j
public class WSiteWebApp {
    public static void main( String[] args ) {
        log.info("=================start WSiteWebApp ...=================\n");
        SpringApplication.run(WSiteWebApp.class, args);
        log.info("=================end WSiteWebApp ...=================\n");
    }
}
