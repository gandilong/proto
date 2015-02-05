package com.uticket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thang.tools.model.Action;
import com.thang.tools.model.Async;
/**
 * 异步请求 事例
 * @author zyt
 *
 */
@WebServlet(name="asyn",urlPatterns="/asyn",asyncSupported=true)
public class MyAsyncAction extends Action{

	private static final long serialVersionUID = 1L;

	public void done(){
		startAsync(new Async(){
			@Override
			public void run() {
				System.out.println(asc);
				System.out.println(values);
			}
			
		});
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("do get=======");
         doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		out.println("servlet start:"+new Date());
		
		AsyncContext ac=req.startAsync();
		ac.setTimeout(8000);
		//可以 添加 监听
		ac.addListener(new AsyncListener() {
			
			@Override
			public void onTimeout(AsyncEvent ae) throws IOException {
				
			}
			
			@Override
			public void onStartAsync(AsyncEvent ae) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(AsyncEvent ae) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete(AsyncEvent ae) throws IOException {
				// TODO Auto-generated method stub
				ae.getAsyncContext().getResponse();
			}
		});
		new Thread(new Done(ac)).start();
		
		out.println("servlet end:"+new Date());
		
	}
	
	/**
	 * 处理业务 的线程类 可以向页面 返回数据
	 * @author zyt
	 *
	 */
	class Done implements Runnable{

		private AsyncContext ac=null;
		
		public Done(AsyncContext ac){
		    this.ac=ac;	
		}
		
		@Override
		public void run() {
			try{
				ServletResponse resp=ac.getResponse();
			    System.out.println("doing bussiness");
			    
			    PrintWriter out=resp.getWriter();
			    out.println("async:"+System.currentTimeMillis());
			    out.flush();
			    Thread.sleep(7000);//这里的时间不能超过  超时 时间
			    
			    out.println("ok I am done");
			    out.close();
			    ac.complete();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
}
