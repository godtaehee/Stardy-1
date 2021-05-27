<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/reset.css">
    <!-- <link rel="stylesheet" href="../css/layout.css"> -->
    <!-- <link rel="stylesheet" href="css/style.css"> -->
    <link rel="stylesheet" href="../css/basic.css">
    <link rel="stylesheet" href="../css/main-only/layout.css">
    <link rel="stylesheet" href="../css/main-only/element.css">
    <link rel="stylesheet" href="../css/header.css">
    <!-- <link rel="stylesheet" href="../css/studyList2.css"> -->
    <link rel="stylesheet" href="../css/studyCreate.css">
    <title>Document</title>
</head>

<body>
    <div class="container-only">
        <%@include file="/layout/header.jsp" %>

        <main class="main-only">
            <div class="section float-content">
                <div class= "inner body__container">
                    <div class="visual_title">
                        <h1 class="d-none">스터디 개설</h1>
                        <p class="section-title" id="banner">스터디 개설</p>
                    </div>
            
                    <section class="bg-section">
                        <div class="jumbotron-img">
                            <div class="d-none">이미지</div>
                            <label class="camera"></label>
                        </div>
            
                        <div class="study-name float-content flex-column">
                            <h2 class="section-title">스터디 이름</h2>
                            <input class="input-create" type="text" size="60" placeholder="스터디 이름을 입력해 주세요.">
                        </div>
            
                        <div class="category float-conten flex-column">
                            <h2 class="section-title">카테고리</h2>
                            <ul class="choose">
                                <li>IT</li>
                                <li>공무원</li>
                                <li>어학</li>
                                <li>자격증</li>
                                <li>취미</li>
                                <li>기타</li>
                            </ul>
                        </div>
                        <br />
                        <div class="member-option float-content flex-column">
                            <li>
                                <label class="section-title">인원 수</label>
                                <input class="input-create" type="number" min="1" max="30">
                            </li>
                            <li>
                                <label class="section-title">공개 여부</label>
                                <input class="check" type="checkbox">
                            </li>
                        </div>
                        <br />
                        
                        <div class="due-date float-content">
                            <h2 class="section-title">모집 종료일</h2>
                            <!-- 모집 종료일을 선택하세요. -->
                            <input class="calendar" type="date" >
                        </div>
                        <br />
                        <div class="introduce-study float-content flex-column">
                            <h2 class="section-title">간단한 스터디 소개</h2>
                            <input class="input-create" type="text" placeholder="">
                        </div>
            
                        <section class="create-btn flex-column">
                            <label class="d-none">개설하기</label>
                            <button class="modi-btn" href="">수정완료</a>
                        </section>
            
                </section> <!--bg-section꺼 -->
                </div>
            </div>
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
</body>
</html>