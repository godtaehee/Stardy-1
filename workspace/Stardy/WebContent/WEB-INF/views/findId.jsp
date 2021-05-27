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
    <title>Document</title>
</head>
<body>
    <div class="body__container">
    
        <%@include file="/layout/header.jsp" %>
        <section>
            <h1>아이디 찾기</h1>
            <p>아이디를 잊으셨나요 ? </p>

            <h2>아이디 찾기</h2>
            <form action="">
                <fieldset>
                    <legend>아이디 찾기 입력 폼</legend>
                    <ul>
                        <li>
                            <label for="nicname">닉네임</label>
                            <input type="text" id="nicname" placeholder="스타디 닉네임을 입력하세요">
                            <input type="submit" value="확인">
                        </li>

                    </ul>
                </fieldset>
            </form>
            <p>혹시 비밀번호를 찾으시나요?</p>
            <a href="#">비밀번호 찾기</a>
        </section>
    </div>
</body>
</html>