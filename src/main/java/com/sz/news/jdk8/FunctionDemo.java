package com.sz.news.jdk8;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 17:29
 */
@FunctionalInterface
public interface FunctionDemo<T, R> {

    R test(T param);

}
