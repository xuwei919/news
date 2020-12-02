package com.sz.news.cache;

import java.math.BigDecimal;

/**
 * @program: news
 * @description
 * @author: 许伟
 * @create: 2020/12/1 17:11
 */
public class FxDemo {

    public static void main(String[] args) {

        Fruit<Apple> fruit = new Fruit<>();

        fruit.setData(new Apple("red", BigDecimal.valueOf(1.5)));

        System.out.println(fruit.getData());

        FruitGenerator fruitGenerator = new FruitGenerator();
        System.out.println(fruitGenerator.next());

    }

}
