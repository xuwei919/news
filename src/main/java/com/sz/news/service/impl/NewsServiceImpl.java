package com.sz.news.service.impl;

import com.alibaba.fastjson.JSON;
import com.sz.news.cache.UserThreadLocal;
import com.sz.news.dao.BidingNews2Dao;
import com.sz.news.dto.BidingNews2DTO;
import com.sz.news.model.BidingNews2;
import com.sz.news.model.UserBean;
import com.sz.news.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/10/23 10:41
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private BidingNews2Dao bidingNews2Dao;

    @Override
    public List<BidingNews2> newsList() {
        return bidingNews2Dao.findAll();
    }

    @Override
    public List<BidingNews2> newsList(BidingNews2DTO news2VO) {
        return bidingNews2Dao.getNewsPage(news2VO);
    }

    @Override
    public int getTotalCount() {
        UserBean userBean = UserThreadLocal.get();
        System.out.println("获取缓存：" + JSON.toJSONString(userBean));
        return bidingNews2Dao.getTotalCount();
    }

    @Override
    public BidingNews2 selectNewsByTitle(String key) {
        return bidingNews2Dao.selectNewsByTitle(key);
    }
}
