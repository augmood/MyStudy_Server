<%@page import="model.model.vo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<Student> sList = (List<Student>)request.getAttribute("sList");
%>
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
	<% for(int i = 0; i<sList.size();) { %>
	<tr>
		<td>
			<a href="member/myinfo?studentId=<%= sList.get(i).getStudentId()%>">
				<%=sList.get(i).getStudentId() %>
			</a>
		</td>
		<td><%= sList.get(i).getStudentId() %></td>
		<td><%= sList.get(i).getStudentName() %></td>
		<td><%= sList.get(i).getStudentGender() %></td>
		<td><%= sList.get(i).getStudentAge() %></td>
		<td><%= sList.get(i).getStudentEmail() %></td>
		<td><%= sList.get(i).getStudentPhone() %></td>
		<td><%= sList.get(i).getStudentAddress() %></td>
		<td><%= sList.get(i).getStudentHobby() %></td>
		<td><%= sList.get(i).getEnrollDate() %></td>
	</tr>
		<% } %>
	<tr>
		<td>kim1234</td>
		<td>김수지</td>
		<td>F</td>
		<td>35</td>
		<td>kim1234@korea.com</td>
		<td>01023456678</td>
		<td>경기도 안산시</td>
		<td>배구</td>
		<td>2021-09-04</td>
	</tr>

	</table>
</body>
</html>