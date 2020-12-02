package com.sz.news.thread;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 13:54
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Task> submit = executorService.submit(Task::new);

        Task task = submit.get();
        System.out.println(task.call());
        System.out.println(LocalDateTime.now());

        executorService.shutdown();

    }

}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return "你好";
    }
}
