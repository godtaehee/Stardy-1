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
    <link rel="stylesheet" href="../css/signup.css">

    <!-- Javascript -->
    <script src="../js/signup.js"></script>
    <title>Document</title>
</head>

<body>
    <div class="container-only body__container">
        <%@include file="/layout/header.jsp" %>

        <main class="main-only">
            <section class="signup-container">
                <h1 class="signup-title">회원가입</h1>

                <div class="signup-form-container">
                    <h1 class="hide">회원가입 폼</h1>
                    <form action="/signup" method="post" class="signup form-signup">

                        <div class="input-control">닉네임을 입력해주세요.</div>
                        <input class="input-item input-nick input--text" type="text" placeholder="gorany" name="nick">

                        <div class="input-control">이메일을 입력해주세요.</div>
                        <input class="input-item input--text" type="email" placeholder="error@mail.com" name="email">

                        <div class="input-control">비밀번호를 입력해주세요.</div>
                        <input class="input-item input-password input--text" type="password" placeholder="******" name="password">

                        <div class="input-control">비밀번호 확인</div>
                        <input class="input-item input--text" type="password" placeholder="******" name="check">

                        <div class="signup-error hide"></div>
                        <div>
                            <button class="btn button button-signup">Create account</button>
                        </div>

                    </form>

                </div>
            </section>
            
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
</body>
</html>