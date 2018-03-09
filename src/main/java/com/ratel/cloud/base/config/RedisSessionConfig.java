package com.ratel.cloud.base.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.clients.jedis.JedisPoolConfig;

import com.ratel.cloud.base.util.AbstractBaseController;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  RedisSessionConfig.java   
 * @Package com.ratel.cloud.base.config   
 * @Description: session用redis缓存数据库配置类
 * @author: gaoheng
 * @date:   2017年12月4日 下午8:48:03   
 * @version V1.0
 */
@Configuration  
@EnableRedisHttpSession( maxInactiveIntervalInSeconds = 18000)
public class RedisSessionConfig extends AbstractBaseController{
	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connection = new JedisConnectionFactory();
		connection.setPort(Integer.valueOf(super.getMessage("redis.one.port")));
		connection.setHostName(super.getMessage("redis.one.host"));
		connection.setPassword(super.getMessage("redis.one.password"));
		connection.setDatabase(Integer.valueOf(super.getMessage("redis.one.database")));
		connection.setTimeout(Integer.valueOf(super.getMessage("redis.one.timeout")));
		JedisPoolConfig poolConfig = new JedisPoolConfig(); // 进行连接池配置
		poolConfig.setMaxTotal(Integer.valueOf(super.getMessage("redis.one.pool.max-active")));
		poolConfig.setMaxIdle(Integer.valueOf(super.getMessage("redis.one.pool.max-idle")));
		poolConfig.setMinIdle( Integer.valueOf(super.getMessage("redis.one.pool.min-idle")));
		poolConfig.setMaxWaitMillis(Long.valueOf(super.getMessage("redis.one.pool.max-wait")));
		connection.setPoolConfig(poolConfig);
		connection.afterPropertiesSet(); // 初始化连接池配置
		return connection;
	}

}
