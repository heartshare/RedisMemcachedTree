package com.bonaparte.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/9/26.
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "memcache")
public class MemcacheProp {
    String servers;
    Integer initConn;
    Integer minConn;
    Integer maxConn;
    Integer maintSleep;
    boolean nagle;
    Integer socketTO;
    boolean failover;
}
