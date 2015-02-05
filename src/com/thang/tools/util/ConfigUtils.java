package com.thang.tools.util;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.thang.tools.dao.Dao;

public class ConfigUtils {

	private static DataSource dataSource;//默认数据源
	private static Properties config=null;
	private static String[] configFiles={"system.properties","dbconfig.properties"};//需要加载到内存听配置信息
	
	private static Logger logger=Logger.getLogger(Dao.class);
	
	static{
		logger.debug("初始化配置...");
		initConfig();
	}
	
	/**
	 * 初始化配置
	 */
	public static void initConfig(){
		try{
			config=new Properties();
		    for(String file:configFiles){
			    config.load(ConfigUtils.class.getResourceAsStream("/"+file));
		    }
		}catch(Exception e){
			e.printStackTrace();
			logger.error("初始化配置失败！");
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 得到classes下面的属性文件
	 * @param fileName
	 * @return
	 */
	public static Properties getConfigFile(String fileName){
		Properties pro=new Properties();
		try {
			pro.load(ConfigUtils.class.getResourceAsStream("/"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("获得配置文件失败！");
			logger.error(e.getMessage());
		}
		return pro;
	}
	
	/**
	 * 得到配置中的值
	 * @param key
	 * @return
	 */
	public static String getConfig(String key){
		return config.getProperty(key);
	}
	
	/**
	 * 得到默认数据源
	 * @return
	 */
	public static DataSource getDataSource(){
		if(null==dataSource){
			Properties pro=getConfigFile("dbconfig.properties");
			if(null!=pro){
				try{
				    dataSource=BasicDataSourceFactory.createDataSource(pro);
				}catch(Exception e){
					e.printStackTrace();
					logger.error("获取默认数据源失败！");
					logger.error(e.getMessage());
				}
			}
		}
		return dataSource;
	}
	
	/**
	 * 根据指定配置文件得到数据源
	 * @param fileName
	 * @return
	 */
	public static DataSource getDataSource(String fileName){
		Properties pro=getConfigFile(fileName);
		DataSource ds=null;
		if(null!=pro){
			try{
			    ds=BasicDataSourceFactory.createDataSource(pro);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("获取指定数据源失败！");
				logger.error(e.getMessage());
			}
		}
		return ds;
	
	}
	
}
