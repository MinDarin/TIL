<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="t1" value ="test" scope="request"></c:set>
</head>
<body>
${t1}<br>
<%int t = 1; %>
<c:set var="t" value ="2" scope="request"></c:set>
${t} <br>
<c:set var="t" value ="10" scope="request"></c:set>
${t} <br>
<%!
	public static class MyMember{
	int no = 0;
	String name = "default";

	public int getNo()	{
		return no;
	}
	public void setNo(int n)	{
		this.no = n;
	}
	public String getName()	{
		return name;
	}
	public void setName(String s)	{
		this.name = s;
	}
}
%>
<%
MyMember member = new MyMember();
member.setNo(1);
member.setName("abc");
pageContext.setAttribute("member", member);
%>
<c:set target="${member}" property="no" value="2"/>
<c:set target="<%=member%>" property="name" value="kim"/>
${member.no} <br>
${member.name} <br>
<c:remove var="t"/>
${t}<br>
<% String lan = "eng";
pageContext.setAttribute("lan",lan);
%>
<c:choose>
	<c:when test="${lan == 'kor'}">안녕</c:when>
	<c:when test="${lan == 'eng'}">Hi</c:when>
	<c:otherwise>다른 값...</c:otherwise>	
</c:choose>
<br><br>
<% 
	ArrayList<String> nameList = new ArrayList<String>();
	nameList.add("ab");
	nameList.add("cd");
	nameList.add("ef");
	nameList.add("gh");	
	pageContext.setAttribute("nameList",nameList);

	pageContext.setAttribute("nameList2","가나,다라,마바");	
%>
<c:forEach var="name" items="${nameList}">
	<c:out value="${name}"/><br>
</c:forEach>
<br>
<c:forEach var="name" items="${nameList2}">
	<c:out value="${name}"/><br>
</c:forEach>

<c:forEach var="no" begin="1" end="6">
	<a href="link${no}.jsp">링크 ${no}<br></a>
</c:forEach>
<%
	String tokens = "v1=2&v2=3&op=+";
	pageContext.setAttribute("tokens",tokens); 
%>
<c:forTokens var = "item" items="${tokens}" delims="&">
	<c:out value="${item}"/><br>		
</c:forTokens>

<c:url var="calcURL" value="http://localhost:9990/web03/calc">
	<c:param name="v1" value="2"/>
	<c:param name="v2" value="3"/>
	<c:param name="op" value="+"/>
</c:url>
<a href="${calcURL}">계산기</a>

<% Date cur_date = new Date(); 
	pageContext.setAttribute("cur_date", cur_date);
%>
<br><fmt:formatDate value="${cur_date}" pattern="yy/MM/dd"/>

</body>
</html>