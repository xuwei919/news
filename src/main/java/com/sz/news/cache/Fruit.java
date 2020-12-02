package com.sz.news.cache;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 17:09
 */
public class Fruit<T> {

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
