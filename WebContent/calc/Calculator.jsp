<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String v1 = "1";
String v2 = "2";
String result = "";
//String[] selected = new String[4];
String[] selected = {"","","",""};

if(request.getParameter("v1")!=null)
{
	v1 = request.getParameter("v1");
	v2 = request.getParameter("v2");
	String op = request.getParameter("op");
	
	result = calculate(Integer.parseInt(v1),Integer.parseInt(v2),op);
	
	if("+".equals(op)) selected[0] = "selected";
	else if("-".equals(op)) selected[1] = "selected";
	else if("*".equals(op)) selected[2] = "selected";
	else if("/".equals(op)) selected[3] = "selected";
//	System.out.println(Integer.parseInt(v1)>Integer.parseInt(v2)?v1:v2); //되네
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv ="Content-Type" content="text/html; charset=UTF-8" >
<title>Insert title here</title>
<title>계산기</title>
</head>
<body>
<h2>계산기</h2>
 <form action = "Calculator.jsp" method = "get">
<input type ="text" name="v1" size = "4" value ="<%=v1%>">

 	<select name ="op">
	<option value="+" <%=selected[0]%>>+</option>
	<option value="-" <%=selected[1]%>>-</option>
	<option value="*" <%=selected[2]%>>*</option>
	<option value="/" <%=selected[3]%>>/</option>	
	</select>
<input type ="text" name="v2" size="4" value ="<%=v2%>">
 <input type = "submit" value ="=" >
<input type ="text" name="result" size="4" value ="<%=result%>">
 </form>
<input type ="text" name="hi" size="4" value ="<%=Integer.parseInt(v1)>Integer.parseInt(v2)?v1:v2%>"> <!-- 이거 되려면 v1 v2초기값을 저래 해줘야함.-->
</body>
</html>

<%!
 private String calculate(int a, int b, String op)
 {
	int r = 0;
	if("+".equals(op))
		r = a+b;
	else if("-".equals(op))
		r = a-b;
	else if("*".equals(op))
		r = a*b;
	else if("/".equals(op))
		r = a/b;
	return Integer.toString(r);
 }
%>