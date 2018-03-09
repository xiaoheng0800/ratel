package com.ratel.cloud.base.config;

import com.ratel.cloud.base.util.AbstractBaseController;
import com.ratel.cloud.base.util.StringUtils;

/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  Global.java   
 * @Package com.ratel.cloud.base.config   
 * @Description:全局配置类
 * @author: gaoheng
 * @date:   2017年12月6日 下午4:56:14   
 * @version V1.0
 */
public class Global extends AbstractBaseController{
	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	/**
	 * session对象标识
	 */
	public static final String USER = "user";
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value =Global.getInstance().getMessage(key);
		if(StringUtils.isNotBlank(value)){
		    return value;
		}
		else{
			return "";
		}
	}
}
