<%@page import="member.model.vo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 회원 정보</title>
</head>
<body>
	<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>성별</th>
		<th>나이</th>
		<th>이메일</th>
		<th>연락처</th>
		<th>주소</th>
		<th>취미</th>
		<th>가입날짜</th>
	</tr>
	<c:forEach items="${requestScope.sList}" var="student" varStatus="index">
	<tr>
		<td>
			<a href="member/myinfo?studentId=${student.studentId}">
				${student.studentId}
			</a>
		</td>
		<td>${student.studentId}</td>
		<td>${student.studentName}</td>
		<td>${student.studentGender}</td>
		<td>${student.studentAge}</td>
		<td>${student.studentEmail}</td>
		<td>${student.studentPhone}</td>
		<td>${student.studentAddress}</td>
		<td>${student.studentHobby}</td>
		<td>${student.enrollDate}</td>
	</tr>
		</c:forEach>
	</table>
</body>
</html>