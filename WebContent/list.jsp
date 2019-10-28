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
	table, td, th{
	border:2px solid gray;
	border-collapse:collapse;
	}
 	input[type=radio]{  
  	display:none;}  
</style>
</head>
<body>
	<h1>Welcome to Board Page!!</h1>
	<jsp:include page="nav.jsp"></jsp:include>
	
	
	<hr>
	<form name="frm">
<!-- 	<label><input type="radio" name="direction" value="DESC" onclick="reArrange();">내림차순</label> -->
<!-- 	<label><input type="radio" name="direction" value="ASC" onclick="reArrange();">오름차순</label> -->
	
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th><label><input type="radio" name="arrange" value="bDate" onclick="reArrange(this);">작성일</label></th>
			<th><label><input type="radio" name="arrange" value="bHit" onclick="reArrange(this);">조회수</label></th>
			<th><label><input type="radio" name="arrange" value="bLike" onclick="reArrange(this);">좋아요</label></th>
		</tr>
		<c:forEach var="b" items="${requestScope.list }">
		<tr>
			<td>${b.bID }</td><td><a href="content_view.do?bID=${b.bID }">${b.bTitle }</a></td><td>${b.userID }</td><td>${b.bDate }</td><td>${b.bHit }</td><td>${b.bLike }</td>
		<tr>
		</c:forEach>
		
	</table>
	</form>
	<select id="searchCon">
		<option value="userID">작성자</option>
		<option value="bTitle">제목</option>
		<option value="bContent">내용</option>
		<option value="bID">글번호</option>
	</select>
	<input type="text" id="search" ><input type="button" value="검색">
	
	<script>
		
		function reArrange(obj){
// 			var arrStandard = document.getElementById("arrange").value;
// 			var direction = document.getElementById("direction").value;
			var arrStandard = obj.value;
// 			var direction = document.frm.direction.value;
// 			location.href=("list.do?arrStandard="+arrStandard+"&direction="+direction);
			location.href=("list.do?arrStandard="+arrStandard);
		}
	</script>
</body>
</html>