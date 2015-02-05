package com.thang.web;

import com.thang.tools.model.Action;

public class IndexAction extends Action {

	private static final long serialVersionUID = -7928300142955305350L;

	public String index(){
		return "index.html";
	}
	
	public String welcome(){ 
		return "/index.html";
	}
	
	public String login(){
		return "login.jsp";
	}
	
}
