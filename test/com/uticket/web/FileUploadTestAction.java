package com.uticket.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.thang.tools.model.Action;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadTestAction extends Action{

	private static final long serialVersionUID = 1L;

	public FileUploadTestAction() {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
          Part part=req.getPart("fname");
          
          System.out.println(part.getName());//表单域的名字fname
          String value=part.getHeader("content-disposition");//上传文件的 真正名字
          System.out.println(value);
          System.out.println(getFileName(part));
          
          
          InputStream in=part.getInputStream();
          
          
          
          StringBuilder sber=new StringBuilder();
          int k=0;
          byte[] bs=new byte[1024];
          
          while(-1!=k){
        	  k=in.read(bs);
        	  if(-1!=k){
        		  sber.append(new String(bs,0,k));  
        	  }
          }
          System.out.println(sber.toString());
	}
	
}
