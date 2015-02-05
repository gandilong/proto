package com.uticket.tools.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thang.tools.model.DBHandler;

public class DBHandlerTest {

	private DBHandler db=null;
	
	@Before
	public void setUp() throws Exception {
		db=new DBHandler();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Map<String,Object> obj=new HashMap<String,Object>();
		obj.put("id", 1);
		obj.put("name", "gandi");
		db.updateSQL("select * where sys_user_info where id=:id and name=:name", obj);
	}

}
