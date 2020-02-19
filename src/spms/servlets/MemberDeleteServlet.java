package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		request.setCharacterEncoding("UTF-8");
		try {
		ServletContext sc = this.getServletContext();
		Class.forName(sc.getInitParameter("driver"));
		conn = DriverManager.getConnection(
				sc.getInitParameter("url"),
				sc.getInitParameter("username"),
				sc.getInitParameter("passwd")); 
		stmt =  conn.prepareStatement("delete from MEMBERS where mno = ?");
		stmt.setString(1,request.getParameter("no"));
		System.out.println(request.getParameter("no"));
		stmt.executeUpdate();
		response.sendRedirect("list");		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request,response);
			//			e.printStackTrace();
		}

	}
}
