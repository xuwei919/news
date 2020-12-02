package com.sz.news.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sz.news.cache.NewsInfoCache;
import com.sz.news.dto.BidingNews2DTO;
import com.sz.news.dto.BidingNewsExportBean;
import com.sz.news.dto.PageDataBean;
import com.sz.news.model.BidingNews2;
import com.sz.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/10/23 10:11
 */
@Controller
@Slf4j
public class NewsController {

    @Resource
    private NewsService newsService;

    @RequestMapping("/")
    public String toIndex() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageDataBean<BidingNews2DTO> list(BidingNews2DTO news2VO) {

        //        List<BidingNews2> newsList = newsService.newsList(pageNum, pageSize);

        List<BidingNews2> news2List = newsService.newsList(news2VO);
        ArrayList<BidingNews2DTO> list = new ArrayList<>();
        for (BidingNews2 bean : news2List) {
            BidingNews2DTO vo = new BidingNews2DTO();
            BeanUtils.copyProperties(bean, vo);
            list.add(vo);
        }
        int totalCount = newsService.getTotalCount();
        PageDataBean<BidingNews2DTO> pageDataBean = new PageDataBean<>();
        pageDataBean.setTotal(totalCount);
        pageDataBean.setRows(list);
        return pageDataBean;
    }

    @RequestMapping(value = "/export")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) {
        try {
            List<BidingNews2> news2List = newsService.newsList();
            List<BidingNewsExportBean> list = new ArrayList<>();
            for (BidingNews2 bidingNews2 : news2List) {
                BidingNewsExportBean bean = new BidingNewsExportBean();
                BeanUtils.copyProperties(bidingNews2, bean);
                list.add(bean);
            }

            String fileName = new SimpleDateFormat("MMddHHmmss").format(new Date());
            String sheetName = "sheet1";
            writeExcel(response, list, fileName, sheetName, new BidingNewsExportBean());

        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        }

    }

    public void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> list, String fileName,
            String sheetName, BaseRowModel model) throws Exception {
        ExcelWriter writer = new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, model.getClass());
        sheet.setSheetName(sheetName);
        sheet.setTableStyle(createTableStyle());
        sheet.setAutoWidth(Boolean.TRUE);
        HashMap<Integer, Integer> map = new HashMap<>(4);
        map.put(0, 3000);
        map.put(1, 6000);
        map.put(2, 30000);
        map.put(3, 10000);
        sheet.setColumnWidthMap(map);
        writer.write(list, sheet);
        writer.finish();
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    private TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        Font font = new Font();
        font.setBold(Boolean.TRUE);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("黑体");
        tableStyle.setTableHeadFont(font);
        tableStyle.setTableHeadBackGroundColor(IndexedColors.WHITE);

        Font contentFont = new Font();
        contentFont.setBold(Boolean.FALSE);
        contentFont.setFontHeightInPoints((short) 12);
        contentFont.setFontName("宋体");
        tableStyle.setTableContentFont(contentFont);

        return tableStyle;
    }

    @GetMapping("/cache")
    @ResponseBody
    public BidingNews2 getinfoFromCache(String title) {
        NewsInfoCache newsInfoCache = new NewsInfoCache();
        //        return newsInfoCache.getData(title);
        return null;
    }

}
