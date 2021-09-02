<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%
	String [] places = request.getParameterValues("place");
%> --%>

<%
	String [] places = (String [])request.getAttribute("place");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도시 선택 결과</title>
</head>
<body>
	<h2>당신이 선택한 도시입니다.</h2>
	<hr>
	<span>선택한 도시는 : </span>
	<% 
		for(String place : places) {
	%>
		 <%= place %>	 
	<% 
		}
	%>
</body>
</html>