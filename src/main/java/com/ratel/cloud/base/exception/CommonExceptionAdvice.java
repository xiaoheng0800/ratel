package com.ratel.cloud.base.exception;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
/*import org.springframework.dao.DataIntegrityViolationException;*/
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ratel.cloud.base.bean.JsonResponse;
import com.ratel.cloud.base.bean.TypeResponse;
/**
 * All rights Reserved, Designed By gaoheng 
 * @Title:  CommonExceptionAdvice.java   
 * @Package com.ratel.cloud.base.exception   
 * @Description: 全局异常控制
 * @author: gaoheng     
 * @date:   2017年11月23日 上午8:05:29   
 * @version V1.0
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {
  private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
  /**
   * 400 -参数缺失异常
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public  JsonResponse handleMissingServletRequestParameterException(HttpServletRequest request,MissingServletRequestParameterException e) {
    logger.error(TypeResponse.PARAMETER_MISSING.getDesc(), e);
    return new JsonResponse(TypeResponse.PARAMETER_MISSING.getDesc(),TypeResponse.PARAMETER_MISSING.getValue(), e.getMessage(),request.getRequestURL().toString(),"");
  }
  /**
   * 400 - 参数解析失败
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public JsonResponse handleHttpMessageNotReadableException(HttpServletRequest request,HttpMessageNotReadableException e) {
    logger.error(TypeResponse.PARAMETER_PARSING_FAILED.getDesc(), e);
    return new JsonResponse(TypeResponse.PARAMETER_PARSING_FAILED.getDesc(),TypeResponse.PARAMETER_PARSING_FAILED.getValue(), e.getMessage(),request.getRequestURL().toString(),"");
  }
  /**
   * 400 -参数验证失败
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public JsonResponse handleMethodArgumentNotValidException(HttpServletRequest request,MethodArgumentNotValidException e) {
    logger.error(TypeResponse.PARAMETER_VALIDATION_FAILED.getDesc(), e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    return new JsonResponse(TypeResponse.PARAMETER_VALIDATION_FAILED.getDesc(),TypeResponse.PARAMETER_VALIDATION_FAILED.getValue(),message,request.getRequestURL().toString(),"");
  }
  /**
   * 400 - 参数绑定失败
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public JsonResponse handleBindException(HttpServletRequest request,BindException e) {
    logger.error(TypeResponse.PARAMETER_BINDING_FAILED.getDesc(), e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    return new JsonResponse(TypeResponse.PARAMETER_BINDING_FAILED.getDesc(),TypeResponse.PARAMETER_BINDING_FAILED.getValue(),message,request.getRequestURL().toString(),"");
  }
  /**
   * 400 - 参数验证失败
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public JsonResponse handleServiceException(HttpServletRequest request,ConstraintViolationException e) {
    logger.error(TypeResponse.PARAMETER_VALIDATION_FAILED.getDesc(), e);
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String message = violation.getMessage();
    return new JsonResponse(TypeResponse.PARAMETER_VALIDATION_FAILED.getDesc(),TypeResponse.PARAMETER_VALIDATION_FAILED.getValue(),message,request.getRequestURL().toString(),"");
  }
  /**
   * 400 -参数验证失败
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public JsonResponse handleValidationException(HttpServletRequest request,ValidationException e) {
    logger.error(TypeResponse.PARAMETER_VALIDATION_FAILED.getDesc(), e);
    return new JsonResponse(TypeResponse.PARAMETER_VALIDATION_FAILED.getDesc(),TypeResponse.PARAMETER_VALIDATION_FAILED.getValue(),"validation_exception",request.getRequestURL().toString(),"");
  }
  /**
   * 405 - 不支持当前请求方法
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public JsonResponse handleHttpRequestMethodNotSupportedException(HttpServletRequest request,HttpRequestMethodNotSupportedException e) {
    logger.error(TypeResponse.REQUEST_METHOD_SUPPORTED_FAILED.getDesc(), e);
    return new JsonResponse(TypeResponse.REQUEST_METHOD_SUPPORTED_FAILED.getDesc(),TypeResponse.REQUEST_METHOD_SUPPORTED_FAILED.getValue(),"request_method_not_supported",request.getRequestURL().toString(),"");
  }
  /**
   * 415 - 不支持当前媒体类型
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public JsonResponse handleHttpMediaTypeNotSupportedException(HttpServletRequest request,Exception e) {
    logger.error(TypeResponse.MEDIA_TYPE_SUPPORTED_FAILED.getDesc(), e);
    return new JsonResponse(TypeResponse.MEDIA_TYPE_SUPPORTED_FAILED.getDesc(),TypeResponse.MEDIA_TYPE_SUPPORTED_FAILED.getValue(),"content_type_not_supported",request.getRequestURL().toString(),"");
  }
  /**
   * 404 - 请求路径不存在
   */
  @ExceptionHandler(value = { NoHandlerFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public JsonResponse noHandlerFoundException(HttpServletRequest request,Exception e) {
	logger.error(TypeResponse.REQUEST_PATH_DOES_NOT_EXIST.getDesc(), e);
    return new JsonResponse(TypeResponse.REQUEST_PATH_DOES_NOT_EXIST.getDesc(), TypeResponse.REQUEST_PATH_DOES_NOT_EXIST.getValue(), e.getMessage(),request.getRequestURL().toString(),"");
   }
  /**
   * 500 - 业务逻辑异常
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServiceException.class)
  public JsonResponse handleServiceException(HttpServletRequest request,ServiceException e) {
    logger.error(TypeResponse.SERVICE_EXCEPTION.getDesc(), e);
    return new JsonResponse(TypeResponse.SERVICE_EXCEPTION.getDesc(), TypeResponse.SERVICE_EXCEPTION.getValue(), e.getMessage(),request.getRequestURL().toString(),"");
  }
  /**
   * 500 - 运行时异常
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public JsonResponse handleException(HttpServletRequest request,Exception e) {
    logger.error(TypeResponse.COMMON_EXCEPTION.getDesc(), e);
    return new JsonResponse(TypeResponse.COMMON_EXCEPTION.getDesc(), TypeResponse.COMMON_EXCEPTION.getValue(), e.getMessage(),request.getRequestURL().toString(),"");
  }
  /**
   * 操作数据库出现异常:名称重复，外键关联
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public JsonResponse handleException(HttpServletRequest request,DataIntegrityViolationException e) {
    logger.error(TypeResponse.DATABASE_EXCEPTION.getDesc(), e);
    return new JsonResponse(TypeResponse.DATABASE_EXCEPTION.getDesc(), TypeResponse.DATABASE_EXCEPTION.getValue(), e.getMessage(),request.getRequestURL().toString(),"");
  }
 
}