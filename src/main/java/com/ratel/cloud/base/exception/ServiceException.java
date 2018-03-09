package com.ratel.cloud.base.exception;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  ServiceException.java   
 * @Package com.ratel.cloud.base.exception   
 * @Description: 自定义异常类
 * @author: gaoheng
 * @date:   2017年11月30日 下午5:10:42   
 * @version V1.0
 */
public class ServiceException extends RuntimeException {
  /**   
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;

public ServiceException(String msg) {
    super(msg);
  }
}