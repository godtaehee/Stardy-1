<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/studyList.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../css/reset.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../css/basic.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/header.css" type="text/css" rel="style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="../css/changePw.css">
    <title>Document</title>

</head>
<body>

    <div class="body__container">

        <section class="find_container">

            <span class="title">
                <p> 비밀번호 변경하기</p> 
            </span>

            <form class="find_form" action="/changePw" method="post">
                <fieldset>
                    <legend class="d-none">비밀번호 변경</legend>
                    <ul>
                        <li><input class="input--text" type="password" placeholder="새 비밀번호 (6글자 이상)" name="newPw"> </li>
                        <li><input class="input--text" type="password" placeholder="새 비밀번호 확인" name="checkPw"></li> 
                        <li><input class="btn" id="btnChange" type="submit" value="변경"></li>
                    </ul>
                </fieldset>
            </form>
        </section>

    </div>


</body>
</html>