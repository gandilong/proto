package com.thang.tools.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.thang.tools.model.Constant;

@WebFilter(filterName="authFilter",urlPatterns="/*",asyncSupported=true,dispatcherTypes={DispatcherType.REQUEST,DispatcherType.ASYNC,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR})
public class AuthFilter implements Filter {

	private static Logger logger=Logger.getLogger(AuthFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("权限过滤器初始化...");
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        String uri=req.getRequestURI();
        String contextPath=request.getServletContext().getContextPath();
        if(uri.equals(contextPath+"/")||uri.startsWith(contextPath+"/index")||uri.startsWith(contextPath+"/WEB-INF")||uri.startsWith(contextPath+"/tools")||uri.startsWith(contextPath+"/common")||uri.startsWith(contextPath+"/net")){
        	filterChain.doFilter(request, response);
        }else{
        	
        	//权限过滤 start
        	HttpSession session=req.getSession(true);
            if(null!=session.getAttribute(Constant.CURRENT_USER)){
            	filterChain.doFilter(request, response);
            }else{
            	String isAsyn=req.getHeader("X-Requested-With");
            	if(null!=isAsyn&&"XMLHttpRequest".equals(isAsyn)){//异步请求处理
            		PrintWriter out=response.getWriter();
            		out.print("{code:401,error:'no power'}");
            		out.close();
            	}else{
            		resp.setStatus(401);
            	}
            }
            //权限过滤 end
            
        }
	}

	@Override
	public void destroy() {
		logger.debug("权限过滤器销毁...");
	}	

}
