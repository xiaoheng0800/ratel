package com.ratel.cloud.base.config;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.ratel.cloud.base.filter.GetRequest;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  WebConfig.java   
 * @Package com.ratel.cloud.base.config   
 * @Description:获取request配置类
 * @author: gaoheng
 * @date:   2017年12月7日 下午11:17:11   
 * @version V1.0
 */
 @Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	@Bean
	public FilterRegistrationBean getDemoFilter(){
		GetRequest getRequest=new GetRequest();
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(getRequest);
		List<String> urlPatterns=new ArrayList<String>();
		urlPatterns.add("/*");//拦截路径，可以添加多个
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
}