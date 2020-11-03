package com.sz.news.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biding_news_2
 *
 * @author
 */
@Data
public class BidingNews2 implements Serializable {

    private Integer id;

    /**
     * ???±???
     */
    private String title;

    private String url;

    /**
     * ???·???ʱ??
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pubdate;

    private static final long serialVersionUID = 1L;
}