package com.cn.redis.clusterlock;

import com.cn.redis.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RedisClusterLock {

    //锁名称
    public static final String LOCK_PREFIX = "redis_lock";

    public static final int LOCK_EXPIRE = 600;//ms

    @Autowired
    private RedisUtil redisUtil;

    /**
     *  最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        String lock = LOCK_PREFIX + key;
        return redisUtil.getRedisTemplate().execute((RedisCallback<Boolean>) x -> {
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = x.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {

                byte[] value = x.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    //如果锁已经过期
                    if (expireTime < System.currentTimeMillis()) {
                        //重新加锁，防止死锁
                        byte[] oldValue = x.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });


    }

    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        redisUtil.getRedisTemplate().delete(key);
    }

}
