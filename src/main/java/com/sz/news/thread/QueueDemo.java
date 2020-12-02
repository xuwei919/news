package com.sz.news.thread;

import lombok.Data;
import lombok.var;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 11:05
 */
@Data
public class QueueDemo {

    public static void main(String[] args) throws InterruptedException {
        var q = new QueueThread();
        var ts = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            var t = new Thread() {
                @Override
                public void run() {
                    // 执行task:
                    while (true) {
                        String s = null;
                        try {
                            s = q.getTask();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("execute task: " + s);
                    }
                }
            };
            t.start();
            ts.add(t);
        }
        var add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.add(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
//        for (var t : ts) {
//            t.interrupt();
//        }
    }

}

class QueueThread {

    private static Queue<String> taskQueue = new LinkedList<>();

    public synchronized void add(String task) {
        taskQueue.add(task);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {

        while (taskQueue.isEmpty()) {
            this.wait();
            System.out.println("没有任务");
        }
        return taskQueue.remove();
    }

}
