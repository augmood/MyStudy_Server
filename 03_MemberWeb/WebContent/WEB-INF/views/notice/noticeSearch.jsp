<%@page import="notice.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<Notice> nList = (List<Notice>)request.getAttribute("nList");
 	String pageNavi  = (String)request.getAttribute("pageNavi");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항</h1>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>글쓴이</th>
			<th>작성일</th>
		</tr>
		<% for(Notice nOne : nList) { %>
		<tr>
			<td><%= nOne.getNoticeNo() %></td>
			<td><a href="/notice/detail?noticeNo=<%= nOne.getNoticeNo() %>"><%= nOne.getNoticeSubject() %></a></td>
			<td><%= nOne.getWriterId() %></td>
			<td><%= nOne.getRegDate() %></td>
		</tr>
		<% } %>
		<!-- 검색창, 글쓰기 버튼, 네비게이션 바 -->
		<tr>
			<td colspan="3" align="center">
				<form action="/notice/search" method="get">
					<input type="text" name="searchKeyword">
					<input type="submit" value="검색">
				</form>
			</td>
			<td align="center">
				<form action="/notice/write" method="get">
					<input type="submit" value="글쓰기">
				</form>
			</td>
		</tr>
		<tr>
			<!-- 페이징 처리 -->
			<td colspan="4" align="center">
					 <%= pageNavi %>  
<!-- 				<a href="#">[이전]</a> -->
<!-- 				<a href="/notice/list?currentPage=1">1</a>  -->
<!-- 				<a href="/notice/list?currentPage=2">2</a> -->
<!-- 				<a href="/notice/list?currentPage=3">3</a> -->
<!-- 				<a href="/notice/list?currentPage=4">4</a> -->
<!-- 				<a href="/notice/list?cureentPage=5">5</a> -->
<!-- 				<a href="#">[다음]</a> -->
			</td>
		</tr>
	</table>
</body>
</html>