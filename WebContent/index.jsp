<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member login</title>
</head>
<body>
	<!-- 값의 유무에 따라 어떤 것을 출력할지를 정한다. -->
	<c:if test="${sessionScope.userId eq null}">
	<h1>login page</h1>
	<form action="member/login" method="post">
	ID : <input type="text" name="user-id"><br>
	PW : <input type="text" name="user-pw"><br>
	<input type=submit value="login">
	<input type=reset value="cancel">
	<a href="member/enroll.html">register</a>
	</form>
	</c:if>
	<!-- 로그인이 됐을 경우 로그인 창 대신 이걸 띄우겠다. -->
	<!-- 세션 : 유저가 정보를 담고 언제든지 꺼내 쓸 수 있는 공간 -->
	<c:if test="${userId ne null and userId ne ''}">
	<h1>${userId}님 환영합니다.</h1>
	<a href="/member/logout">logout</a><br>
<%-- 	<form action="/member/myinfo">
		<input type="text" name="student-id" value="<%= studentId %>">
		<input type="submit" value="my info"> 
	</form> 아랫줄이랑 같은 내용임  ? => 키 값이 시작된다. --%>
	<!-- localhost:1989/member/myinfo?student-id=ahn1234 (주소창에 이렇게 뜬다) -->
	<a href="/member/myinfo?studentId=${userId }">My info</a><br>
	<a href="/member/listAll">전체 회원 조회</a><br>
	<a href="/file/upload">파일 업로드</a><br>
	<a href="file/list">파일 목록 조회</a><br>
	<a href="/notice/list">NOTICE</a>
	</c:if>
</body>
</html>