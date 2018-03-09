package com.ratel.cloud.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  RestConfig.java   
 * @Package com.ratel.cloud.base.config   
 * @Description: RestTemplate配置类
 * @author: gaoheng
 * @date:   2017年11月28日 下午7:48:02   
 * @version V1.0
 */
@Configuration
public class RestConfig {
	@Bean
	public RestTemplate getRestTemplate() { 
		return new RestTemplate() ;
	}
}
