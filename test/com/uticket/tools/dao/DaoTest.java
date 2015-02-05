package com.uticket.tools.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thang.tools.dao.Dao;
import com.thang.tools.model.ActionValues;
import com.thang.tools.model.ResultValues;

public class DaoTest {

	private Dao dao=null;
	@Before
	public void setUp() throws Exception {
		dao=new Dao();
	}

	@After
	public void tearDown() throws Exception {
		dao=null;
	}
	
	//@Test
	public void forPageMysql(){
		ActionValues values=new ActionValues();
		values.openPage(1,5);
		values.descBy("sid");
		List<ResultValues> rs=dao.listSQL("select * from student", values);
		for(ResultValues r:rs){
			System.out.println(r);
		}
	}
	
	//@Test
	public void forPageOracle(){
		ActionValues values=new ActionValues();
		values.openPage(1,5);
		values.descBy("datasort");
		List<ResultValues> rs=dao.listSQL("select * from bmasysparam ", values);
		for(ResultValues r:rs){
			System.out.println(r);
		}
	}
	
	@Test
	public void forPageMssql(){
		ActionValues values=new ActionValues();
		values.openPage(1,5);
		values.descBy("id");
		List<ResultValues> rs=dao.listSQL("select * from boat ", values);
		for(ResultValues r:rs){
			System.out.println(r);
		}
	}

}
