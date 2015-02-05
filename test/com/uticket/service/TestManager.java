package com.uticket.service;

import java.sql.Connection;

import com.thang.tools.dao.Service;
import com.thang.tools.model.ActionValues;

public class TestManager extends Service {

	public void updatePassword(){
		try{
			
		    Connection conn=getConnection();
		    conn.setAutoCommit(false);
		    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		    
		    dao.updateXSQL(conn, "system.user.getUser", new ActionValues());
		    dao.updateSQL(conn, "update sys_user_info set uname='aaa'", new ActionValues());
		    
		    conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
