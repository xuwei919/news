package com.sz.news.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/2 10:55
 */
@Controller
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/send/{num}")
    @ResponseBody
    public String send(@PathVariable("num") int num) {

        QuueTask queueTask = new QuueTask(amqpTemplate);

        Thread thread = new Thread(queueTask);
        thread.start();

        return "success";
    }

}

class QuueTask implements Runnable {

    private AmqpTemplate amqpTemplate;

    public QuueTask(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void run() {
        while (true) {
            amqpTemplate.convertAndSend("hello-news", UUID.randomUUID().toString());
        }
    }
}


