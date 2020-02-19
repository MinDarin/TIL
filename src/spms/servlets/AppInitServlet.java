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
		System.out.println("AppInitServlet �غ�");
		super.init(config);	//�ʱ�ȭ ���Ŀ��� ServletConfig ����ϰ� �ʹٸ�...?
		//init �޼ҵ� �������̵��ϰ� service/doget dopost()ȣ��� ����Ŭ���� init�޼ҵ� ȣ�� �ʼ���µ�...
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
		Connection conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"),sc.getInitParameter("passwd"));
		sc.setAttribute("conn",conn);
		}catch(Throwable e)	//Throwable�� Exception���� ���� Ŭ������ (����ó���� �� �� �ִ� �ֻ��� Ŭ����)
		{
			throw new ServletException(e);	//.
		}		
	}
	public void destroy(){
		System.out.println("AppInitServlet ������");
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
