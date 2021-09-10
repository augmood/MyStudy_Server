<%@page import="file.model.vo.FileData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<FileData> fList = (List<FileData>)request.getAttribute("fList"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File List</title>
</head>
<body>
	<h1>파일 목록</h1>
	<table border = "1">
	<tr>
		<th>파일이름</th>
		<th>파일사이즈</th>
		<th>업로더</th>
		<th>업로드시간</th>
		<th>다운로드</th>
		<th>삭제</th>
	</tr>
<%--	<% for(int i= 0; i < fList.size(); i++) { %>  --%>
		<% for(FileData data: fList) { %>
	<tr>
		<td><%= data.getFileName() %></td>
		<td><%= data.getFileSize() %></td>
		<td><%= data.getFileUser() %></td>
		<td><%= data.getUploadTime() %></td>
		<!-- <td><a href="/upload/<%=data.getFileName()%>" download><%=data.getFileName()%></a></td> -->
		<td>
		<form action="/file/down" method="post">
			<input type="hidden" name="file-path" value="<%= data.getFilePath() %>">
			<input type="submit" value="다운로드">
		</form>
		</td>
		<td>
 			<form action="/file/remove" method="post">
		<!-- 특정한 것을 지워야 하기 때문에 설정해 줌, 내 파일을 지워야 하기 때문에 파일의 이름과 내 이름이 필요함 -->
		<input type="hidden" name="file-name" value="<%= data.getFileName() %>">
		<input type="hidden" name="file-user" value="<%= data.getFileUser() %>">
		<input type="hidden" name="file-path" value="<%= data.getFilePath() %>">
		<input type="submit" value="삭제">
		</form>
		</td>
	</tr>
	<% } %>
	</table>
</body>
</html>