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
		//1.사용할 jdbc드리아버 등록
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			throw new ServletException(e); //톰캣 서버에 던짐
		}
		try {
			
			
		//2.드라이버를 사용하여 mysql서버와 연결
			con = DriverManager.getConnection("jdbc:mysql://localhoast/JDB", "root", "twinsangel1996^^");
			stmt = con.createStatement();
		//4.SQL을 던지는 객체를 사용하여 서버에 질의하라
			rs = stmt.executeQuery("select MNO,MNAME,EMAIL,CRE_DATE From MEMBERS order by MNO asc");
		//5.서버에서 가져온 데이터를 사용하여 html을 만들어서 웹 브라우저로 출력하라.
		response.setContentType("Text/html charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원목록</title></head><body>");
		while(rs.next())
		{
			out.println(rs.getInt("MNO")+","+rs.getString("MNAME")+","+rs.getString("EMAIL")+","+rs.getString("CRE_DATE")+"<br>"); //column index는 1부터 시작
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
