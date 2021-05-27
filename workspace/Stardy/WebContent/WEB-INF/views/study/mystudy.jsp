<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/MystudyList.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../css/reset.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../css/basic.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/header.css" type="text/css" rel="style.css">

    <title>MyStudyList</title>
    <script src="https://kit.fontawesome.com/df0a9bb94a.js" crossorigin="anonymous"></script>
</head>
<body class="body__container">

<%@include file="/layout/header.jsp" %>

    <!-- VISUAL -->
    <div class="section--visual">
        <h2 class="hide">비주얼</h2>
        <div class="vstitle">
            <p>함께하고 있는 스터디 목록</p>
            <p>작심삼일은 이제 그만!</p>
            <p>팀원들과 함께 배우고, 나누고 성장하세요</p>
        </div>
    </div>

<main class="section section--main">
    <div class="inner">
    <!--카테고리 버튼-->
    <div class="section--category">
        <div class="list_search">
            <div class="search-menu">
                <ul>
                    <li class="btn2">IT</li>
                    <li class="btn2">어학</li>
                    <li class="btn2">자격증</li>
                    <li class="btn2">고시/공무원</li>
                    <li class="btn2">취미/교양</li>
                    <li class="btn2">기타</li>
                </ul>
            </div>
        </div>
    </div>


    <div class="card-list-container">
        <ul class="card-list">
            <li class="card-item">
                <figure class="card-image" style="background-image: url(../img/study_img.png); height: 132px;">
                    <img src="" alt="스터디 이미지">
                </figure>
                <div class="card-desc">
                    <p>자바를 잡는 스터디</p>
                    <p>각자 모여서 궁금한거 질문하면서 스터디해요~ </p>
                    
                    <div></div>
                    <p>회원수</p>
                    
                </div>
            </li>
            <li class="card-item">
                <figure class="card-image" style="background-image: url(../img/study_img.png);">
                    <img src="" alt="스터디 이미지">
                </figure>
                <div class="card-desc">
                    <p>스터디 제목</p>
                    <p>각자 모여서 궁금한거 질문하면서 스터디해요~ </p>
                    <div>회원수</div>
                </div>
            </li>
            <li class="card-item">
                <figure class="card-image" style="background-image: url(../img/study_img.png);">
                    <img src="" alt="스터디 이미지">
                </figure>
                <div class="card-desc">
                    <p>스터디 제목</p>
                    <p>각자 모여서 궁금한거 질문하면서 스터디해요~ </p>
                    <div>회원수</div>
                </div>
            </li>
            <li class="card-item">
                <figure class="card-image" style="background-image: url(../img/study_img.png); height: 132px;">
                    <img src="" alt="스터디 이미지">
                </figure>
                <div class="card-desc">
                    <p>스터디 제목</p>
                    <p>각자 모여서 궁금한거 질문하면서 스터디해요~ </p>
                    <div>회원수</div>
                </div>
            </li>
            <li class="card-item">
                <figure class="card-image" style="background-image: url(../img/study_img.png);">
                    <img src="" alt="스터디 이미지">
                </figure>
                <div class="card-desc">
                    <p>스터디 제목</p>
                    <p>각자 모여서 궁금한거 질문하면서 스터디해요~ </p>
                    <div>회원수</div>
                </div>
            </li>
            <li class="card-item">
                <figure class="card-image" style="background-image: url(../img/study_img.png);">
                    <img src="" alt="스터디 이미지">
                </figure>
                <div class="card-desc">
                    <p>스터디 제목</p>
                    <p>각자 모여서 궁금한거 질문하면서 스터디해요~ </p>
                    <div>회원수</div>
                </div>
            </li>
        </ul>
        
    </div>



    <nav class="pager">
        <h1 class="d-none">페이저</h1>
        <i class="fas fa-caret-left"> 이전</i>
        <div class="button"></div>
        <ul>
            <li><a href>1</a></li>
            <li><a href>2</a></li>
            <li><a href>3</a></li>
            <li><a href>4</a></li>
            <li><a href>5</a></li>
        </ul>
        
        <div class="button">
            다음 <i class="fas fa-caret-right"></i>
        </div>
    </nav> 
</div>
</main>
<%@include file="/layout/footer.jsp" %>
</body>
</html>