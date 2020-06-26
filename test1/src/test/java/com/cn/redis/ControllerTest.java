package com.cn.redis;

import com.cn.redis.config.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
//        redisUtil.lSet("persons",persons,800);
        List<Object> persons = redisUtil.lGet("persons", 0, -1);
        persons.stream().forEach(System.out::println);
    }
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    String key1="key1";
    String key2="key2";
    String key3="key3";

    String value1="半缘修道半缘君";
    String value2="123ABC先看庭前花开";
    String value3="nbnnbnbnnb12321";

    String table1="user";
    String table2="user2";

    @Test
    public void test122() {

//        System.out.println(redisUtil.set("age","1234王明超",30, TimeUnit.MINUTES));
//        System.out.println(redisUtil.getExpire("age",TimeUnit.MINUTES));
//        System.out.println(redisUtil.expire("age",2,TimeUnit.DAYS));
//        System.out.println(redisUtil.getExpire("age",TimeUnit.MINUTES));
//        System.out.println(redisUtil.setExpirePre("age"));
//        System.out.println(redisUtil.hasKey("age"));
//        System.out.println(redisUtil.hasKey("age2"));
//        System.out.println(redisUtil.del("age"));
        System.out.println(redisUtil.set(key1,value1));
        System.out.println(redisUtil.set(key2,value2));
        System.out.println(redisUtil.set(key3,value3));
    }


    @Test
    public void delete() {
        System.out.println(redisUtil.del(key1));
        System.out.println(redisUtil.del(key2,key3));
    }
    @Test
    public void test1() {
        System.out.println(redisUtil.incr("incr",8));
    }
    @Test
    public void test2() {
        System.out.println(redisUtil.hmset(table1,map));
        System.out.println(redisUtil.hMget(table1));//{sex=男, name=wmc, age=12}


    }
    @Test
    public void test3() {
        System.out.println(redisUtil.hset(table2,"001",value1));
        System.out.println(redisUtil.hset(table2,"002",value2));
        System.out.println(redisUtil.hget(table2,"001"));
        System.out.println(redisUtil.hget(table2,"002"));


    }
    @Test
    public void test4() {
//
//        System.out.println(redisUtil.hmset(table1+11,map));
//        System.out.println(redisUtil.hMget(table1+11));
        System.out.println(redisTemplate.opsForHash().values(table1+11));//[男, wmc, 1, 12]
        System.out.println(redisTemplate.opsForHash().keys(table1+11));//[sex, name, id, age]
    }
    @Test
    public void test5() {
        for (int i=0;i<4;i++){
        System.out.println(redisUtil.hincr("test","001",1));
        }
        System.out.println(redisUtil.hget("test","001"));
/*
1.0
2.0
3.0
4.0
4*/
    }
    @Test
    public void test6() {
//        System.out.println(redisUtil.lSet("persons",persons));
//        System.out.println(redisUtil.lGet("persons",2,-1));
    //这里的  list 的  索引 就是 row：  就是redis客户端上的行；
        System.out.println(redisUtil.lUpdateIndex("persons",1,"updatedafdafdsafds"));

    }
    @Test
    public void test7() {
    }
    @Test
    public void test8() {
    }
    @Test
    public void test9() {
    }
    @Test
    public void test10() {
    }
    @Test
    public void test11() {
    }
    @Test
    public void test12() {
    }
    @Test
    public void test13() {
    }
    @Test
    public void test14() {
    }
    @Test
    public void test15() {
    }
    @Test
    public void test16() {
    }
    @Test
    public void test17() {
    }
    @Test
    public void test18() {
    }
    @Test
    public void test19() {
    }
    @Test
    public void test20() {
    }
    @Test
    public void test21() {
    }
    @Test
    public void test22() {
    }
    @Test
    public void test23() {
    }
    @Test
    public void test24() {
    }
    @Test
    public void test25() {
    }
    @Test
    public void test26() {
    }
    @Test
    public void test27() {
    }
    @Test
    public void test28() {
    }
    @Test
    public void test29() {
    }
    @Test
    public void test30() {
    }
    @Test
    public void test31() {
    }
    @Test
    public void test32() {
    }
    @Test
    public void test33() {
    }
    @Test
    public void test34() {
    }
    @Test
    public void test35() {
    }
    @Test
    public void test36() {
    }
    @Test
    public void test37() {
    }
    @Test
    public void test38() {
    }
    @Test
    public void test39() {
    }







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
    HashMap map=new HashMap();
    HashMap map2=new HashMap();
    {
        map.put("id",001);
        map.put("age",12);
        map.put("name","wmc");
        map.put("sex","男");

        map2.put("id",002);
        map2.put("age",255);
        map2.put("name","12312wmc");
        map2.put("sex","dfasdf男");
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


}
