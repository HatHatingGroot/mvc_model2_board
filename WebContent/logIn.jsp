<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Jua&display=swap');

body {
	font-family: 'Jua', sans-serif;
}

input, textarea {
	border-radius: 10px 10px;
	padding-left: 10px;
	font-family: 'Jua', sans-serif;
}

header>h3::first-letter {
	color: red;
	font-weight: bold;
}

.userInput {
	display: inline-block;
	width: 40px;
	margin-left: 20px;
}

h3 {
	margin-left: 20px;
}

input:focus {
	font-weight: bold;
}

.btn:hover {
	border: 2px solid gray;
	background-color: lightblue;
}
</style>
</head>
<body>
	<header>
		<h3>Log-In</h3>
	</header>
	<section>
		<c:set var="idIncorrect" value="${requestScope.idIncorrect }" />
		<c:set var="pwIncorrect" value="${requestScope.pwIncorrect }" />
		<c:choose>
			<c:when test="${idIncorrect eq 'true'}">
				<script>
					alert("아이디 또는 비밀번호가 일치하지 않습니다");
				</script>
			</c:when>
			<c:when test="${pwIncorrect eq 'true'}">
				<script>
					alert("아이디 또는 비밀번호가 일치하지 않습니다");
				</script>
			</c:when>
		</c:choose>

		<form name="frm" action="logInAction.do" method="post">
			<span class="userInput">ID</span><input type="text" name="userID" autofocus>
			<br> <br> 
			<span class="userInput">PW</span><input type="password" name="userPW">
			<br> <br> 
			<input class="btn" type="button" value="ID/PW찾기" onclick="location.href='findIDPW.do';">&nbsp;&nbsp;
			<input class="btn" type="submit" value="로그인하기" onclick="return isFilled();">&nbsp;&nbsp;
			<input class="btn" type="button" value="회원가입하기" onclick="location.href='join.do';">
		</form>
	</section>
	<footer> </footer>

	<script>
		function isFilled() {
			var userID = document.frm.userID;
			var password = document.frm.userPW;
			if ((userID.value == null | userID.value == "")) {
				alert("아이디를 입력해주세요");
				userID.focus();
				return false;
			} else if ((password.value == null | password.value == "")) {
				alert("비밀번호를 입력해주세요");
				password.focus();
				return false;
			} else {
				return true;
			}
		}
	</script>

</body>
</html>