package com.sz.news.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/10/23 17:30
 */
@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1627943298951966021L;

    /**
     * 每页显示数量
     */

    private int limit;
    /**
     * 页码
     */
    private int page;

    /**
     * sql语句起始索引
     */
    private int offset;

}
