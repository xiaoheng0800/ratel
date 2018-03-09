package com.ratel.cloud.base.bean;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  TypeResponse.java   
 * @Package com.ratel.cloud.base.bean   
 * @Description: 返回类型枚举类
 * @author: gaoheng
 * @date:   2017年12月10日 下午7:17:02   
 * @version V1.0
 */
public enum TypeResponse {
    PARAMETER_MISSING("400", "参数缺失异常"), 
	PARAMETER_PARSING_FAILED("400", "参数解析失败"),
	PARAMETER_VALIDATION_FAILED("400", "参数验证失败"),
	PARAMETER_BINDING_FAILED("400", "参数绑定失败"),
	REQUEST_METHOD_SUPPORTED_FAILED("405", "不支持当前请求方法"),
    MEDIA_TYPE_SUPPORTED_FAILED("415", "不支持当前媒体类型"),
    REQUEST_PATH_DOES_NOT_EXIST("404", "请求路径不存在"),
    SERVICE_EXCEPTION("500", "业务逻辑异常"),
    COMMON_EXCEPTION("100","运行时异常"),
    DATABASE_EXCEPTION("300","操作数据库异常"),
    SUCCESS("200","操作成功");
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private TypeResponse(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}