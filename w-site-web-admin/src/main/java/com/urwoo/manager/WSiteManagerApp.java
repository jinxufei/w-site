package com.urwoo.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.urwoo"})
@ComponentScan(basePackages = {"com.urwoo"})
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@Slf4j
public class WSiteManagerApp {

    public static void main( String[] args ) {
        log.info("=================start WSiteManagerApp ...=================\n");
        SpringApplication.run(WSiteManagerApp.class, args);
        log.info("=================end WSiteManagerApp ...=================\n");
    }
}
