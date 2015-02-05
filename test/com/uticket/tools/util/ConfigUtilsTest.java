package com.uticket.tools.util;

import javax.sql.DataSource;

import com.thang.tools.util.ConfigUtils;

public class ConfigUtilsTest {

	public static void main(String[] args) {
		//测试得到配置文件的路径
		System.out.println(ConfigUtils.class.getResource("/dbconfig.properties").getPath());
		DataSource ds=ConfigUtils.getDataSource();
		System.out.println(ds);
	}

}
