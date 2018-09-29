package com.bonaparte.config;

import com.bonaparte.constant.RedissonProps;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/9/29.
 */
@Configuration
public class RedissonConfig {
    @Autowired
    private RedissonProps redissonProps;

    @Bean
    @ConditionalOnProperty(name="redisson.master-name")
    RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redissonProps.getSentinelAddresses())
                .setMasterName(redissonProps.getMasterName())
                .setTimeout(redissonProps.getTimeout())
                .setMasterConnectionPoolSize(redissonProps.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redissonProps.getSlaveConnectionPoolSize());

        if(StringUtils.isNotBlank(redissonProps.getPassword())) {
            serverConfig.setPassword(redissonProps.getPassword());
        }
        return Redisson.create(config);
    }
}
