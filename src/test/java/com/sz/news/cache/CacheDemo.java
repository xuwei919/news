package com.sz.news.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/23 16:42
 */
@Slf4j
public class CacheDemo {

    private static ConcurrentHashMap<String, UserEntity> concurrentHashMap = new ConcurrentHashMap<>();

    public static synchronized   UserEntity get(String key) {

        UserEntity entity = concurrentHashMap.get(key);
        if (entity != null) {
            System.out.println(entity);
        } else {
            entity = new UserEntity().getData();
            entity.setName(key);
            concurrentHashMap.put(key, entity);
        }
        return entity;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> get("admin")).start();
        }


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserEntity {

        private String name;
        private int age;

        /**
         * 模拟查询数据库数据
         *
         * @return
         */
        public UserEntity getData() {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("数据查询中。。。");
            return new UserEntity(name, new Random().nextInt());
        }

    }

}
