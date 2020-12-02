package com.sz.news.cache;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 17:18
 */
public interface Generator<T> {

    T next();

}
