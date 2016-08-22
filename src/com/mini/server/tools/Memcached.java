package com.mini.server.tools;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class Memcached {
    
    private static MemCachedClient memcached = new MemCachedClient();
    
    private Memcached() {
        
    }
    
    static {
//    	String[] servers = { "192.168.1.21:22121"};
    	String[] servers = { "127.0.0.1:22121","127.0.0.1:22122"};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(100);
        pool.setMaxIdle(1000 * 60 * 60 * 6);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setAliveCheck(true);
        pool.initialize();
        memcached.setCompressEnable(true);
        memcached.setCompressThreshold(512 * 1024);
    }
    
    public static MemCachedClient getInstance() {
        return memcached;
    }
}
