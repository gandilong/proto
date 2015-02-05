package com.thang.web.system;

import java.util.List;

import javax.servlet.ServletException;

import com.thang.entity.system.User;
import com.thang.service.system.UserManager;
import com.thang.tools.model.Action;
import com.thang.tools.model.ResultValues;

public class UserAction extends Action{

	private UserManager userManager=null;
	private static final long serialVersionUID = 7925187887163392582L;
    
	@Override
	public void init() throws ServletException {
		userManager=new UserManager();
		super.init();
	}
	
	public String page()throws Exception{
		if(values.isNotEmpty("to")){
			return values.getStr("to")+".jsp";
		}
		return null;
	}
	
    public void get()throws Exception{
    	ResultValues r=userManager.get(User.class, values);
		printJSON(r);
	}
	
	public void list()throws Exception{
		List<ResultValues> rs=userManager.list(User.class, values);
		printJSON(rs);
	}
	
	public void insertOrUpdate()throws Exception{
		userManager.insertOrUpdate(User.class, values);
	}
	
	public void delete()throws Exception{
		userManager.delete(User.class, values);
	}
	
}
