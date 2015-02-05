package com.uticket.web;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.thang.tools.model.Action;

@WebServlet(name="hello",urlPatterns={"/hello/*"},asyncSupported=true,initParams={@WebInitParam(name="ap",value="myfirstparam"),@WebInitParam(name="bp",value="mysecondparam")})
public class HelloWorldAction extends Action{

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
	}
	
	
	public void hello(){
		print("hello world");
		printJSON(values);
	}
	
	public void test(){
		print("I am test!");
		printJSON(values);
	}
	
}
