package com.thang.tools.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.thang.tools.util.StrUtils;

public class Action extends HttpServlet{

	private static final long serialVersionUID = 2119689331937527863L;
	private static final Gson gson=new Gson();
	private static Logger logger=Logger.getLogger(Action.class);

	protected HttpServletRequest request=null;
	protected HttpServletResponse response=null;
	protected ActionValues values=null;
	
	protected PrintWriter out=null;//页面打印器
	protected static String basePath="/WEB-INF";
	
	@Override
	public void init() throws ServletException {
		super.init();
		logger.debug("初始化Action:");
		logger.debug(getClass().getName());
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	/**
	 * 不适用业务Action
	 * 处理get请求
	 */
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)throws ServletException, IOException {
		this.request=req;
        this.response=resp;
		String isAsyn=req.getHeader("X-Requested-With");
		resp.setContentType("text/html;charset=UTF-8");
		if(null!=isAsyn&&"XMLHttpRequest".equals(isAsyn)){//异步请求处理
		     startAsync(new Async(){//在异步处理中返回数据全靠PrintWriter
				@Override
				public void run() {
					try{
					    Object obj=invokeMethod(req, resp);
					    if(null!=obj){
					    	printJSON(obj);
					    }
					    closePrint();
					    asc.complete();
					}catch(Exception e){
						e.printStackTrace();
						logger.error(e.getMessage(), e.getCause());
					}
				}
		     });	
		}else{//同步请求处理
			Object obj=invokeMethod(req, resp);
			if(null!=obj){//如果有返回值，则必须是指向页面的字符
				String page=String.valueOf(obj);
				if(page.startsWith("/")){
					response.sendRedirect(req.getServletContext().getContextPath()+page);
				}else{
					String path=getClass().getPackage().getName().replace("com.thang.web", "").replaceAll("\\.", "/")+"/"+getClass().getSimpleName().replaceAll("Action", "").toLowerCase()+"/";
					request.setAttribute("values", values);
					request.getRequestDispatcher(basePath+path+page).forward(req, resp);
				}
			}
			closePrint();	
		}
	}
	
	/**
	 * 不适用业务Action
	 * 处理post请求
	 */
	@Override
	protected void doPost(final HttpServletRequest req,final HttpServletResponse resp)throws ServletException, IOException {
		this.request=req;
        this.response=resp;
		String isAsyn=req.getHeader("X-Requested-With");
		if(null!=isAsyn&&"XMLHttpRequest".equals(isAsyn)){//异步请求处理
		     startAsync(new Async(){//在异步处理中返回数据全靠PrintWriter
				@Override
				public void run() {
					try{
						Object obj=invokeMethod(req, resp);
						if(null!=obj){
							printJSON(obj);
					    }
					    closePrint();
					    asc.complete();
					}catch(Exception e){
						e.printStackTrace();
						logger.error(e.getMessage(), e.getCause());
					}
				}
		     });	
		}else{//同步请求处理
			Object obj=invokeMethod(req, resp);
			if(null!=obj){//如果有返回值，则必须是指向页面的字符
				String page=String.valueOf(obj);
				if(page.startsWith("/")){
					response.sendRedirect(req.getServletContext().getContextPath()+page);
				}else{
					String path=getClass().getPackage().getName().replace("com.thang.web", "").replaceAll("\\.", "/")+"/"+getClass().getSimpleName();
					request.setAttribute("values", values);
					request.getRequestDispatcher(basePath+path+page).forward(req, resp);
				}
			}
			closePrint();	
		}
	}
	
	/**
	 * 不适用业务Action
	 * @param req
	 * @param resp
	 * @return
	 */
	protected Object invokeMethod(HttpServletRequest req, HttpServletResponse resp){
        try{
        	String uri=req.getRequestURI();
            String path=req.getServletContext().getContextPath()+req.getServletPath();
            
            //方法名 与 请求路径比对，如果两者相同则说明路径中无方法名，则请求无效
            if(uri.equals(path)){
            	logger.error("无效方法！");
            	return null;
            }
            
            String methodName=uri.replace(path, "").replaceAll("/", "");
            
            //方法名不能为空 且 不能为 init,destory,doGet,doPost
            if(null!=methodName&&!"".equals(methodName)&&!"init".equals(methodName)&&!"destroy".equals(methodName)&&!"doGet".equals(methodName)&&!"doPost".equals(methodName)){
            	Method method=this.getClass().getDeclaredMethod(methodName);
            	//方法不为空，且为public 且没有参数 且返回类型为空或字符串
            	if(null!=method&&1==method.getModifiers()&&0==method.getParameterTypes().length&&(method.getReturnType()==Void.TYPE||method.getReturnType()==String.class)){
                    this.values=new ActionValues(req);
                	return method.invoke(this);
                }else{
                	logger.error("未指定执行方法！");
                }
            }else{
            	logger.error("指定方法无效！");
            }
        }catch(Exception e){
        	logger.error("处理请求出错！");
        	logger.error(e.getMessage(), e.getCause());
        	print("{status:'error',result:'"+e.getMessage()+"'}");
		}
        return null;
	}
	
	
	/**
	 * 开启异步处理
	 * @param async
	 */
	protected void startAsync(Async async){
		try{
			new Thread(async.ready(request.startAsync(),values)).start();
		}catch(Exception e){
			logger.error(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * 得到上传文件的真实名称
	 * @param part
	 * @return
	 */
	protected String getFileName(Part part){
		String[] tes=part.getHeader("content-disposition").split(";");
		if(null!=tes&&tes.length>2){
			return StrUtils.trim(tes[2].trim().split("=")[1],"\"");
		}
		return null;
	}
	
	/**
	 * 打印字符串到页面
	 * @param content
	 */
	protected void print(String content){
	    openPrint();
	    out.write(content);
	    out.flush();
	}
	
	/**
	 * 打印JSON字符串到页面
	 * @param obj
	 */
	protected void printJSON(Object obj){
		openPrint();
		out.write(gson.toJson(obj));
		out.flush();
	}
	
	/**
	 * 不适用业务Action
	 * 开启打印器
	 */
	private void openPrint(){
		try{
			if(null==out){
			    out=response.getWriter();
			}
		}catch(Exception e){
			logger.error("开启输出流出错！");
			logger.error(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * 不适用业务Action
	 * 关闭打印器
	 */
	private void closePrint(){
		try{
			if(null!=out){
				out.close();
				out=null;
			}
		}catch(Exception e){
			logger.error("关闭输出流出错！");
			logger.error(e.getMessage(), e.getCause());
		}
	}

	
	@Override
	public void destroy() {
		super.destroy();
		logger.debug("销毁Action:");
		logger.debug(getClass().getName());
	}
	
}
