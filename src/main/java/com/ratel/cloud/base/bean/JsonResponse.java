package com.ratel.cloud.base.bean;
/**
 * All rights Reserved, Designed By Android_Robot   
 * @Title:  ErrorResponse.java   
 * @Package com.rate.cloud.base.exception   
 * @Description:JSONAPI
 * @author: gaoheng     
 * @date:   2017年11月19日 下午11:31:02   
 * @version V1.0
 */
public class JsonResponse {
	   /**
	    * 异常类型
	    */
	    private String errorType;
	    /**
	     * 异常代码
	     */
	    private String code;
	    /**
	     * 异常信息
	     */
	    private String message;
	    /**
	     * json主体信息
	     */
	    private Object data;
	    /**
	     * 异常发生路径
	     */
	    private String url ;
	    
		public JsonResponse(String errorType, String code, String message, String url,Object data) {
			super();
			this.errorType = errorType;
			this.code = code;
			this.message = message;
			this.url = url;
			this.data = data;
		}
		public String getErrorType() {
			return errorType;
		}

		public void setErrorType(String errorType) {
			this.errorType = errorType;
		}

		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return "ErrorResponse [errorType=" + errorType + ", code=" + code
					+ ", message=" + message + ", url=" + url + "]";
		}
		
		
		 
}
