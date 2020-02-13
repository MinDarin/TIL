package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
//@WebServlet("/member/list")
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

/*
 * public class MemberListServlet extends GenericServlet{
 * 
 * @Override public void service(ServletRequest request, ServletResponse
 * response) throws ServletException, IOException { //1.����� jdbc�帮�ƹ� ���
 * Connection con = null; Statement stmt = null; ResultSet rs = null; try { //
 * DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
 * 
 * // ServletConfig config = this.getServletConfig(); //
 * Class.forName(config.getInitParameter("driver"));
 * 
 * // Class.forName(this.getInitParameter("driver"));
 * 
 * ServletContext ctx = this.getServletContext();
 * Class.forName(ctx.getInitParameter("driver"));
 * 
 * //2.����̹��� ����Ͽ� mysql������ ���� // con = DriverManager.getConnection(
 * "jdbc:mysql://localhost:3306/jdb?serverTimezone=UTC", "root",
 * "twinsangel1996^^"); con =
 * DriverManager.getConnection(this.getInitParameter("url"),
 * this.getInitParameter("username"), this.getInitParameter("passwd"));
 * 
 * con = DriverManager.getConnection(ctx.getInitParameter("url"),
 * ctx.getInitParameter("username"), ctx.getInitParameter("passwd"));
 * 
 * stmt = con.createStatement(); //4.SQL�� ������ ��ü�� ����Ͽ� ������ �����϶� rs = stmt.
 * executeQuery("select MNO,MNAME,EMAIL,CRE_DATE From MEMBERS order by MNO asc"
 * ); //5.�������� ������ �����͸� ����Ͽ� html�� ���� �� �������� ����϶�.
 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
 * response.getWriter();
 * out.println("<html><head><title>ȸ�����</title></head><body>");
 * out.println("<p><a href='add'>new coustomer</a></p>"); while(rs.next()) {
 * out.println( rs.getInt("MNO") + "," + "<a href='update?no=" +
 * rs.getInt("MNO") + "'>" + rs.getString("MNAME") + "</a>," +
 * rs.getString("EMAIL") + "," + rs.getDate("CRE_DATE") + "<br>" ); }
 * out.println("</body></html>"); } catch (Exception e) { throw new
 * ServletException(e); //��Ĺ ������ ���� } catch (SQLException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); }
 * catch(ClassNotFoundException ec){ throw new ServletException(ec); //��Ĺ ������ ����
 * }
 * 
 * finally { try{rs.close();}catch(Exception e ) {}
 * try{stmt.close();}catch(Exception e ) {} try{con.close();}catch(Exception e )
 * {} } } }
 * 
 */
@SuppressWarnings("serial")
public class MemberListServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
/*			Class.forName(sc.getInitParameter("driver"));
		conn = 	DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"),sc.getInitParameter("passwd"));*/
		conn = (Connection)sc.getAttribute("conn");	
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select MNO,MNAME,EMAIL,CRE_DATE From MEMBERS order by MNO asc");
//		response.setContentType("text/html; charset=UTF-8");
//		ArrayList<Member> members = new ArrayList<Member>();
		ArrayList<Member> members = new ArrayList();

		while(rs.next()) {
			members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE")));
			}
			request.setAttribute("members",members);
			RequestDispatcher rd= request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request,response);
		}
		catch (Exception e) {
			//e.printStackTrace();
			//throw new ServletException();
			request.setAttribute("error",e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
		finally
		{
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}		conn�� AppInitServlet���� �����ѰŶ� �ٸ��ֵ鵵 �� �� �յ��� ���⼭ �Ⱦ���
		}
		
	}
}
