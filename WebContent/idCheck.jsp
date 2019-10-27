<%@ page language="java"  isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script src="https://code.jquery.com/jquery-3.0.0.js"></script>
	<script src="https://code.jquery.com/jquery-migrate-3.1.0.js"></script>
</head>
<body>
	<h1>IDCheck</h1>
	
	<hr>
	<c:set var="able" value="${requestScope.able }"/>
	 
	<c:if test="${able eq 'true' }">
		${param.idInput } 는 사용 가능한 아이디입니다<br>
		<input type ="button" value="사용하기" onclick="isAble();"><br>
	</c:if>
	<c:if test="${able eq 'false' }">
		${param.idInput } 는 이미 존재하는 아이디입니다<br>
		<span>아이디 입력 :</span> <input type="text" id="userID">&nbsp;&nbsp;
	<input type ="button" value="중복확인" onclick="checkID();"><br><br>
	</c:if>
	

	<script>
		function isAble(){
			var idInput = ${param.idInput };
			opener.document.getElementById("isAble").value = 'true';
			opener.document.frm.userID.value = idInput;
			window.close();
		}
		function checkID(){
			var idInput = document.getElementById("userID").value;
			location.href=('idCheck.do?idInput='+idInput);
		}
	</script>
</body>
</html>