package com.sz.news.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/11/2 16:55
 */
@Data
public class BidingNewsExportBean extends BaseRowModel {

    @ExcelProperty(value = "序号", index = 0)
    private Integer id;

    @ExcelProperty(value = "标题", index = 1)
    private String title;

    @ExcelProperty(value = "网址", index = 2)
    private String url;

    @ExcelProperty(value = "发布时间", index = 3, format = "yyyy-MM-dd HH:mm:ss")
    private Date pubdate;
}
