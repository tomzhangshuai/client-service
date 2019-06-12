package com.wufanbao.api.distributionservice.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * redis 帮助类是
 */
@Component
public class RedisUtils {

    @Autowired
    private JedisPool jedisPool;
   /* @Autowired
    private RedisTemplate<String, Object> redisTemplate;*/

    /**
     * 为key设置过期时长
     * @param key
     * @param seconds
     * @return
     */
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

    /**
     * 设置
     * @param key
     * @param str
     * @return
     */
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

    /**
     * 设置
     * @param key
     * @param str
     * @return
     */
    public boolean set(String key, String str,int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //nxxx   nx 不存在时设置  xx 存在时设置
            //expx   只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒
            jedis.set(key, str);
            jedis.expire(key,seconds);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
        return true;
    }

    /**
     *
     * @param key
     * @return
     */
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

    /**
     * 获取
     * @param key
     * @return
     */
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

    public Map<String, String> getAll(String key) {
        Jedis jedis = null;
        Map<String, String> result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 是否存在key
     * @param key
     * @return
     */
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

    /**
     * 删除
     * @param key
     * @return
     */
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

    /*public boolean hasKey(String key, String hashKey) {

        return redisTemplate.opsForHash().hasKey(key, hashKey);

    }*/
}
