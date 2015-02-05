package com.uticket.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thang.tools.model.Action;

@WebServlet("/test")
public class TestAction extends Action {

	private static final long serialVersionUID = 480579487192141329L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("test action");
		//用resp.sendRedirect();访问不到WEB-INF下面的页面文件
		req.getRequestDispatcher("/WEB-INF/main.html").forward(req, resp);
	}
	
}
