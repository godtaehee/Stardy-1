<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS -->
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/basic.css">
    <link rel="stylesheet" href="../css/header.css">
    <link rel="stylesheet" href="../css/main-only/layout.css">
    <link rel="stylesheet" href="../css/main-only/element.css">
    <link rel="stylesheet" href="../css/mypage/common.css">
    <link rel="stylesheet" href="../css/mypage/modify.css">

    <!-- Javascript -->
    <script src="../js/mypage/mypage.js"></script>
    <script src="../js/mypage/modify.js"></script>
    <title>Document</title>
</head>

<body>

    <div class="container-only body__container">
        <%@include file="/layout/header.jsp" %>

        <main class="main-only">
            <section class="my-page-box">
                <%@include file="/layout/mypage/common.jsp" %>

                    <section class="modify-box">
                        <h1 class="hide">회원 정보 수정 폼</h1>
                        
                        <div class="modify-form-box">
                            <form action="/mypage/modify" class="modify-form" method="post">
                                <div class="input-control">내 닉네임</div>
                                <input class="input-item input-nick input--text" type="text" value="${member.nickname }" name="nickname">

                                <div class="input-control">내 이메일</div>
                                <input class="input-item input--text" type="email" value="${member.email }" name="email" readonly>

                                <div class="input-control">비밀번호 변경</div>
                                <input class="input-item input-password input--text" type="password" placeholder="******" name="password">

                                <div class="input-control">비밀번호 확인</div>
                                <input class="input-item input--text" type="password" placeholder="******" name="check">
								<input type="hidden" name="id" value="${member.id }">			
                                <div class="signup-error hide"></div>
                                <div class="button-box">
                                    <button class="btn button button-modify">개인정보 수정</button>
                                    <button class="btn button button-drop-out">회원 탈퇴</button>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>

            </section>
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
<script>
	window.loginId = '${id}';
	window.msg = '${msg}';
</script>
<script src="../js/ajax/ajax.js"></script>
</body>
</html>