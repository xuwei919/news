package com.sz.news;

import java.util.StringJoiner;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/2 9:06
 */
public class Demo1 {

    public static void main(String[] args) {

        StringJoiner joiner = new StringJoiner(",");
        joiner.add("德玛");
        joiner.add("盖伦");

        String join = String.join(",", "hello", "world", "你好", "世界");

        System.out.println(join);

        System.out.println(joiner);

    }
}
