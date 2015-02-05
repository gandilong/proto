package com.thang.tools.listener;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.thang.tools.util.ConfigUtils;
import com.thang.tools.util.DirUtils;
import com.thang.tools.util.StrUtils;

/**
 * 启动配置
 * @author zyt
 * 2014年10月13日 下午5:23:45
 */
@WebListener
public class Bootstrap implements ServletContextListener {

	private static Logger logger=Logger.getLogger(Bootstrap.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("系统关闭...");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();
		configureLogPath(sc);
		
		String root=Bootstrap.class.getResource("/").getFile();
		File actionDir=new File(root+ConfigUtils.getConfig("action"));
		List<String> actions=new ArrayList<String>();
		loadActions(actionDir,actions,root);
		
		for(String action:actions){
			String[] as=action.replaceFirst(ConfigUtils.getConfig("action").replaceAll("/", "."), "").split("\\.");
			ServletRegistration.Dynamic dyna=sc.addServlet(as[as.length-1].toLowerCase(), action);
			dyna.addMapping("/"+StrUtils.join(as, "/").replaceAll("Action", "").toLowerCase()+"/*");
			dyna.setAsyncSupported(true);
			logger.debug("加载Action类："+action);
			logger.debug("映射路径："+"/"+StrUtils.join(as, "/").replaceAll("Action", "").toLowerCase()+"/*");
		}
	}
	
	/**
	 * 配置系统日志
	 * @param sc
	 */
	private void configureLogPath(ServletContext sc){
		//设置日志输出文件地址
		String contextPath=sc.getRealPath("/");
		contextPath=Bootstrap.class.getResource("/").getFile();
		String logPath=DirUtils.Root()+"/log/";
		Properties props=new Properties();
		try{
		    FileInputStream fin=new FileInputStream(contextPath+"log4j.properties");
			props.load(fin);
			fin.close();
			props.setProperty("log4j.appender.txt.File", logPath+props.getProperty("log4j.appender.txt.File"));
			PropertyConfigurator.configure(props);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询并加载所有action类
	 */
	private void loadActions(File dir,List<String> actions,String root){
		if(dir.isDirectory()){
			File[] files=dir.listFiles();
			for(File file:files){
				if(file.isDirectory()){
					loadActions(file,actions,root);
				}else{
					String path=file.getAbsolutePath();
					String action=path.substring(path.indexOf("classes")+8,path.length()-6).replaceAll("/", ".").replaceAll("\\\\", ".");
					if(action.endsWith("Action")){
						actions.add(action);
					}
				}
			}
		}else{
			String path=dir.getAbsolutePath();
			String action=path.substring(path.indexOf("classes")+8,path.length()-6).replaceAll("/", ".").replaceAll("\\\\", ".");
			if(action.endsWith("Action")){
				actions.add(action);
			}
		}
	}

}
