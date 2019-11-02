<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

.inputEl {
	display: inline-block;
	width: 130px;
	/* border: 1px solid; */
}

input, textarea {
	border-radius: 10px 10px;
	padding-left: 10px;
	font-family: 'Jua', sans-serif;
}

textarea {
	width: 300px;
}

section {
	padding-left: 15px;
}

header {
	padding-left: 100px;
}

.must rt {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<header>
		<h3>Join</h3>
	</header>
	<article>
		<section>
			<form name="frm" class="input" action="personalInfoMod.do" method="post">
				<span class="inputEl">이름</span>
				<input type="text" name="userName" autofocus placeholder="${requestScope.uvo.userName }"><br>
				
				<span class="inputEl">이메일</span>
				<input type="text" name="userEmailID" placeholder="${fn:split(uvo.userEmail,'@')[0]}">@<input type="text" name="userEmailDomain" id="userEmailDomain" placeholder="${fn:split(uvo.userEmail,'@')[1]}"> <select id="selection" onchange="writeDomain();">
					<option value="">직접입력</option>
					<option value="gmail.com">gmail.com</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
				</select><br><br>
				
				<span class="inputEl">사진</span><input type="file" name="userPhoto"><br>
				<br> 
				
				<span class="inputEl" id="userIntro">자기소개</span>
				<textarea name="userIntro" rows="10" maxlength="500" placeholder="${uvo.userIntro }"></textarea>
				<br><br>
			
				<input type="submit" value="수정하기">
			</form>
		</section>
	</article>
	<footer> </footer>
	
	<script>
		
		function writeDomain() {
			var target = document.getElementById("selection");
			var domainAddr = target.options[target.selectedIndex].value
			if (domainAddr == "") {
				document.getElementById("userEmailDomain").focus();
			}
			document.getElementById("userEmailDomain").value = domainAddr;
		}
	</script>

</body>
</html>