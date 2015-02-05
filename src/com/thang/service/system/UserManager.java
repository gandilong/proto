package com.thang.service.system;

import com.thang.entity.system.User;
import com.thang.tools.dao.Service;
import com.thang.tools.model.ActionValues;

public class UserManager extends Service{

	public void test(ActionValues values){
		get(User.class, values);
	}
}
