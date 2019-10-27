<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<header>
		<h3>Join</h3>
	</header>
	<article>
		<section>
			<form name="frm" class="input" action="joinAction.do" method="post">
				<span class="inputEl">
				<ruby class="must">이름<rt>필수입력</rt></ruby></span>
				<input type="text" name="userName" autofocus><br>
				
				<br> <span class="inputEl"><ruby class="must">아이디<rt>필수입력</rt></ruby></span> 
				<input type="text" name="userID" onchange="document.getElementById('isAble').value='changed';" placeholder= ><!-- 중복확인후 아이디 변경시 isAble값 false로 변경 -->
				<input type="button" value="중복확인" onclick="checkID();"><br>
				<br> <input type="hidden" id="isAble"><!-- 중복확인 결과 저장 태그 -->
				
				<span class="inputEl"><ruby class="must">비밀번호<rt>필수입력</rt></ruby>	</span>
				<input type="password" name="userPW"><br><br>
				
				<span class="inputEl"><ruby class="must">비밀번호 재확인<rt>필수입력</rt></ruby></span>
				<input type="password" name="pwCheck" onkeyup="pwRecheck();"> <span id="pwChecked"></span><br><br> 
				
				<span class="inputEl"><ruby class="must">이메일<rt>필수입력</rt></ruby></span>
				<input type="text" name="userEmailID">@<input type="text" name="emailDomain" id="userEmailDomain"> <select id="selection" onchange="writeDomain();">
					<option value="">직접입력</option>
					<option value="gmail.com">gmail.com</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
				</select><br><br>
				
				<span class="inputEl"><ruby class="must">성별<rt>필수입력</rt></ruby></span>
				<input type="radio" name="userGender" value="male">남자&nbsp; 
				<input type="radio" name="userGender" value="female">여자<br>
				<br> 
				
				<span class="inputEl">사진</span><input type="file" name="userPhoto"><br>
				<br> 
				
				<span class="inputEl" id="userIntro"><ruby class="must">자기소개<rt>필수입력</rt></ruby></span>
				<textarea name="userIntro" rows="10" maxlength="500"></textarea>
				<br><br>
			
				<input type="submit" value="가입하기" onclick="return isFilled()">
			</form>
		</section>
	</article>
	<footer> </footer>
	
	<script>
		function checkID() {
			var idInput = document.frm.userID.value;
			window.open('idCheck.do?idInput=' + idInput, '아이디 중복 확인',
							'width=430,height=500,location=no,status=no,scrollbars=yes');
		}

		function pwRecheck() {
			var pwOrigin = document.frm.userPW.value;
			var pwInput = document.frm.pwCheck.value;
			if (pwOrigin == pwInput) {
				document.getElementById("pwChecked").innerHTML = '&nbsp;&nbsp;일치함';
				document.getElementById("pwChecked").style = "color:green;";
			} else {
				document.getElementById("pwChecked").innerHTML = '&nbsp;&nbsp;미일치';
				document.getElementById("pwChecked").style = "color:red;";
			}
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
			var password = document.frm.userPW;
			var emailID = document.frm.userEmailID;
			var emailDomain = document.frm.userEmailDomain;
			var gender = document.frm.userGender;
			var introduction = document.frm.userIntro;
			var pwChecked = document.getElementById("pwChecked");
			var pwCheck = document.frm.pwCheck;
			var isAble = document.getElementById("isAble");
			if ((name.value == null | name.value == "")) {
				alert("이름을 입력해주세요");
				name.focus();
				return false;
			} else if ((userID.value == null | userID.value == "")) {
				alert("아이디를 입력해주세요");
				userID.focus();
				return false;
			} else if ((isAble.value != 'true')) {
				if (isAble.value == "changed") {
					alert("중복 확인 후 아이디가 다시 변경되었습니다 다시 중복확인해주세요");
				}else{
					alert("아이디 중복확인을 해주세요");
				}
				userID.focus();
				return false;
			} else if ((password.value == null | password.value == "")) {
				alert("비밀번호를 입력해주세요");
				password.focus();
				return false;
			} else if ((pwCheck.value == null | pwCheck.value == "")
					| (pwChecked.innerHTML != "&nbsp;&nbsp;일치함")) {
				alert("비밀번호 재확인을 해주세요");
				pwCheck.focus();
				return false;
			} else if ((emailID.value == null | emailID.value == "")) {
				alert("이메일을 입력해주세요");
				emailID.focus();
				return false;
			} else if ((emailDomain.value == null | emailDomain.value == "")) {
				alert("이메일 도메인 주소를  입력해주세요");
				emailDomain.focus();
				return false;
			} else if ((gender.value == null | gender.value == "")) {
				alert("성별을 선택해주세요");
				return false;
			} else if ((introduction.value == null | introduction.value == "")) {
				alert("자기소개를 입력해주세요");
				introduction.focus();
				return false;
			} else {
				return true;
			}
		}
	</script>

</body>
</html>