package com.sz.news.jdk8;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 17:30
 */
public class Test {

    public static void main(String[] args) {

        FunctionDemo<String, String> functionDemo = String::toUpperCase;

        System.out.println(functionDemo.test("hello"));

        Function<Integer, Integer> function = o -> o + 10;

        System.out.println(function.apply(1));

        Supplier<String> supplier = "我是 supplier函数"::toUpperCase;
        System.out.println(supplier.get());

        Function<String, String> stringStringFunction = o -> "1747";

        String dataFromJimdb = getDataFromJimdb("20201201", stringStringFunction);
        System.out.println(dataFromJimdb);

        Function<Integer, Integer> A = i -> i + 1;
        Function<Integer, Integer> B = i -> i * i;

        System.out.println("F1:" + B.apply(A.apply(5)));
        System.out.println("F2:" + A.apply(B.apply(5)));

        System.out.println("F3:" + A.compose(B).apply(5));
        System.out.println("F3:" + A.andThen(B).apply(5));

    }

    public static <T> T getDataFromJimdb(String date, Function<String, T> function) {
        T t = function.apply(date);
        return t;
    }

}
