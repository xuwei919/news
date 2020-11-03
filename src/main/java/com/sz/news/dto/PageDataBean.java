package com.sz.news.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/10/23 16:45
 */
@Data
public class PageDataBean<T> {

    /**
     * 实体类集合
     */
    private List<T> rows = new ArrayList<T>();
    /**
     * 数据总条数
     */
    private int total;

}
