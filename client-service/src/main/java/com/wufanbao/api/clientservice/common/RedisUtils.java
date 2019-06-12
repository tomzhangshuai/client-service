package com.wufanbao.api.clientservice.common;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//redist 帮助类
@Component
public class RedisUtils {

    @Autowired
    private JedisPool jedisPool;

    //为key设置过期时长
    public boolean expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, seconds);//设置过期时长
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
        return true;
    }

    //设置
    public boolean set(String key, String str) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, str);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
        return true;
    }

    public long incr(String key) {
        Jedis jedis = null;
        long count = 0;
        try {
            jedis = jedisPool.getResource();
            count = jedis.incr(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
        return count;
    }

    //获取
    public String get(String key) {
        Jedis jedis = null;
        String str = null;
        try {
            jedis = jedisPool.getResource();
            str = jedis.get(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
        return str;
    }

    //是否存在key
    public boolean exists(String key) {
        Jedis jedis = null;
        boolean b = false;
        try {
            jedis = jedisPool.getResource();
            b = jedis.exists(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
        return b;
    }

    //删除
    public boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
        return true;
    }
    //获取剩余时间
    public long ttl(String key) {
        Jedis jedis = null;
        long time=-1;
        try {
            jedis = jedisPool.getResource();
            time=jedis.ttl(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -2;
        } finally {
            jedis.close();
        }
        return time;
    }
}
