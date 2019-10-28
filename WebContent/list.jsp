<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  	#pageList{
  		width:350px;
  		text-align:center;
  	}
</style>
</head>
<body>
	<h1>Welcome to Board Page!!</h1>
	<jsp:include page="nav.jsp"></jsp:include>
	
	
	<hr>
	<form name="frm">
<!-- 	<label><input type="radio" name="direction" value="DESC" onclick="reArrange();">내림차순</label> -->
<!-- 	<label><input type="radio" name="direction" value="ASC" onclick="reArrange();">오름차순</label> -->
	
	
	<c:choose>
		<c:when test="${param.pageNum == null}">
			<c:set var="pageNum" value="1"/>
		</c:when>
		<c:when test="${param.pageNum != null}">
			<c:set var="pageNum" value="${param.pageNum}"/>
		</c:when>
	</c:choose>
	
	pageNum : ${pageNum}<br>
	beginIndex : ${(pageNum-1)*3}<br>
	endIndex : ${(pageNum-1)*3 + 3}<br>
	listLength : ${fn:length(requestScope.list )/3}<br>
	<c:set var = "listLength" value="${fn:length(requestScope.list )/3}"/>
	maxPageNum : ${listLength+(1-(listLength%1))%1}
	<c:set var = "maxPageNum" value="${listLength+(1-(listLength%1))%1}"/>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th><label><input type="radio" name="arrange" value="bDate" onclick="setArrStandard(this);reArrange();">작성일</label></th>
			<th><label><input type="radio" name="arrange" value="bHit" onclick="setArrStandard(this);reArrange();">조회수</label></th>
			<th><label><input type="radio" name="arrange" value="bLike" onclick="setArrStandard(this);reArrange();">좋아요</label></th>
		</tr>
		<c:forEach var="b" begin="${(pageNum-1)*3}" end="${(pageNum-1)*3 + 2}" step="1" items="${requestScope.list }">
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
	
	<div id="pageList">
		<c:if test="${pageNum>1 }">
			<a href="list.do?pageNum=${pageNum -1 }">${pageNum -1 }</a>
		</c:if>
			<a href="list.do?pageNum=${pageNum }">${pageNum }</a>
		<c:if test="${pageNum<maxPageNum }">
			<a href="list.do?pageNum=${pageNum +1 }">${pageNum +1 }</a>
		</c:if>
	</div>
	<script>
		var arrStandard = "";
		function setArrStandard(obj){
			arrStandard = obj.value;
		}
		function getPageNum(){
			return ${pageNum};
		}
		function getArrStandard(){
			return arrStandard;
		}
		function reArrange(){
// 			var arrStandard = document.getElementById("arrange").value;
// 			var direction = document.getElementById("direction").value;
// 			var direction = document.frm.direction.value;
// 			location.href=("list.do?arrStandard="+arrStandard+"&direction="+direction);
			var p1 = getPageNum();
			var p2 = getArrStandard();
			location.href=("list.do?pageNum="+p1+"&arrStandard="+p2);
		}
	</script>
</body>
</html>