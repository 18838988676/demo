package com.example.test1.other;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Test888 {

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

//用map 来提取 对象中某个属性，然后再用reduce 进行归约。
    @Test
    public void test() {
        //reduce（T identitty，BinaryOperator）首先，需要传一个起始值，然后，传入的是一个二元运算。

        // identitty 起始值 0  然后与集合中的值进行 相应的运算，再次赋值给 identity 然后 在进行运算。
        Integer sum = Arrays.asList(1, 3, 5).stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        // reduce（BinaryOperator）此方法相对于上面方法来说，没有起始值，则有可能结果为空，所以返回的值会被封装到Optional中。

        Optional<Integer> reduce = persons.stream().map(x -> x.getAge()).reduce(Integer::sum);
        System.out.println(reduce.get());

        String collect1 = persons.stream().map(Person::getName).collect(Collectors.joining("，","头","尾"));
        String collect3 = persons.stream().map(Person::getName).collect(Collectors.joining());
        System.out.println(collect1); //头张三，张三，李四，王五，赵六，钱七，翠花1，翠花2，翠花3尾
        System.out.println(collect3); // 张三张三李四王五赵六钱七翠花1翠花2翠花3


        //求平均数
        Double collect = persons.stream().collect(Collectors.averagingDouble(Person::getAge));
        System.out.println(collect);

        //Collectors.maxBy（） 求最大值

        Optional<Person> collect222 = persons.stream().collect(Collectors.maxBy((o1, o2) -> Integer.compare(o1.age, o2.age)));
        System.out.println(collect222.get().age);

        //9 Collectors.minBy（） 求最小值
        Optional<Person> collect33 = persons.stream().collect(Collectors.minBy((o1, o2) -> Integer.compare(o1.age, o2.age)));
        System.out.println(collect33.get().age);

    }
    @Test
    public void test3() {
        Map<Integer, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        collect.forEach((x, y) -> {
            System.out.println(" status === " + x);
            y.forEach(System.out::println);
        });

    }


// 收集collect（将流转换为其他形式。接收一个Collector接口得实现，用于给其他Stream中元素做汇总的方法）
    @Test
    public void test2() {
/*       Collector接口中方法得实现决定了如何对流执行收集操作（如收集到List，Set，Map）。
       但是Collectors实用类提供了很多静态方法，可以方便地创建常见得收集器实例。
*/
// Collectors.toList（） 将流转换成List
        List<Person> collect = persons.stream().collect(Collectors.toList());
//        collect.forEach(System.out::println);

// 将流转换成HashSet

        Set<String> collect1 = persons.stream().map(e -> e.getName()).collect(Collectors.toSet());
//        collect1.forEach(System.out::println);


        LinkedHashSet<Integer> collect2 = persons.stream().map(Person::getAge).collect(Collectors.toCollection(LinkedHashSet::new));
//        collect2.forEach(System.out::println);


//        persons.add(  new Person("张三", "男", 76));
//        Map<String,Integer > collect3 = persons.stream().collect(Collectors.toMap( Person::getName,Person::getAge));
//        Set<Map.Entry<String,Integer >> entries = collect3.entrySet();
//        //此写法会有问题，如果Map的key 重复了，会报java.lang.IllegalStateException: Duplicate key
//        for (Map.Entry<String,Integer > ma:entries){
//            System.out.println(ma.getKey()+"====="+ma.getValue());
//        }


//        第三个参数为如果 key重复了要如何处理，是保留旧的还是选择新的
        Map<Integer, String> collect4 = persons.stream().collect(Collectors.toMap(Person::getAge, Person::getName,(firstwmc,secondwmc)->secondwmc));
        Set<Map.Entry<Integer, String>> entries2 = collect4.entrySet();
        //此写法会有问题，如果Map的key 重复了，会报java.lang.IllegalStateException: Duplicate key
        for (Map.Entry<Integer, String> ma:entries2){
            System.out.println(ma.getKey()+"====="+ma.getValue());
        }
        System.out.println("---------------------------");
        collect4.forEach((x,y)-> System.out.println(x+"=="+y));
    }
}
