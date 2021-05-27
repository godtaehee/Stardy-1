<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/studyList.css" type="text/css"
	rel="style.css">
<link rel="stylesheet" href="../css/reset.css" type="text/css"
	rel="style.css">
<link rel="stylesheet" href="../css/basic.css" type="text/css"
	rel="style.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/header.css" type="text/css"
	rel="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet" href="../css/find_pw.css">
<title>비밀번호 찾기</title>
</head>
<body>
	<div class="body__container">

		<!-- HEADER -->

		<%@include file="/layout/header.jsp"%>


		<section class="find_container">
			<div class="bg"
				style="background-image: url('../img/bg_find_pw.svg')"></div>

			<span class="title">
				<p>비밀번호를 잊으셨나요 ?</p>
			</span> <span class="desc_pw">
				<p>이메일로 재설정 링크를 보내드립니다.</p>
			</span>

			<form class="find_form" action="/findpw" method="get">
				<fieldset>
					<legend class="d-none">비밀번호 찾기</legend>
					<ul>
						<li><label class="d-none" for="nicname">닉네임</label> 
						<input class="input--text" type="text" id="nicname" placeholder="가입한 이메일을 입력하세요" name="memberEmail">
						<input class="btn" type="submit" value="확인"></li>
					</ul>
				</fieldset>
			</form>


		</section>
	</div>

</body>
</html>