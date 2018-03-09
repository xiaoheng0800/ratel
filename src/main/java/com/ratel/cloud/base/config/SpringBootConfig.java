package com.ratel.cloud.base.config;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  SpringBootConfig.java   
 * @Package com.ratel.cloud.base.config   
 * @Description: 通用型配置，该类被其他模块注入使用
 * @author: gaoheng     
 * @date:   2017年11月20日 下午4:56:42   
 * @version V1.0
 */
@Configuration	// 此处为配置项
public class SpringBootConfig {
	/**
	 * @Title: servletContainer   
	 * @Description: 设置容器默认启动为jetty
	 * @param: @return      
	 * @return: EmbeddedServletContainerFactory      
	 * @throws
	 */
	 @Bean  
	 public EmbeddedServletContainerFactory servletContainer() {  
			JettyEmbeddedServletContainerFactory factory =  new JettyEmbeddedServletContainerFactory();  
			return factory;  
	  }
	 
}
