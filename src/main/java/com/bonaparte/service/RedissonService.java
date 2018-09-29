package com.bonaparte.service;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yangmingquan on 2018/9/29.
 * 相比于传统的redisson，提供更多的数据类型，更多的功能
 */
@Service
public class RedissonService {
    @Autowired
    RedissonClient redissonClient;

    public void redissonOperation(){
        AtomicLong atomicLong = (AtomicLong) redissonClient.getAtomicLong("bonaparte");
        atomicLong.compareAndSet(100, 101);
       long atomicLong1 = atomicLong.incrementAndGet();
        System.out.println(atomicLong1);
    }
}
