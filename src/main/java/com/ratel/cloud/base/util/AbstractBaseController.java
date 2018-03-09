package com.ratel.cloud.base.util;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  AbstractBaseController.java   
 * @Package com.ratel.cloud.base.controller   
 * @Description: 控制器的抽象类
 * @author: gaoheng     
 * @date:   2017年11月17日 下午12:23:43   
 * @version V1.0
 */
public abstract class AbstractBaseController {
	@Resource
	private MessageSource messageSource; // 自动注入此资源对象
	public String getMessage(String key, String... args) {
		return this.messageSource.getMessage(key, args, Locale.getDefault()); 
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {	// 在本程序里面需要针对于日期格式进行处理
		// 首先建立一个可以将字符串转换为日期的工具程序类
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
		// 明确的描述此时需要注册一个日期格式的转化处理程序类
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
}
