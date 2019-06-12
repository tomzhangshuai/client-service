package com.wufanbao.api.oldclientservice.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

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

    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        List<String> strs = null;
        try {
            jedis = jedisPool.getResource();
            strs = jedis.lrange(key, start, end);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
        return strs;
    }

    //在key对应 list的头部添加字符串元素
    public long lpush(String key, String... strings) {
        Jedis jedis = null;
        long str = 0;
        try {
            jedis = jedisPool.getResource();
            str = jedis.lpush(key, strings);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
        return str;
    }

    //在key对应 list 的尾部添加字符串元素
    public long rpush(String key, String... strings) {
        Jedis jedis = null;
        long str = 0;
        try {
            jedis = jedisPool.getResource();
            str = jedis.rpush(key, strings);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }
        return str;
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
}
