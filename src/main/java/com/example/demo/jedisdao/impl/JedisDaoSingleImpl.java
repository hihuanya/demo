package com.example.demo.jedisdao.impl;

import com.example.demo.jedisdao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author chuliuhuan
 * @date 2022-11-13 14:18
 */
@Service
public class JedisDaoSingleImpl implements JedisDao {
    /**使用@Autowired要开启组件扫描，不使用的话要通过pagebean的propertie注入进来
     *
     */
    @Resource
    private JedisPool jedispool;

    @Override
    public String set(String key, String value) {
        Jedis resource=jedispool.getResource();
        return resource.set(key, value);
    }

    @Override
    public String get(String key) {
        Jedis resource=jedispool.getResource();
        return resource.get(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis resource=jedispool.getResource();
        return resource.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        Jedis resource=jedispool.getResource();
        return resource.hget(key, field);
    }
    /**
     * 用于设置失效时间
     * @param key
     * @param sec
     * @return
     */
    @Override
    public Long expire(String key, int sec) {
        Jedis resource = jedispool.getResource();

        return resource.expire(key, sec);
    }
    /**
     * 删除jedis缓存
     * @param key
     * @return
     */
    @Override
    public Long del(String key) {
        Jedis resource = jedispool.getResource();

        return resource.del(key);
    }

}
