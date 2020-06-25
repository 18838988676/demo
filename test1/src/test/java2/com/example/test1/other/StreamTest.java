package com.example.test1.other;

import lombok.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    @Setter
    @Getter
    @AllArgsConstructor
    class Stu{
    private String name;
    private Integer age;
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
        Status statusEnum;
    }
enum Status{
    FREE,
    BUSY,
    VOCATION;
    }

    @Test
    public void test4(){
        List<String> list = Arrays.asList("1","2","3","4","0","222","33");
        Stream<String> stream = list.stream();
        Stream<String> stream1 = list.parallelStream();
        IntStream stream2= Arrays.stream(new int[]{1,2,3});
    Stream<String> str=    Stream.of("a","b","c");
    }
    @Test
    public void test1(){
        List<String> strings = Arrays.asList("1", "2", "3", "4", "0", "222", "33");
        strings.stream().filter(x-> !x.equals("2") ).skip(1).limit(4).forEach(System.out::println);
    }
    @Test
    public void test2(){
        List<Stu> stuList = Arrays.asList(new Stu("a",1),new Stu("ab",3),new Stu("c",11),new Stu("f",12));
        List<Integer> collect1 = stuList.stream().map(x -> x.getAge()).collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }
    @Test
    public void test3(){

        List<Integer> strings = Arrays.asList(4,7,8,1,2,7,8,43);
        strings.stream().sorted().forEach(System.out::println);
        System.out.println("=============");

        //逆序：从大到小
        strings.stream().sorted((o1,o2)->{

            if(o1>o2){
                return -1;
            }else if(o1<o2){
                return 1;
            }else {
                return 0;
            }
        }).forEach(System.out::println);
    }

    @Test
    public void test5(){
        List<Person> persons = Arrays.asList(
                new Person("张三", "男", 76, Status.FREE),
                new Person("李四", "女", 12, Status.BUSY),
                new Person("王五", "男", 35, Status.BUSY),
                new Person("赵六", "男", 3, Status.FREE),
                new Person("钱七", "男", 56, Status.BUSY),
                new Person("翠花", "女", 34, Status.VOCATION),
                new Person("翠花", "女", 34, Status.FREE),
                new Person("翠花", "女", 34, Status.VOCATION)
        );
        //allMatch —— 检查是否匹配所有元素。 false
//        System.out.println(persons.stream().allMatch(x->x.getStatusEnum().equals(Status.FREE)));
        //anyMatch —— 检查是否至少匹配一个元素。 tue
//        System.out.println(persons.stream().anyMatch(x->x.getStatusEnum().equals(Status.FREE)));
        //noneMatch —— 检查是否没有匹配所有元素 false
//        System.out.println(persons.stream().noneMatch(x->x.getStatusEnum().equals(Status.FREE)));

        // findFirst —— 返回第一个元素。
        Optional<Person> first = persons.stream().findFirst();
    //        System.out.println(first.get());

        // findAny —— 返回当前流中任意元素。
        Optional<Person> any = persons.stream().findAny();
        System.out.println(any.get());//StreamTest.Person(name=张三, sex=男, age=76, statusEnum=FREE)

        // findAny —— 返回当前流中任意元素。
        Optional<Person> any2 = persons.stream().findAny();
        //count —— 返回流中元素总个数。
        long count = persons.stream().count();
        System.out.println(count);//8

        //max —— 返回流中最大值。
        Optional<Person> max = persons.stream().max((x, y) -> Integer.compare(x.age, y.age));
        System.out.println(max.get());//StreamTest.Person(name=张三, sex=男, age=76, statusEnum=FREE)

        //max —— 返回流中最xiao值。
        Optional<Person> mix = persons.stream().min((x, y) -> Integer.compare(x.age, y.age));
        System.out.println(mix.get());//StreamTest.Person(name=张三, sex=男, age=76, statusEnum=FREE)

    }

    public void test9(){
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // identitty 起始值 0  然后与集合中的值进行 相应的运算，再次赋值给 identity 然后 在进行运算。
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }








}
