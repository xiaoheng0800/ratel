package com.ratel.cloud.base.util;
import javax.servlet.http.HttpServletRequest;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  RequestUtils.java   
 * @Package com.ratel.cloud.base.util   
 * @Description: 获取request工具类
 * @author: gaoheng
 * @date:   2017年12月7日 下午11:12:50   
 * @version V1.0
 */
public class RequestUtils {  
    private static ThreadLocal<HttpServletRequest> requestLocal= new ThreadLocal<HttpServletRequest>();  
    public static HttpServletRequest getRequest() {  
    	HttpServletRequest request =requestLocal.get();
    	if(request!=null){
         return (HttpServletRequest)requestLocal.get(); 
    	}else{
    	 return null;
    	}
    }  
    public static void setRequest(HttpServletRequest request) {  
          requestLocal.set(request);    
    }  
}
	