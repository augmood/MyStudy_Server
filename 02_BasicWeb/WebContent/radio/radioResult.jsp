<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	String gender = request.getParameter("gender");
	String chkEmail = request.getParameter("chk-email");
%> --%>
<%
	String gender = (String)request.getAttribute("gender12");
	String chkEmail = (String)request.getAttribute("chk-email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>radioResult </title>
</head>
<body>
	성별 : <%= gender %> <br> 
	메일 수신여부 : <%=  chkEmail %>

<%-- 	<% 
		out.println("성별 : "+ gender);
		out.println("메일 수신여부 : "+ chkEmail);
	%> --%>
</body>
</html>