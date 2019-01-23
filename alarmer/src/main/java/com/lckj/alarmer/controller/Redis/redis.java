package com.lckj.alarmer.controller.Redis;

import redis.clients.jedis.Jedis;


public class redis {
    public static void ConnectAndWrite(String key,String name) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //查看服务是否运行
        String connect_result = jedis.ping();
        System.out.println(connect_result);
        if (connect_result.equals("PONG")) {
            System.out.println("Redis 连接成功");
        }
        else{
            System.out.println("Redis连接失败，请检查连通性。");
        }
        jedis.set(key, name);
    }
}
