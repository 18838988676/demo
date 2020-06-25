package com.cn.redis;

import com.cn.redis.config.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {



    @Autowired
    private RedisUtil redisUtil;

    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    class Person {
        String name ;
        String sex ;
        Integer age;
    }

    List<Person> persons = Arrays.asList(
            new Person("张三", "男", 7777),  new Person("张三", "男", 76),
            new Person("李四", "女", 76),
            new Person("王五", "男", 35),
            new Person("赵六", "男", 35),
            new Person("钱七", "男", 56),
            new Person("翠花1", "女", 34),
            new Person("翠花2", "女", 34),
            new Person("翠花3", "女", 34)
    );

    @Test
    public void test() {
//        redisUtil.lSet("persons",persons,800);
        List<Object> persons = redisUtil.lGet("persons", 0, -1);
        persons.stream().forEach(System.out::println);
    }














}
