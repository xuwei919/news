package com.sz.news.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 14:26
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Double> completableFuture = CompletableFuture.supplyAsync(CompletableFutureDemo::fetchPrice);
        completableFuture.thenAccept(result -> System.out.println("price:" + result));
        completableFuture.exceptionally(e -> {
            System.out.println("执行异常");
            return null;
        });

        Thread.sleep(2000L);

    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

}
