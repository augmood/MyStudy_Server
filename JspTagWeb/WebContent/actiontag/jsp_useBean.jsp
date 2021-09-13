<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp useBean</title>
</head>
<body>
	<jsp:useBean id="member" class="test.member.model.vo.Member" scope="page">
		<jsp:setProperty name="member" property="id" value="user11"></jsp:setProperty>
		<jsp:setProperty name="member" property="password" value="pass11"></jsp:setProperty>
		<jsp:setProperty name="member" property="name" value="문정원"></jsp:setProperty>	
	</jsp:useBean>
	
	ID : <jsp:getProperty property="id" name="member"></jsp:getProperty><br>
	PASSWORD :  <jsp:getProperty property="password" name="member"></jsp:getProperty><br>
	NAME :  <jsp:getProperty property="name" name="member"></jsp:getProperty><br>
</body>
</html>