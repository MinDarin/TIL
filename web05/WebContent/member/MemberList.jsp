<%@ page import = "spms.vo.Member"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>
</head>
<body>
<!--<jsp:include page="/Header.jsp"/>-->
<%
	RequestDispatcher rd = request.getRequestDispatcher("/Header.jsp");
	rd.include(request,response);
%>
<h1>회원 목록</h1>
<p><a href = 'add'>신규회원</a></p>
<%
ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
for(Member member : members)
{
%>
<%= member.getNo() %>,
<a href = "update?no=<%= member.getNo() %>"><%= member.getName() %></a>, <!-- jspwriter의 객체인 out객체는 쌓아두었다가 한꺼번에 출력하기에 html 태그안 속성 값으로 jsp 표현식이 가능한듯? -->
<%= member.getEmail() %>,
<%= member.getCreatedDate() %>
<a href = "delete?no=<%= member.getNo() %>">[삭제]</a><br>

<% }%>
</body>
</html>