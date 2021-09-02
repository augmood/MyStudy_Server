<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <% /* 입력 */
/*  	int firstNum = Integer.parseInt(request.getParameter("first_num"));
 	int secondNum = Integer.parseInt(request.getParameter("second_num"));
 	char operator = request.getParameter("operator").charAt(0); */
 	int firstNum = (int)request.getAttribute("firstNum"); 
 	int secondNum = (int)request.getAttribute("secondNum");
 	char operator = (char)request.getAttribute("operator");
 	int result = (int)request.getAttribute("result");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
<style>
	span {
		background : salmon;
	}
</style>
</head>
<body>
	<!--  jsp : Java Server Page -->
	
	<!-- html + servlet => JSP ,출력 MVC MODEL 1 방식 
	 
	-->
	
<%-- 	<%
	switch(operator) {
	case '+' :
	%> --%>
		<span style = "color : blue;">
		<%= firstNum %> <%= operator %> <%= secondNum %> =  <%= result %></span>
<%-- 	<% 	
		break;
	case '-' :
	%>
		<span style = "color : blue;">
		<%= firstNum %> <%= operator %> <%= secondNum %> =  <%= result %></span>
	<%	
		break;
	case '*' :
	%>
		 	<span style = "color : blue;">
		<%= firstNum %> <%= operator %> <%= secondNum %> =   <%= result %></span>
	<%		
		break;
	case '/' :
	%>
		<span style = "color : blue;">
		<%= firstNum %> <%= operator %> <%= secondNum %> =   <%= result %></span>
	<%
		break;
	}
	%> --%>
</body>
</html>