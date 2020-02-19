package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
//@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/Add.jsp");
		rd.include(request,response);
		/*		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>ȸ���߰�</title></head>");
		out.println("<body><h1>�߰��߰�</h1>");
		out.println("<form action='add' method='post'>");
		out.println("�̸�: <input type='text' name='name'><br>");
		out.println("�̸���: <input type='text' name='email'><br>");
		out.println("���: <input type='password' name='password'><br>");
		out.println("<input type='submit' value='추�?'>");
		out.println("<input type='reset' value='�? ?��'>");
		out.println("</form>");
		out.println("</body></html>");	*/
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
//		request.setCharacterEncoding("UTF-8");

		try {
//			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdb?serverTimezone=UTC", "root", "twinsangel1996^^");

/*			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"),
					this.getInitParameter("username"),
					this.getInitParameter("passwd"));*/

			ServletContext ctx = this.getServletContext();
/*
			Class.forName(ctx.getInitParameter("driver"));

			conn = DriverManager.getConnection(ctx.getInitParameter("url"),
					ctx.getInitParameter("username"),
					ctx.getInitParameter("passwd"));
*/
			conn = (Connection)ctx.getAttribute("conn");
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
					+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
				System.out.println(request.getParameter("name"));
			stmt.executeUpdate();
			
//			 response.sendRedirect("list");
			//?���? 밑에�? ?��?�� ?��?��질거?��0
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>�߰��Ϸ�</title>"
					+ "<meta http-equiv = 'Refresh' content='1;url=list'>"
					+ "</head>");
			out.println("<body>");
			out.println("<p>c�߰��Ǿ����ϴ�.!</p>");
			out.println("</body></html>");
		//	response.setHeader("Refresh", "1;url=list");
		//	response.addHeader("Refresh", "1;url=list");
		} catch (Exception e) {
			//throw new ServletException(e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		 response.sendRedirect("list");  //만약 마�?막에 추�??���? ?���? 찍고 리다?��?��?�� ?��?��? ->?��?�� 그냥 리다?��?��?��?��
	
		}	
}
