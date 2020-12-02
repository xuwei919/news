package com.sz.news.cache;

import java.math.BigDecimal;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 17:18
 */
public class FruitGenerator implements Generator<Apple> {

    @Override
    public Apple next() {
        return new Apple("red", BigDecimal.valueOf(0.08));
    }
}
