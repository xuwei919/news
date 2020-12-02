package com.sz.news.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 14:41
 */
public class CompletableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //        String s = CompletableFuture.supplyAsync(() -> "hello world").thenApplyAsync(s1 -> {
        //            System.out.println(s1);
        //            return s1.toUpperCase();
        //        }).get();
        //        System.out.println(s);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("sina", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("163", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);
        System.out.println(cfQuery.get());

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);

    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            if (name.equals("163")) {
                Thread.sleep(3000);
            }

        } catch (InterruptedException e) {
        }

        if (name.equals("163")) {
            name = "163163163";
        } else {
            name = "sinasinaisna";
        }

        return name;

    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
