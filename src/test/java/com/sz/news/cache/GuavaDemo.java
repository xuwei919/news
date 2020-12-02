package com.sz.news.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/24 9:45
 */
public class GuavaDemo {

    public static void main(String[] args) throws ExecutionException {

        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        Object first = cache.get("first", () -> getFirst("admin"));
        System.out.println(first);

    }


    private static List<String> getFirst(String key) {
        System.out.println(key);
        return Lists.newArrayList(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }


}
