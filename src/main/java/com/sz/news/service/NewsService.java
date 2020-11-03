package com.sz.news.service;

import com.sz.news.model.BidingNews2;
import com.sz.news.dto.BidingNews2DTO;

import java.util.List;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/10/23 10:41
 */
public interface NewsService {

    /**
     * 获取列表
     *
     * @return
     */
    List<BidingNews2> newsList();

    /**
     * 分页查询
     *
     * @param news2VO
     * @return
     */
    List<BidingNews2> newsList(BidingNews2DTO news2VO);

    /**
     * 获取数据总数
     */
    int getTotalCount();
}
