<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
<!-- 	미로그인시 -->
	<ul>
		<li><a href="logIn.do">LogIn</a></li>
		<li><a href="join.do">Join</a></li>
	</ul>
	
<!-- 	로그인시 -->
	<ul>
		<li><a href="logOut.do">LogOut</a></li>
		<li><a href="myPage.do">MyPage</a></li>
	</ul>
</body>
</html>