package com.cn.redis.clusterlock;


import com.cn.redis.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

//使用RedisTemplate实现分布式锁时，需要配合lua脚本实现，直接看代码。
/*注意事项：

1、获取锁时设置key的value值需要保持一定的唯一性，以识别是否是加锁和解锁的线程

2、key对应设置生命周期expireTime，防止死锁*/
public class LuaRedisLock {

    private static final Long SUCCESS = 1L;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 获取锁
     * @param lockKey
     * @param value
     * @param expireTime：单位-秒
     * @return
     */
    public  boolean getLock(String lockKey, String value, int expireTime){
        boolean ret = false;
        try{
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";

            RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

            Object result = redisUtil.getRedisTemplate().execute(redisScript, Collections.singletonList(lockKey),value,expireTime);

            if(SUCCESS.equals(result)){
                return true;
            }

        }catch(Exception e){

        }
        return ret;
    }

    /**
     * 释放锁
     * @param lockKey
     * @param value
     * @return
     */
    public  boolean releaseLock(String lockKey, String value){

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        Object result = redisUtil.getRedisTemplate().execute(redisScript, Collections.singletonList(lockKey),value);
        if(SUCCESS.equals(result)) {
            return true;
        }

        return false;
    }

}
