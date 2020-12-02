package com.sz.news.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sz.news.model.BidingNews2;
import com.sz.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NewsInfoCache {

    @Autowired
    private static NewsService newsService;

    private static final LoadingCache<String, BidingNews2> cahceBuilder;

    static {

        cahceBuilder = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, BidingNews2>() {
                    @Override
                    public BidingNews2 load(String key) {
                        return getData(key);
                    }
                });
    }

    private static BidingNews2 getData(String key) {
        return newsService.selectNewsByTitle(key);
    }

    public BidingNews2 get(String id) throws Exception {
        return cahceBuilder.get(id);
    }
}