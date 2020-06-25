package com.example.test1.other.jkd;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Jdk1 {

    @Test
    public void test3() {
        LocalDateTime idt=LocalDateTime.now();
        //2020-06-25 14:03:37
        System.out.println(idt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//2020-06-25 13:56:48
        LocalDate localDate=idt.toLocalDate();
        LocalTime localTime=idt.toLocalTime();
        boolean after = idt.isAfter(LocalDateTime.of(2024, 1, 1, 2, 3));
        System.out.println(after);//false

        //注意默认获取出来的是当前的美国时间和我们相差八个小时
        Instant ins = Instant.now();

        System.out.println(ins);//2020-06-25T06:03:37.636Z
        //们在东八区 所以可以加8个小时 就是我们的北京时间
        OffsetDateTime time =ins.atOffset(ZoneOffset.ofHours(8));
        //2020-06-25T14:06:45.894+08:00
        System.out.println(time);

    }
}
