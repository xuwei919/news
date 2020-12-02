package com.sz.news.dao;

import com.sz.news.dto.BidingNews2DTO;
import com.sz.news.model.BidingNews2;

import java.util.List;

public interface BidingNews2Dao {

    int deleteByPrimaryKey(Integer id);

    int insert(BidingNews2 record);

    int insertSelective(BidingNews2 record);

    BidingNews2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidingNews2 record);

    int updateByPrimaryKey(BidingNews2 record);

    /**
     * 分页获取
     *
     * @return
     */
    List<BidingNews2> getNewsPage(BidingNews2DTO news2VO);

    /**
     * 查询所有
     *
     * @return
     */
    List<BidingNews2> findAll();

    int getTotalCount();

    /**
     * 根据标题查询数据
     *
     * @param key
     * @return
     */
    BidingNews2 selectNewsByTitle(String title);
}