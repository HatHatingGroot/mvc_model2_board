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
	
	<h1>Welcome to My Page!!</h1>
	
	<jsp:include page="nav.jsp"></jsp:include>
	
	<c:set var="withdrawal" value ="${requestScope.withdrawal }"/>
	
	<c:choose>
	<c:when test="${withdrawal == 'false' }">
		<script>alert("입력하신 정보가 일치하지 않습니다 회원탈퇴에 실패하였습니다");</script>
	</c:when>
	<c:when test="${withdrawal == 'true' }">
		<script>alert("회원탈퇴가 완료되었습니다");location.href="main.do";</script>
	</c:when>
	<c:when test="${withdrawal == null }">
	</c:when>
	</c:choose>
	
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${requestScope.result == 'true' }"> --%>
<!-- 			<script>alert("회원정보 수정이 완료되었습니다");</script> -->
<%-- 		</c:when> --%>
<%-- 		<c:when test="${requestScope.result == 'false' }"> --%>
<!-- 			<script>alert("회원정보 수정이 완료되었습니다");</script> -->
<%-- 		</c:when> --%>
<%-- 		<c:when test="${requestScope.result == null }"> --%>
<!-- 			<script>alert("회원정보 수정이 완료되었습니다");</script> -->
<%-- 		</c:when> --%>
<%-- 	</c:choose> --%>
	
	
	<a href = "personalInfo.do">개인정보 수정</a><br>
	<a href = "#" onclick="withdraw();">회원탈퇴</a><br>
	
	<script>
		function withdraw(){
			if(confirm("정말 탈퇴하시겠습니까?")){
				var inputPW = prompt("비밀번호를 입력해주세요");
				location.href="withdrawal.do?inputPW="+inputPW;
			}
		}
	</script>
</body>
</html>