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
		<c:set var="pageNum" value="${param.pageNum}"/> 
		
		<c:if test="${param.pageNum == null}">
		<c:set var="pageNum" value="1"/>
		</c:if>
		
		<c:set var="period" value="3"/>
		
		
		
		<c:choose>
		<c:when test="${pageNum < 3}">
		<c:set var="begin" value="${(pageNum-1)*period}"/>
		<c:set var="end" value="${(pageNum)*period -1}"/>
		</c:when>
		<c:otherwise>
		<c:set var="begin" value="6"/>
		<c:set var="end" value="8"/>
		</c:otherwise>
		</c:choose>
<%-- 		param.sort : ${param.sort}<br> --%>
<%-- 		param.order : ${param.order}<br> --%>
<%-- 		param.pageNum : ${param.pageNum}<br> --%>
<%-- 		pageNum : ${pageNum}<br> --%>
<%-- 		period : ${period }<br> --%>
<%-- 		begin : ${begin }<br> --%>
<%-- 		end : ${end }<br> --%>
		<form name="frm">
	<label class="order"  id="DESC" ><input type="radio" name="order"value="DESC" onclick="chOrder(this);">내림차순</label>
	<label class="order" id="ASC" ><input type="radio" name="order" value="ASC" onclick="chOrder(this);">오름차순</label>
	<table>
		<tr>
			<td>userID</td>
			<td>replyNum</td>
			<td>bHit</td>
		</tr>
		<tr>
			<td>bDate</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>bTitle</td>
			<td>bLike</td>
			<td></td>
		</tr>
		<tr>
			<td>bContent</td>
			<td></td>
			<td></td>
		</tr>
<%-- 		<c:forEach var="b" begin="${begin }" end="${end }" step="1" items="${requestScope.list }"> --%>

<!-- 		<tr> -->
<%-- 			<td>${b.bID }</td><td><a href="content_view.do?bID=${b.bID }">${b.bTitle }</a></td><td>${b.userID }</td><td>${b.bDate }</td><td>${b.bHit }</td><td>${b.bLike }</td> --%>
<!-- 		<tr> -->
<%-- 		</c:forEach> --%>
		
	</table>
	</form>
	<select id="queryType">
		<option value="userID">작성자</option>
		<option value="bTitle">제목</option>
		<option value="bContent">내용</option>
		<option value="bID">글번호</option>
	</select>
	<input type="text" id="query" ><input type="button" value="검색" onclick="doQuery();">
<%-- 	listLength : ${fn:length(requestScope.list )} --%>
	<c:set var = "listLength" value="${fn:length(requestScope.list )}"/>
	
	<div id="pageList">
		<c:if test="${pageNum>2 }">
			<input type="button" value = "${pageNum -2 }" onclick="chPageNum(${pageNum -2})">
		</c:if>
		<c:if test="${pageNum>1 }">
			<input type="button" value = "${pageNum -1 }" onclick="chPageNum(${pageNum -1})">
		</c:if>
			<input type="button" value = "${pageNum }" onclick="chPageNum(${pageNum })" style="border:2px solid blue;">
		
		<c:if test="${(pageNum==1) }">
		<c:if test="${(listLength>3) }">
			<input type="button" value = "${pageNum +1 }" onclick="chPageNum(${pageNum +1})">
		</c:if>
		<c:if test="${(listLength>6) }">
			<input type="button" value = "${pageNum +2 }" onclick="chPageNum(${pageNum +2})">
		</c:if>
		</c:if>
		
		<c:if test="${(pageNum==2) }">
		<c:if test="${(listLength>6) }">
			<input type="button" value = "${pageNum +1 }" onclick="chPageNum(${pageNum +1})">
		</c:if>
		<c:if test="${(listLength>9) }">
			<input type="button" value = "${pageNum +2 }" onclick="chPageNum(${pageNum +2})">
		</c:if>
		</c:if>
		
		<c:if test="${(pageNum>2) }">
		<c:if test="${listLength>(3*period) }">
			<input type="button" value = "${pageNum +1 }" onclick="chPageNum(${pageNum +1})">
		</c:if>
		<c:if test="${listLength>(4*period) }">
			<input type="button" value = "${pageNum +2 }" onclick="chPageNum(${pageNum +2})">
		</c:if>
		</c:if>
		
	</div>
	<script>
		
		var sort = "bDate"; // 정렬 기준
		var order = "DESC" // 정렬 방향
		var pageNum = ""; //페이지 번호
		var queryType = "";
		var query = "";
		if('${param.sort }' !=null &'${param.sort }' !='' ) sort = '${param.sort }';
		if('${param.order }' !=null&'${param.order }' !='' ) order = '${param.order }';
		if('${param.pageNum }' !=null) pageNum = '${param.pageNum }';
		if('${param.queryType }' !=null) queryType = '${param.queryType }';
		if('${param.query }' !=null) query = '${param.query }';
		
		document.getElementById(sort).style.color = "blue";
		document.getElementById(sort).style.fontWeight = "bold";
		
		document.getElementById(order).style.color = "blue";
		document.getElementById(order).style.fontWeight = "bold";
		
		

		function chSort(obj){//정렬 함수
			sort = obj.value;
			pageNum = 1;
			location.href=("list.do?sort="+sort+"&order="+order+"&pageNum="+pageNum+"&queryType="+queryType+"&query="+query);
		}
		
		function chOrder(obj){//정렬 방향 함수
			order = obj.value;
			pageNum = 1;
			location.href=("list.do?sort="+sort+"&order="+order+"&pageNum="+pageNum+"&queryType="+queryType+"&query="+query);
		}
		
		function chPageNum(pNum){//페이지 이동
			pageNum = pNum;
			location.href=("list.do?sort="+sort+"&order="+order+"&pageNum="+pageNum+"&queryType="+queryType+"&query="+query);
		}
		
		function doQuery(){//검색 
			queryType = document.getElementById('queryType').value;	
			query = document.getElementById('query').value;
			pageNum = 1;
			location.href=("list.do?sort="+sort+"&order="+order+"&pageNum="+pageNum+"&queryType="+queryType+"&query="+query);
		}
		
	</script>
</body>
</html>