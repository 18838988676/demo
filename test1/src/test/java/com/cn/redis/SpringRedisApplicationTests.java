package com.cn.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRedisApplicationTests {


    @Autowired
    private ApplicationContext context;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void test1(){
        System.out.println(stringRedisTemplate);
        System.out.println(redisTemplate);
        System.out.println(context);
    }
    @Test
    public void RedisTemplateTest() {
        System.out.println(redisTemplate);  // 结果： org.springframework.data.redis.core.RedisTemplate@314c8b4a
        redisTemplate.opsForValue().set("db-type", "redis");
        System.out.println(redisTemplate.opsForValue().get("db-type")); // 结果： redis
    }

    @Test
    public void StringRedisTemplateTest() {
        System.out.println(stringRedisTemplate);  // 结果： org.springframework.data.redis.core.RedisTemplate@314c8b4a
        stringRedisTemplate.opsForValue().set("db-type", "mongodb");
        System.out.println(stringRedisTemplate.opsForValue().get("db-type")); // 结果： mongodb
        System.out.println(redisTemplate.opsForValue().get("db-type")); // 结果： redis
    }

    @Test
    public void RedisConnectionFactoryTest() {
        RedisConnectionFactory redisConnectionFactoryBean = context.getBean(RedisConnectionFactory.class);
        System.out.println(redisConnectionFactoryBean); // 结果： org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory@59496961
    }

    public boolean tt() {
        System.out.println("1");
        return false;
    }
    public boolean tt2() {
        System.out.println("2");
        return false;
    }
    @Test
    public void tt3() {
//        String key="aa";  2
      String key="a";//1
      boolean a=  Predicate.isEqual(1).test(key.length()) ? tt()  : tt2();
    }



}
