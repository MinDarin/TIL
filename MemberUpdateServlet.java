package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.Location;

@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
/*		Class.forName(this.getInitParameter("driver"));
		conn = DriverManager.getConnection(this.getInitParameter("url"),
											this.getInitParameter("username"),
											this.getInitParameter("password"));
*/
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));

			conn = DriverManager.getConnection(ctx.getInitParameter("url"),
					ctx.getInitParameter("username"),
					ctx.getInitParameter("passwd"));

		stmt = conn.createStatement();
		rs = stmt.executeQuery("Select mno,email,mname,cre_date from MEMBERS where MNO = "+ request.getParameter("no"));
		rs.next();
		
		//response.setContentType("text/html; charset=UTF-8");
/*		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>�?�?</title></head>"
				+ "<body>"
				+ "<h1></h1>"
				+ "<form action='update' method='post'>"
				+"번호: <input type='text' name='no' value='" 
				+ request.getParameter("no") + "' readonly><br>"
				+"�씠?���?: <input type='text' name='name'"
				+" value='" + rs.getString("MNAME")  + "'><br>"
				+"�씠硫붿?��: <input type='text' name='email'" 
				+" value='" + rs.getString("EMAIL")  + "'><br>"
				+"媛��엯�씪: " + rs.getDate("CRE_DATE") + "<br>"
				+"<input type='submit' value='���옣'>"
				+"<input type='button' value='?��?��?��'" 
				+ " onclick='location.href=\"list\"'>"
				+ "<input type='submit' value = >"
				+ "</form>"
				+ "</body>"
				+ "</html>");*/
/*		PrintWriter out = response.getWriter();
		out.println("<html><head><title>��������</title></head>");
		out.println("<body><h1>������</h1>");
		out.println("<form action='update ' method='post'>");
		out.println("��ȣ: <input type='text' name='no' value='" +
			request.getParameter("no") + "' readonly><br>");
		out.println("�̸�: <input type='text' name='name'" +
			" value='" + rs.getString("MNAME")  + "'><br>");
		out.println("�̸���: <input type='text' name='email'" +
			" value='" + rs.getString("EMAIL")  + "'><br>");
		out.println("��������: " + rs.getDate("CRE_DATE") + "<br>");
		out.println("<input type='submit' value='����'>");
		out.println("<input type='button' value='����'" + "onclick = 'location.href=\"delete?no="+request.getParameter("no") +"\"'>");
		out.println("<input type='button' value='�ڷΰ���'" + 
			"onclick='location.href=\"list\"'>");
		out.println("</form>");
		out.println("</body></html>");*/
		RequestDispatcher rd = request.getRequestDispatcher("/update.jsp");
		request.setAttribute("rs", rs);
		rd.forward(request, response);
//		System.out.println("������� ������ ���ư���?");

	} catch (Exception e) {
	//	throw new ServletException(e);
		RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
		rd.forward(request, response);		
	} finally {
//		try {if (rs != null) rs.close();} catch(Exception e) {}
		try {if (stmt != null) stmt.close();} catch(Exception e) {}
//		try {if (conn != null) conn.close();} catch(Exception e) {}
	}
	}
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
/*				Class.forName(this.getInitParameter("driver"));
				conn = DriverManager.getConnection(
						this.getInitParameter("url"),
						this.getInitParameter("username"),
						this.getInitParameter("passwd")); 
				*/
				ServletContext ctx = this.getServletContext();
				Class.forName(ctx.getInitParameter("driver"));

				conn = DriverManager.getConnection(ctx.getInitParameter("url"),
						ctx.getInitParameter("username"),
						ctx.getInitParameter("passwd"));

				stmt = conn.prepareStatement("Update MEMBERS set EMAIL=?,Mname=?,MOD_DATE=NOW() where MNO = ?");
				stmt.setString(1,request.getParameter("email"));
				stmt.setString(2, request.getParameter("name"));
				stmt.setInt(3, Integer.parseInt( request.getParameter("no")));

				stmt.executeUpdate();
				response.sendRedirect("list");
			} catch (Exception e) {
				throw new ServletException(e);
				
			} finally {
				try {if (stmt != null) stmt.close();} catch(Exception e) {}
				try {if (conn != null) conn.close();} catch(Exception e) {}
			}
		}
}
