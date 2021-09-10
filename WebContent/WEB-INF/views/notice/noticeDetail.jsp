<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice 상세정보</title>
</head>
<body>
	<h2>제목 : ${requestScope.noticeOne.noticeSubject}</h2>
	<h6>글번호 :${requestScope.noticeOne.noticeNo} / 
		글쓴이 : ${requestScope.noticeOne.writerId} / 
		작성일 : ${requestScope.noticeOne.regDate}</h6>
	<h3>글내용</h3>
		${requestScope.noticeOne.noticeContents}
	<div>
	</div>
	<a href="/notice/modify?noticeNo=${requestScope.noticeOne.noticeNo}">수정</a>
	<a href="/notice/list">목록</a>
	<!-- 쿼리스트림으로 여러개를 보낼 수도 있다. -->
	<a href="/notice/remove?noticeNo=${requestScope.noticeOne.noticeNo}">삭제</a>
</body>
</html>