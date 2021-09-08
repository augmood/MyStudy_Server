<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Notice noticeOne = (Notice)request.getAttribute("noticeOne");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice 상세정보</title>
</head>
<body>
	<h2>제목 : <%= noticeOne.getNoticeSubject() %></h2>
	<h6>글번호 :<%= noticeOne.getNoticeNo() %> / 
		글쓴이 : <%= noticeOne.getWriterId() %> / 
		작성일 : <%= noticeOne.getRegDate() %></h6>
	<h3>글내용</h3>
		<%= noticeOne.getNoticeContents() %>
	<div>
	</div>
	<a href="/notice/modify?noticeNo=<%= noticeOne.getNoticeNo() %>">수정</a>
	<a href="/notice/list">목록</a>
	<!-- 쿼리스트림으로 여러개를 보낼 수도 있다. -->
	<a href="/notice/remove?noticeNo=<%= noticeOne.getNoticeNo() %>">삭제</a>
</body>
</html>