<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>변경</title>
</head>
<body>
	<% ResultSet rs = (ResultSet)request.getAttribute("rs");%>
	<h1>상세정보</h1>
		<form action='update' method='post'>
		번호: <input type='text' name='no' value='<%=request.getParameter("no")%>' readonly><br>
		이름: <input type='text' name='name'  value='<%=rs.getString("MNAME")%>'><br>
		이메일: <input type='text' name='email' value='<%=rs.getString("EMAIL")%>'><br>
		갱신일자: <%=rs.getDate("CRE_DATE")%> <br>
		<input type='submit' value='저장'>
		<input type='button' value='삭제' onclick = 'location.href="\delete?no=<%=request.getParameter("no")%>\"'>
		<input type='button' value='뒤로가기' onclick='location.href="\list\"'>
		
		</form>
</body>
</html>