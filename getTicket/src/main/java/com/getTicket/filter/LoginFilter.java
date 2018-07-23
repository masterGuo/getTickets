package com.getTicket.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 用户过滤器
 * 
 * @author Guoqi
 *
 */

public class LoginFilter implements Filter {
    private static Logger logger = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	// 获得在下面代码中要用的request,response,session对象
	HttpServletRequest servletRequest = (HttpServletRequest) request;
	HttpServletResponse servletResponse = (HttpServletResponse) response;
	servletResponse.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type,Depth,User-Agent,X-File-Size,"
		+ ",X-Requested-With,X-Requested-By,If-Modified-Since,X-File-Name,X-File-Type,Cache-Control,Origin");
	servletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
	servletResponse.setHeader("Access-Control-Allow-Origin", "www.globalinterpark.com");
	servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
	servletRequest.setAttribute("Access-Control-Allow-Origin", "*");
	logger.info("-----------");
	 chain.doFilter(request, response);


    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }}

