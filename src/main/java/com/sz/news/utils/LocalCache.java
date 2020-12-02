package com.sz.news.utils;

import com.google.common.base.Supplier;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @program: news
 * @description 本地缓存
 * @author: 许伟
 * @create: 2020/11/23 16:08
 */
public class LocalCache<K, V> {

    /**
     * 默认的本地缓存最大数据长度
     */
    private static final long DEFAULT_MAXIMUM_SIZE = 5_000;

    private static final long MAX_EXPIRE_AFTER_WRITE = 60L;

    /**
     * 本地缓存数据长度为1
     */
    private static final long SINGLE_SIZE = 1;


    private Cache<K, Optional<V>> cache;

    public LocalCache() {
    }

    public static <K, V> LocalCache<K, V> create() {
        LocalCache<K, V> local = new LocalCache<>();
        local.cache = CacheBuilder.newBuilder().maximumSize(DEFAULT_MAXIMUM_SIZE).
                expireAfterWrite(MAX_EXPIRE_AFTER_WRITE, TimeUnit.SECONDS).build();
        return local;
    }

    /**
     * 创建本地缓存对象
     *
     * @param expireAfterWrite 写入缓存后失效的秒数
     * @return
     */
    public static <K, V> LocalCache<K, V> create(long expireAfterWrite) {
        LocalCache<K, V> local = new LocalCache<K, V>();

        local.cache = CacheBuilder.newBuilder()
                .maximumSize(DEFAULT_MAXIMUM_SIZE)
                .expireAfterWrite(expireAfterWrite <= 0 ? MAX_EXPIRE_AFTER_WRITE : expireAfterWrite, TimeUnit.SECONDS)
                .build();
        return local;
    }

    /**
     * 创建只能缓存一个数据元素的本地缓存对象
     *
     * @return
     */
    public static <K, V> LocalCache<K, V> createSingleSize() {
        LocalCache<K, V> local = new LocalCache<>();

        local.cache = CacheBuilder.newBuilder()
                .maximumSize(SINGLE_SIZE)
                .expireAfterWrite(MAX_EXPIRE_AFTER_WRITE, TimeUnit.SECONDS)
                .build();
        return local;
    }


    private Cache<K, Optional<V>> getCache() {
        return this.cache;
    }

    /**
     * 获取缓存k对应的值，没有值返回null
     *
     * @param k 缓存key
     * @return
     */
    public Optional<V> get(K k) {
        return getCache().getIfPresent(k);
    }


    /**
     * 获取缓存key对应的值，若key未在本地缓存中，使用call进行初始化
     *
     * @param k    缓存key
     * @param call 若key未在本地缓存中，使用call进行初始化。
     *             可用Lambada表达式， （） ->{ 具体逻辑,return V };
     * @return
     */
    public V get(K k, Supplier<V> call) {
        if (k == null) {
            return null;
        }
        /**
         * 由于Guava的Callable接口中，若采用过期机制，
         * 如果自带的Callable返回了null，get(xx,CallAble)便会抛出异常： CacheLoader returned null for key
         * 故采用Optional + 额外的Supplier
         */
        Optional<V> value = get(k);
        //未放置本地缓存数据
        if (value == null) {
            V v = call.get();
            getCache().put(k, Optional.ofNullable(v));
            return v;
        }
        return value.orElse(null);
    }

}
