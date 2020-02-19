package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.vo.Member;



@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet{
	@Override()
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(request, response);
	}
	@Override()
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		ServletContext sc = request.getServletContext();
		Connection conn =  null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = (Connection)sc.getAttribute("conn");
		stmt = conn.prepareStatement("Select MNAME, EMAIL from members where EMAIL = ? and pwd=?");		
		stmt.setString(1, request.getParameter("email"));
		stmt.setString(2, request.getParameter("password"));
		rs = stmt.executeQuery();
		if(rs.next())
		{
			Member member = new Member().setEmail(rs.getString("Email")).setName(rs.getString("MNAME"));
			HttpSession session = request.getSession();
			session.setAttribute("member",member);
			response.sendRedirect("../member/list");
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
			rd.forward(request, response);
		}
		}catch(Exception e)
		{
			throw new ServletException(e);
		}finally
		{
				if(rs!=null) { try {rs.close();}catch(Exception e) {}}
				if(stmt!=null) { try {stmt.close();}catch(Exception e) {}}				
		}
	}		
}
