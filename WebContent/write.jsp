<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

#wrapper {
	width: 800px;
	height: 1000px;
	margin-left: auto;
	margin-right: auto;
	/* 		text-align:center; */
}

input, textarea {
	font-family: 'Jua', sans-serif;
	font-size: 1.2rem;
	width: 300px;
	margin-bottom: 10px;
}

textarea {
	height: 500px;
	overflow:scroll;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div id="wrapper">
		<h1>Welcome to Write Page!!</h1>
		<jsp:include page="nav.jsp"></jsp:include>

		<hr>
		<form name="frm" action="writeAction.do" method="post">
			<input type="text" name="bTitle" placeholder="제목을 입력하세요"><br>
			<textarea maxlength="500" name="bContent"placeholder="글내용을 입력해주세요(500자 이내)"></textarea>
			<br><input type="reset" value="다시쓰기">
			<br><input type="submit" value="등록">
		</form>
	</div>
	<script>
		
	</script>
</body>
</html>