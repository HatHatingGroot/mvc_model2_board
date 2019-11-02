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

#findID {
	display: none;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<header>
		<h3 id="title">Find ID</h3>
	</header>
	<article>
		<section>

			<c:set var="inputCorrect" value="${requestScope.inputCorrect }" />
			<c:set var="PW" value="${PW.userPW }" scope="page" />
			<c:set var="ID" value="${requestScope.ID }" />
			<hr>
				<c:if test="${PW != null }">
					<script>alert('회원님의 초기화 비밀번호는 ' + '${PW }' +' 입니다');</script>
				</c:if>
				<c:if test="${ID != null }">
					<script>alert('회원님의 아이디는 ' + '${ID}' +' 입니다');</script>
				</c:if>
					
			<c:if test="${inputCorrect eq 'false' }">
				<script>
						alert("입력하신 정보가 일치하지 않습니다");
					</script>
			</c:if>

			<hr>

			<form name="frm" class="input" action="findAction.do" method="post">
				<input type="radio" name="find" value="findID" onclick="fID();" checked>아이디 찾기&nbsp;&nbsp; <input type="radio" name="find" value="findPW" onclick="fPW();">비밀번호 초기화 <br> <br> <br> <br> <span class="inputEl">이름 </span> <input type="text" name="userName"
					autofocus
				><br> <br> <br> <span class="inputEl">이메일</span> <input type="text" name="userEmailID">@<input type="text" name="userEmailDomain" id="userEmailDomain"> <select id="selection" onchange="writeDomain();">
					<option value="">직접입력</option>
					<option value="gmail.com">gmail.com</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
				</select> <br> <br> <br>

				<div id="findID">
					<span class="inputEl">아이디</span> <input type="text" name="userID" onchange="document.getElementById('isAble').value='changed';" placeholder=>
					<!-- 중복확인후 아이디 변경시 isAble값 false로 변경 -->
					<br> <br> <br>
				</div>

				<input type="submit" id="submit" value="ID찾기" onclick="return isFilled();">

			</form>
		</section>
	</article>
	<footer> </footer>

	<script>
		function fID() {
			document.getElementById("findID").style = "display:none;";
			document.getElementById("title").innerHTML = "Find ID";
			document.getElementById("submit").value = "ID찾기";
		}
		function fPW() {
			document.getElementById("findID").style = "display:block;";
			document.getElementById("title").innerHTML = "PW INITIALIZE";
			document.getElementById("submit").value = "비밀번호 초기화";
		}

		function writeDomain() {
			var target = document.getElementById("selection");
			var domainAddr = target.options[target.selectedIndex].value
			if (domainAddr == "") {
				document.getElementById("userEmailDomain").focus();
			}
			document.getElementById("userEmailDomain").value = domainAddr;
		}
		function isFilled() {
			var name = document.frm.userName;
			var userID = document.frm.userID;
			var emailID = document.frm.userEmailID;
			var emailDomain = document.frm.userEmailDomain;
			var selector = document.frm.find;

			if ((name.value == null | name.value == "")) {
				alert("이름을 입력해주세요");
				name.focus();
				return false;
			} else if ((emailID.value == null | emailID.value == "")) {
				alert("이메일을 입력해주세요");
				emailID.focus();
				return false;
			} else if ((emailDomain.value == null | emailDomain.value == "")) {
				alert("이메일 도메인 주소를  입력해주세요");
				emailDomain.focus();
				return false;
			} else if ((selector.value == 'findPW')) {
				if ((userID.value == null | userID.value == "")) {
					alert("아이디를 입력해주세요");
					userID.focus();
					return false;
				}
			} else {
				return true;
			}
		}
	</script>

</body>
</html>