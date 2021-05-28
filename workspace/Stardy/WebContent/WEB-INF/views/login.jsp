<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/loginModal.css">


    <title>Document</title>
</head>

<body>
    <div class="container-only body__container">
    
	<%@include file="/layout/header.jsp" %>

        <main class="main-only">
            <section class="login-container">
                <h1 class="login-title">로그인</h1>

                <div class="login-form-container">
                    <h1 class="hide">로그인 폼</h1>
                    <form action="/login" method="post" class="form form-login">
                    	<input type="hidden" name="uri" value="${param.uri}">
                        <div class="input-control">이메일을 입력해주세요.</div>
                        <input class="input-item input--text" name="email" type="email" placeholder="error@mail.com">

                        <div class="input-control">비밀번호를 입력해주세요.</div>
                        <input class="input-item input--text" name="password" type="password" placeholder="******">
                        <div class="login-error hide"></div>
                        <div>
                            <button class="btn button button-login"></button>
                        </div>

                        <div class="login-help">
                            <a href="findPw" class="find-password">비밀번호 찾기</a>
                            <img class="between-border" src="../img/between-border.png">
                            <a href="signup" class="signup">회원가입하기</a>
                        </div>
                    </form>

                </div>
            </section>
            
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
    
    
<!-- Modal -->
<div class="modal hide">
    <div class="modal-main">
        <div class="modal-title">회원 가입 완료</div>
        <img class="congratz" src="../img/congratz.png">
    </div>
    <div class="modal-footer">
        <div class="modal-button-box">
            <button class="button button-confirm">확인</button>
        </div>
    </div>
</div>
<!-- Modal -->
    
  	<script>
  		window.msg = '${msg}';
  	</script>
    <!-- Javascript -->
    <script src="../js/login.js"></script>
</body>
</html>