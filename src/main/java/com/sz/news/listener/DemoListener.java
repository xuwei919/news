package com.sz.news.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/30 18:16
 */
@Component
public class DemoListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        if (event instanceof DemoEvent) {
            System.out.println("接收到内容" + event.getSource());
        }

    }
}
