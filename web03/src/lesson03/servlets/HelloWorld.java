package lesson03.servlets;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet{
	ServletConfig config;  
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
		// TODO Auto-generated method stub
		this.config = config;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig");

		// TODO Auto-generated method stub
		return config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo");
		// TODO Auto-generated method stub
		return "HelloWorld Servlet";
	}


}
