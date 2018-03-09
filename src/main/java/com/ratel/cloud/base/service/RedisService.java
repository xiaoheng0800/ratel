package com.ratel.cloud.base.service;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  RedisService.java   
 * @Package com.ratel.cloud.base.service   
 * @Description: redisTemplate封装类
 * @author: gaoheng
 * @date:   2017年12月10日 下午3:47:12   
 * @version V1.0
 */
@Service
public class RedisService {
    @Resource
	private RedisTemplate<Object, Object> redisTemplate;
    /**
	 * 根据指定o获取Object
	 * @param o
	 * @return
	 */
	public Object getObj(Object o) {
		return this.redisTemplate.opsForValue().get(o);
	}
    /**
	 * 设置obj缓存
	 * 
	 * @param o1
	 * @param o2
	 */
	public void setObj(Object o1, Object o2) {
		this.redisTemplate.opsForValue().set(o1, o2);
	}
    /**
	 * 删除Obj缓存
	 * 
	 * @param o
	 */
	public void delObj(Object o) {
		this.redisTemplate.delete(o);
	}

}