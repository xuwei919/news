package com.sz.news.controller;

import com.sz.news.cache.UserThreadLocal;
import com.sz.news.listener.DemoEvent;
import com.sz.news.model.UserBean;
import com.sz.news.service.NewsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/30 18:20
 */
@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private NewsService newsService;

    @GetMapping("/send")
    @ResponseBody
    public String hello() {

        applicationContext.publishEvent(new DemoEvent("发送消息成功"));

        return "success";
    }

    @GetMapping("/cache")
    @ResponseBody
    public Object cache() {
        UserBean admin = new UserBean("admin", 18);

        UserThreadLocal.set(admin);

        int totalCount = newsService.getTotalCount();

        return admin;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private int age;
}