package com.ratel.cloud.base.config;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  Swagger2Config.java   
 * @Package com.ratel.cloud.base.config   
 * @Description:Swagger监控配置类 
 * @author: gaoheng
 * @date:   2017年11月30日 下午2:32:48   
 * @version V1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter{
	@Resource
	private MessageSource messageSource; // 自动注入此资源对象
	public String getMessage(String key, String... args) {
		return this.messageSource.getMessage(key, args, Locale.getDefault()); 
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html") .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**") .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	@Bean
	public Docket getDocket() { // 此类主要是整个的Swagger配置项，利用这个类需要来指派扫描包
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.getApiInfo()).select().apis(RequestHandlerSelectors.basePackage(getMessage("doc.api.path")))
				.paths(PathSelectors.any()).build(); // 设置文档的显示类型
	}
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("微服务环境")
				.description("www.pingtg.cn")
				.termsOfServiceUrl("http://www.pingtg.cn").contact("平头哥工作室")
				.license("高恒").version("1.0").build();
	}
}
