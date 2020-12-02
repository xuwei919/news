package com.sz.news.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/30 18:22
 */
public class DemoEvent extends ApplicationEvent {

    public DemoEvent(final String content) {
        super(content);
    }
}
