package com.sz.news.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/10/23 17:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BidingNews2DTO extends BaseModel {

    private static final long serialVersionUID = -4979349784980544907L;

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
}
