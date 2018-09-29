package com.bonaparte.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;

/**
 * Created by yangmingquan on 2018/9/28.
 * redis 队列
 */
@Service
public class RedisQueueService {
    @Resource(name = "jedisCluster")
    private JedisCommands jedis;

    public void queueSenior(){
        jedis.lpush("listBonaparte", "abc11111111111");
        jedis.blpop(10, "listBonaparte");
    }
}
