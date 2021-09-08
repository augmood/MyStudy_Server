<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  	Notice notice = (Notice)request.getAttribute("notice");
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice 수정</title>
</head>
<body>
	<h1> 공지사항 수정 </h1>
	<form action="/notice/modify" method="post">
		<input type="text" size="100" name="notice-subject" value="<%=notice.getNoticeSubject() %>"> 
		<textarea rows="30" cols="100" name="notice-contents">  <%=notice.getNoticeContents() %></textarea><br>
		<!-- hidden => 숫자를 안보이게끔 하는 것  -->
		<input type="hidden" name="noticeNo" value="<%= notice.getNoticeNo() %>">
		<input type="submit" value="수정완료">
		<input type="submit" value="취소">
	</form>
</body>
</html>