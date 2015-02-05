package com.uticket.web;

import javax.servlet.annotation.WebServlet;

import com.thang.entity.system.User;
import com.thang.tools.model.FMAction;

@WebServlet("/test_main/*")
public class FreemarkerTestAction extends FMAction {

	private static final long serialVersionUID = -5970034275298823075L;

	
	public String main(){
		values.add("title", "欢迎你！");
		values.add("user",new User());
		return "main.ftl";
	}
	
	public String system(){
		return "system/system.ftl";
	}
}
