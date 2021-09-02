<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     <%
	String userName = request.getParameter("pname");
	String userColor = request.getParameter("color");
	String userPlayer = request.getParameter("player");
	String [] userFood = request.getParameterValues("food");
    %> --%>
    
    
    <%
	String userName = (String)request.getAttribute("pname");
	String userColor = (String)request.getAttribute("color");
	String userPlayer = (String)request.getAttribute("player");
	String [] userFood = (String [] )request.getAttribute("food");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인취향 테스트</title>
</head>
<body>
	<h2> 개인취향 테스트 (get) </h2>
	<span><%=userName %>님은</span>
	<% 
		if(userColor.equals("red")) {
	%>
				<span style = 'color:red;'>빨간색</span>을 좋아합니다.
	<% 
		}else if(userColor.equals("blue")) {
	%>
			<span style = 'color:blue;'>파란색</span>을 좋아합니다.
	<% 
		}else if(userColor.equals("green")) {
	%>
			<span style = 'color:green;'>초록색</span>을 좋아합니다.
	<% 
		}
	%>
	
	<% 
		if(userPlayer.equals("sjkim")) {
	%>
			김수지 선수를 좋아합니다.
	<% 
		}else if(userPlayer.equals("japark")) {
	%>
			박정아 선수를 좋아합니다.
	<%
		}else if(userPlayer.equals("yjhwang")) {
	%>
			황연주 선수를 좋아합니다.
	<% 
		}else if(userPlayer.equals("hjyang")) {
	%>
			양효진 선수를 좋아합니다.
	<% 
		}
	%>
		좋아하는 음식은 
	<% 
		for(String food : userFood) { 
			if(food.equals("zzajang")) {
	%>
				짜장면,
	<% 
			}else if(food.equals("zzam")) {
	%>
				짬뽕,
	<%
			}else if(food.equals("tang")) {
	 %>
				탕수육,
	<% 
			}else if(food.equals("pal")) {
	%>
				팔보채,
	<%
			}
		}
	%>
		입니다.
	
</body>
</html>