package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class AppInitServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config)throws ServletException
	{
		System.out.println("AppInitServlet 준비");
		super.init(config);	//초기화 이후에도 ServletConfig 사용하고 싶다면...?
		//init 메소드 오버라이딩하고 service/doget dopost()호출시 상위클래스 init메소드 호출 필수라는데...
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
		Connection conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"),sc.getInitParameter("passwd"));
		sc.setAttribute("conn",conn);
		}catch(Throwable e)	//Throwable이 Exception보다 상위 클래스임 (예외처리를 할 수 있는 최상위 클래스)
		{
			throw new ServletException(e);	//.
		}		
	}
	public void destroy(){
		System.out.println("AppInitServlet 마무리");
		super.destroy();
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		try {
			if(conn != null && conn.isClosed() == false) conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
