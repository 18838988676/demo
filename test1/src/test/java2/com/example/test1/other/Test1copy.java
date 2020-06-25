package com.example.test1.other;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class Test1copy {

    //        test(T t): 判断t，是否满足Predicate实例所代表的表达式
    @Test
    public void test(){
        // 形参x的数据类型，由Predicate<T>的泛型T指定
        // 定义一个 用于判断的表达式(这里为 x >= 1)
        Predicate<Integer> predicate=x->x>=1;
        System.out.println(predicate.test(1));
        System.out.println(predicate.test(0));
    }

    //isEqual(Object targetRef): 判断targetRef，是否与Predicate实例所代表的对象相等
    @Test
    public void test2(){
        Object o=new Object();
        System.out.println(Predicate.isEqual(o).test(o));
        System.out.println(Predicate.isEqual(o).test(null));
        System.out.println(Predicate.isEqual(o).test(new Object()));
    }

    //and(Predicate<? super T> other): 对两个Predicate实例取&&，得到新的Predicate实例
    @Test
    public void test33(){
        Predicate<Integer> predicate1=x -> x>=1;
        Predicate<Integer> predicate2=x -> x<=5;
        System.out.println(predicate1.and(predicate2).test(3));
        System.out.println(predicate1.and(predicate2).test(7));
        System.out.println(predicate1.and(predicate2).test(0));
    }


    //and(Predicate<? super T> other): 对两个Predicate实例取&&，得到新的Predicate实例
    @Test
    public void test4(){
        Predicate<Integer> predicate1=x -> x>=1;
        Predicate<Integer> predicate2=x -> x<=5;
        System.out.println(predicate1.or(predicate2).test(3));
        System.out.println(predicate1.or(predicate2).test(7));
        System.out.println(predicate1.or(predicate2).test(0));
    }

    //negate(): 对当前Predicate实例取!，得到新的Predicate实例
    @Test
    public void test55(){
        Predicate<Integer> predicate1=x -> x>=1;
        System.out.println(predicate1.negate().test(3));
        System.out.println(predicate1.negate().test(7));
        System.out.println(predicate1.negate().test(0));
    }

}
