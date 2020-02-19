<%@ page import ="spms.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id = "member" scope = "session" class="spms.vo.Member"/>
 <%
// Member member = (Member)session.getAttribute("member");
%>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">
SPMS(Simple Project Management System)
 <%
if(member.getEmail() != null) //원래 getAttribute메소드 쓰는 위의 코드로 했을때도 이 조건문 없으면 밑에 .getName에서 에러 걸릴수  있음(Error.jsp)(세션없을때)
{
%>
<span style = "float:right;">
<%=member.getName()%>
<a style = "color:white" href = "<%=request.getContextPath()%>/auth/logout">로그아웃</a>
</span>
<%}%>
 </div>