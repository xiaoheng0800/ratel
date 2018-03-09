package com.ratel.cloud.base.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import com.ratel.cloud.base.util.AbstractBaseController;
import com.ratel.cloud.base.util.RedisObjectSerializer;
@Configuration
public class RedisConfig extends AbstractBaseController{
	public RedisConnectionFactory getRedisConnectionFactory(String hostName,
			String password, int port, int maxActive, int maxIdle, int minIdle,
			long maxWait, int database,int timeout) { // 是负责建立Factory的连接工厂类
		JedisConnectionFactory jedisFactory = new JedisConnectionFactory();
		jedisFactory.setHostName(hostName);
		jedisFactory.setPort(port);
		jedisFactory.setPassword(password);
		jedisFactory.setDatabase(database);
		jedisFactory.setTimeout(timeout);
		JedisPoolConfig poolConfig = new JedisPoolConfig(); // 进行连接池配置
		poolConfig.setMaxTotal(maxActive);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWait);
		jedisFactory.setPoolConfig(poolConfig);
		jedisFactory.afterPropertiesSet(); // 初始化连接池配置
		return jedisFactory;
	}
	@Bean
	public RedisTemplate<Object, Object> getRedisTemplateTwo() {
		String hostName=super.getMessage("redis.two.host");
		String password = super.getMessage("redis.two.password");
		int port = Integer.valueOf(super.getMessage("redis.two.port"));
		int database =  Integer.valueOf(super.getMessage("redis.two.database"));
		int timeout =  Integer.valueOf(super.getMessage("redis.two.timeout"));
		int maxActive = Integer.valueOf(super.getMessage("redis.two.pool.max-active"));
		int maxIdle =  Integer.valueOf(super.getMessage("redis.two.pool.max-idle"));
		int minIdle =  Integer.valueOf(super.getMessage("redis.two.pool.min-idle"));
		long maxWait = Long.valueOf(super.getMessage("redis.two.pool.max-wait"));
        RedisConnectionFactory factory = this.getRedisConnectionFactory(hostName, password, port, maxActive, maxIdle, minIdle, maxWait,database,timeout); // 建立Redis的连接
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
		redisTemplate.setValueSerializer(new RedisObjectSerializer()); // value的序列化类型
		return redisTemplate;
	}
}