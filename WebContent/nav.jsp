<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Jua&display=swap');

body {
	font-family: 'Jua', sans-serif;
}
</style>
</head>
<body>
	<ul>
		<li><a href="main.do">Main</a></li>
		<li><a href="list.do">Board</a></li>
	</ul>


	<br>
	<hr>
	<c:set var="session" value="${sessionScope.userID}" scope = "session"/>
	<c:set var="userID" value="${uvo.userID}" scope="request" />
	<c:set var="uvo" value="${requestScope.uvo}" scope="session" />
	<br>
	<hr>


	<c:choose>
		<c:when test="${session eq null }">
			<!-- 	미로그인시 -->
			<ul>
				<li><a href="logIn.do">LogIn</a></li>
				<li><a href="join.do">Join</a></li>
			</ul>
		</c:when>
		<c:when test="${session eq userID }">
			<!-- 	로그인시 -->
			<ul>
				<li><a href="logOut.do" onclick="confirm('로그아웃 하시겠습니까???')">LogOut</a></li>
				<li><a href="myPage.do">MyPage</a></li>
			</ul>
		</c:when>
	</c:choose>

</body>
</html>