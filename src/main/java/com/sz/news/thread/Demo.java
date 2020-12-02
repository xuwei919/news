package com.sz.news.thread;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 10:20
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        HelloThread thread = new HelloThread();
        thread.start();
        Thread.sleep(50);
        thread.flag = false;
    }

}

class HelloThread extends Thread {

    public volatile boolean flag = true;

    @Override
    public void run() {

        int n = 0;
        while (flag) {
            n++;
            System.out.println("thread is running....." + n);
        }
        System.out.println("thread end!");
    }
}