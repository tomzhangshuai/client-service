package com.wufanbao.api.oldclientservice.core;

/**
 * User:Wangshihao
 * Date:2017-08-02
 * Time:14:17
 */
public interface LRUCacheImpl<K, V> {
    /**
     * 获取当前缓存大小
     */
    int size();

    /**
     * 获取存活时间
     */
    long defaultLifeTime();

    /**
     * 向缓存添加value对象，默认存活时间
     */
    void put(K key, V value);

    /**
     * 向缓存添加value对象，并且添加一个存活时间
     */
    void put(K key, V value, long expire);

    /**
     * 查找缓存对象
     */
    V get(K key);

    /**
     * 淘汰对象
     *
     * @return 被删除对象大小
     */
    int eliminate();

    /**
     * 检查缓存是否已经被填满
     */
    boolean isFull();

    /**
     * 删除缓存对象
     */
    void rm(K key);

    /**
     * 清除所有缓存
     */
    void clear();

    /**
     * 返回缓存总大小
     */
    int getFullSize();

    /**
     * 判断缓存是否为空
     */
    boolean isEmpty();
}
