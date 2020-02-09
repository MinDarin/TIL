package smps.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("member/list")
public class MemberListServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		//1.����� jdbc�帮�ƹ� ���
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			throw new ServletException(e); //��Ĺ ������ ����
		}
		try {
			
			
		//2.����̹��� ����Ͽ� mysql������ ����
			con = DriverManager.getConnection("jdbc:mysql://localhoast/JDB", "root", "twinsangel1996^^");
			stmt = con.createStatement();
		//4.SQL�� ������ ��ü�� ����Ͽ� ������ �����϶�
			rs = stmt.executeQuery("select MNO,MNAME,EMAIL,CRE_DATE From MEMBERS order by MNO asc");
		//5.�������� ������ �����͸� ����Ͽ� html�� ���� �� �������� ����϶�.
		response.setContentType("Text/html charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>ȸ�����</title></head><body>");
		while(rs.next())
		{
			out.println(rs.getInt("MNO")+","+rs.getString("MNAME")+","+rs.getString("EMAIL")+","+rs.getString("CRE_DATE")+"<br>"); //column index�� 1���� ����
		}
		out.println("</body></html>");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try{rs.close();}catch(Exception e ) {}
			try{stmt.close();}catch(Exception e ) {}
			try{con.close();}catch(Exception e ) {}
		}
	}
}
