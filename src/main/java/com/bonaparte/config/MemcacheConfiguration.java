package com.bonaparte.config;

import com.bonaparte.constant.MemcacheProp;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/9/26.
 */
@Configuration
public class MemcacheConfiguration {
    @Autowired
    public MemcacheProp memcachedProps;
    @Value("${memcache.servers}")
    private String memcacheServers;

    @Bean
    public SockIOPool sockIOPool(){
        SockIOPool pool = SockIOPool.getInstance();
        String[] servers = new String[1];
        servers[0] = memcacheServers;
        pool.setServers(servers);
        pool.setFailover(memcachedProps.isFailover());
        pool.setInitConn(memcachedProps.getInitConn());
        pool.setMinConn(memcachedProps.getMinConn());
        pool.setMaxConn(memcachedProps.getMaxConn());
        pool.setMaintSleep(memcachedProps.getMaintSleep());
        pool.setNagle(memcachedProps.isNagle());
        pool.setSocketTO(memcachedProps.getSocketTO());
        pool.initialize();
        return pool;
    }

    @Bean
    public MemCachedClient memCachedClient(){
        return new MemCachedClient();
    }
}
