package com.sz.news.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/2 10:57
 */
@Component
@RabbitListener(queues = {"hello-news","test"})
public class QueueListener {

    @RabbitHandler
    public void process(String message) {
        System.out.println("接收到消息：" + message);
    }

}
