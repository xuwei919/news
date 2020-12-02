package com.sz.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/19 10:43
 */
public class Demo {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        list.add(new Person("admin", 18));
        list.add(new Person("test", 15));
        list.add(new Person("java", 30));

        Map<String, Person> collect = list.stream().collect(Collectors.toMap(Person::getName, person -> person));
        System.out.println(collect.toString());


    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
    }

}
