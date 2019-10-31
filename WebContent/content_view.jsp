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

table, td, th {
	border: 2px solid gray;
	border-collapse: collapse;
}

input[type=radio] {
	display: none;
}

#pageList {
	width: 350px;
	text-align: center;
}

#ContentShow ,#setReply{
	width: 600px;
	height: 600px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 5px;
}
#setReply{ height:200px;}
#ContentShow tr:first-of-type td:first-of-type {
	width: 80%;
}

#ContentShow tr:first-of-type td:last-of-type {
	width: 10%;
}

#ContentShow tr:last-of-type {
	height: 80%;
}

#replyShow {
	width: 600px;
	height: 200px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 3px;
}

#replyShow tr:first-of-type td:first-of-type {
	width: 90%;
}

#replyShow tr:last-of-type {
	height: 70%;
}

.toDo {
	margin-bottom: 10px;
}
#setReply textarea{
	width:100%;
	height:150px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<h1>Welcome to Board Page!!</h1>
		<jsp:include page="nav.jsp"></jsp:include>

<%-- 		<c:set var="dvo" value='${requestScope.bvo }' /> --%>
		<c:set var="rList" value='${requestScope.rList }' />

		<hr>

		<table id="ContentShow">
			<tr>
				<td>${bvo.userID }</td>
				<td rowspan="2">
				<c:choose>
						<c:when test="${rList[0].userID==null}">
					 		<img src="images/조회수.jpg" width="25px" height="25px">&nbsp; 0
						</c:when>
						<c:otherwise>
							<img src="images/조회수.jpg" width="25px" height="25px">&nbsp;${fn:length(rList )}
						</c:otherwise>
					</c:choose>
				</td>
				<td rowspan="2"><img src="images/댓글.png" width="25px" height="25px">&nbsp;${bvo.bHit }</td>
			</tr>
			<tr>
				<td>${bvo.bDate }</td>
			</tr>
			<tr>
				<td colspan="2">${bvo.bTitle }</td>
				<td rowspan="2"><img src="images/좋아요.png" width="35px" height="35px" onclick="">&nbsp;${bvo.bLike }</td>
			</tr>
			<tr>
				<td colspan="2">${bvo.bContent }</td>
			</tr>

		</table>
		<input class="toDo" type="button" value="목록" onclick="location.href='list.do';"> 
		
		<c:if test="${sessionScope.userID == bvo.userID }">
		<input class="toDo" type="button" value="수정" onclick="location.href='modify.do?bID=${param.bID }';"> 
		<input class="toDo" type="button" value="삭제" onclick="location.href='delete.do?bID=${param.bID }';"> 
		</c:if>
		
		<fieldset>
		<legend>댓글창</legend>
		<div id="setReply">
			<form name="reply" action="reply.do" method="post">
			<input type="hidden" name="bID" value = "${param.bID }">
			<input type="hidden" name="userID" value = "${sessionScope.userID }">
			<textarea name="rContent" maxlength="250" placeholder="내용을 입력해주세요(250자 이내)"></textarea>
			<input type="submit" value="등록" >
			</form>
		</div>
		
		
		
		<c:forEach var="r" items="${rList }">
			<c:if test="${r.userID != null }">
				<table id="replyShow">
					<tr>
						<td>${r.userID }</td>
						<td rowspan="3"><img src="images/좋아요.png" width="35px" height="35px" onclick="">&nbsp;${r.rLike }</td>
					</tr>
					<tr>
						<td>${r.rDate }</td>
					</tr>
					<tr>
						<td>${r.rContent }</td>
					</tr>
				</table>
			</c:if>
		</c:forEach>
		</fieldset>

	</div>
	<script>
		var sort = "bDate"; // 정렬 기준
		var order = "DESC" // 정렬 방향
		var pageNum = ""; //페이지 번호
		var queryType = "";
		var query = "";
		if ('${param.sort }' != null & '${param.sort }' != '')
			sort = '${param.sort }';
		if ('${param.order }' != null & '${param.order }' != '')
			order = '${param.order }';
		if ('${param.pageNum }' != null)
			pageNum = '${param.pageNum }';
		if ('${param.queryType }' != null)
			queryType = '${param.queryType }';
		if ('${param.query }' != null)
			query = '${param.query }';

		document.getElementById(sort).style.color = "blue";
		document.getElementById(sort).style.fontWeight = "bold";

		document.getElementById(order).style.color = "blue";
		document.getElementById(order).style.fontWeight = "bold";

		function chSort(obj) {//정렬 함수
			sort = obj.value;
			pageNum = 1;
			location.href = ("list.do?sort=" + sort + "&order=" + order
					+ "&pageNum=" + pageNum + "&queryType=" + queryType
					+ "&query=" + query);
		}

		function chOrder(obj) {//정렬 방향 함수
			order = obj.value;
			pageNum = 1;
			location.href = ("list.do?sort=" + sort + "&order=" + order
					+ "&pageNum=" + pageNum + "&queryType=" + queryType
					+ "&query=" + query);
		}

		function chPageNum(pNum) {//페이지 이동
			pageNum = pNum;
			location.href = ("list.do?sort=" + sort + "&order=" + order
					+ "&pageNum=" + pageNum + "&queryType=" + queryType
					+ "&query=" + query);
		}

		function doQuery() {//검색 
			queryType = document.getElementById('queryType').value;
			query = document.getElementById('query').value;
			pageNum = 1;
			location.href = ("list.do?sort=" + sort + "&order=" + order
					+ "&pageNum=" + pageNum + "&queryType=" + queryType
					+ "&query=" + query);
		}
	</script>
</body>
</html>