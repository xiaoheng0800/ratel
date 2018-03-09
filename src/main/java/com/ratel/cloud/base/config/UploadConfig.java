package com.ratel.cloud.base.config;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  UploadConfig.java   
 * @Package com.ratel.cloud.base.config   
 * @Description:文件上传设置配置类
 * @author: gaoheng
 * @date:   2017年12月28日 下午2:51:54   
 * @version V1.0
 */
@Configuration
public class UploadConfig { 
	@Bean
	public MultipartConfigElement getMultipartConfig() {
		MultipartConfigFactory config = new MultipartConfigFactory() ;
		config.setMaxFileSize("10MB"); 	// 设置上传文件的单个大小限制
		config.setMaxRequestSize("100MB"); 	// 设置总的上传的大小限制
		config.setLocation("/"); // 设置临时保存目录
		return config.createMultipartConfig() ;	// 创建一个上传配置
	}
}