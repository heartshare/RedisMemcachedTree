package com.bonaparte.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/9/26.
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisProp {
    Integer poolMaxIdle;
    Integer poolMaxTotal;
    Integer poolMaxWaitMillis;
    boolean poolTestOnBorrow;
    Integer commandTimeout;
    String clusterNodes;

    public Integer getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(Integer poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }

    public Integer getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public void setPoolMaxTotal(Integer poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public Integer getPoolMaxWaitMillis() {
        return poolMaxWaitMillis;
    }

    public void setPoolMaxWaitMillis(Integer poolMaxWaitMillis) {
        this.poolMaxWaitMillis = poolMaxWaitMillis;
    }

    public boolean isPoolTestOnBorrow() {
        return poolTestOnBorrow;
    }

    public void setPoolTestOnBorrow(boolean poolTestOnBorrow) {
        this.poolTestOnBorrow = poolTestOnBorrow;
    }

    public Integer getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(Integer commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }
}
