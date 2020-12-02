package com.sz.news.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/2 10:37
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello-news");
    }

    @Bean
    public Queue queue2() {
        return new Queue("test");
    }

}
