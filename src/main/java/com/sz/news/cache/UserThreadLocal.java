package com.sz.news.cache;

import com.sz.news.model.UserBean;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 15:59
 */
public class UserThreadLocal {

    private static final ThreadLocal<UserBean> threadlocal = new ThreadLocal<>();

    public static void set(UserBean userBean) {
        threadlocal.set(userBean);
    }

    public static UserBean get() {
        return threadlocal.get();
    }

    public static void remove() {
        threadlocal.remove();
    }

}
