package com.example.test1.other;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void test() {
//创建一个值为张三的String类型的Optional
        Optional<String> ofOptional = Optional.of("张三");
        System.out.println(ofOptional.get());//张三
        System.out.println(ofOptional.isPresent());//
//如果我们用of方法创建Optional对象时，所传入的值为null，则抛出NullPointerException如下图所示
        Optional<String> nullOptional = Optional.of(null);
        System.out.println(nullOptional.get());
        System.out.println(nullOptional.isPresent());
    }

    @Test
    public void test2() {

        //为指定的值创建Optional对象，不管所传入的值为null不为null，创建的时候都不会报错
        Optional<String> nullOptional = Optional.ofNullable(null);
        System.out.println(nullOptional.get());
        System.out.println(nullOptional.isPresent());
        Optional<String> nullOptional2 = Optional.ofNullable("lisi");
        System.out.println(nullOptional2.get());
        System.out.println(nullOptional2.isPresent());
    }



}
