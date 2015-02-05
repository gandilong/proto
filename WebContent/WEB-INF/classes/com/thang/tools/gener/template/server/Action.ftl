[#ftl]
package com.thang.web.${pkgName};

import javax.servlet.annotation.WebServlet;
import com.thang.tools.model.Action;

@WebServlet(value="/${url}/*",asyncSupported=true)
public class ${entityName?cap_first}Action extends Action{

    private ${entityName?cap_first}Manager ${entityName}Manager=null;
	private static final long serialVersionUID = 7925187887163392582L;
    
	@Override
	public void init() throws ServletException {
		${entityName}Manager=new ${entityName?cap_first}Manager();
		super.init();
	}
	
	public String page()throws Exception{
		if(values.isNotEmpty("to")){
			return "${url}/"+values.getStr("to");
		}
		return null;
	}
	
	public void get()throws Exception{
    	ResultValues r=${entityName}Manager.get(User.class, values);
		printJSON(r);
	}
	
	public void list()throws Exception{
		List<ResultValues> rs=${entityName}Manager.list(User.class, values);
		printJSON(rs);
	}
	
	public void insertOrUpdate()throws Exception{
		${entityName}Manager.insertOrUpdate(User.class, values);
		print(0);
	}
	
	public void delete()throws Exception{
		${entityName}Manager.delete(User.class, values);
		print(0);
	}
    

}