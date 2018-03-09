package com.ratel.cloud.base.config;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  RedisCache.java   
 * @Package com.ratel.cloud.base.config   
 * @Description:二级缓存配置
 * @author: gaoheng
 * @date:   2017年12月10日 下午2:24:50   
 * @version V1.0
 */
public class RedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private RedisTemplate<Object, Object> redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间
    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void putObject(Object key, Object value) {
        try {
            RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
            ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
            opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            logger.debug("Put query result to redis");
        }
        catch (Throwable t) {
            logger.error("Redis put failed", t);
        }
    }
    @Override
    public Object getObject(Object key) {
        try {
            RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
            ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
            logger.debug("Get cached query result from redis");
            return opsForValue.get(key);
        }
        catch (Throwable t) {
            logger.error("Redis get failed, fail over to db", t);
            return null;
        }
    }
    @Override
    public Object removeObject(Object key) {
        try {
            RedisTemplate<Object, ?> redisTemplate = getRedisTemplate();
            redisTemplate.delete(key);
            logger.debug("Remove cached query result from redis");
        }
        catch (Throwable t) {
            logger.error("Redis remove failed", t);
        }
        return null;
    }
    @Override
    public void clear() {
        RedisTemplate<?, ?> redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback<?>) connection -> {
            connection.flushDb();
            return null;
        });
        logger.debug("Clear all the cached query result from redis");
    }
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
    private RedisTemplate<Object, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}