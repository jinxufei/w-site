package com.urwoo.web.config.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Setter
@Getter
public class RedisSingleProperties {

    private boolean cluster;
    private String singleHost;
    private Integer singlePort;
    private String password;
}
