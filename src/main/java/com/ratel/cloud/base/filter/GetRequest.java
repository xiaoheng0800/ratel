package com.ratel.cloud.base.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.ratel.cloud.base.util.RequestUtils;
/**
 * All rights Reserved, Designed By gaoheng
 * @Title:  GetRequest.java   
 * @Package com.ratel.cloud.base.filter   
 * @Description:获取request过滤器类
 * @author: gaoheng
 * @date:   2017年12月7日 下午11:15:21   
 * @version V1.0
 */
public class GetRequest implements Filter {
	public GetRequest() {  
        // TODO Auto-generated constructor stub  
    }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
    @Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        // place your code here  
        RequestUtils.setRequest((HttpServletRequest)request);  
        // pass the request along the filter chain  
        chain.doFilter(request, response);  
    }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

    
}
