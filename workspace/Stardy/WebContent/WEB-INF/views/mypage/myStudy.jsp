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
    <link rel="stylesheet" href="../css/mypage/myStudy.css">

    <!-- Javascript -->
    <script src="../js/mypage/mypage.js"></script>
    <script src="../js/mypage/myStudy.js"></script>
    <title>Document</title>
</head>

<body>
    <div class="container-only body__container">
        <%@include file="/layout/header.jsp" %>

        <main class="main-only">

            <section class="my-page-box">
                <h1 class="hide">마이페이지</h1>
                <div class="profile-box">
                    <h1 class="hide">프로필 박스</h1>
                    <div class="dummy-box"></div>
                    <div class="profile-icon-box">
                        <img class="profile-icon" src="../img/icon-person-dummy.png" alt="profile icon">
                    </div>
                    <div class="profile-info-box">
                        <div class="profile-nick-box">
                            <label class="profile-nick">왕밤빵</label>
                        </div>
                        <div class="profile-status-box">
                            <input class="profile-status" type="text" value="💻 코딩의 늪" readonly>
                        </div>
                        <div class="profile-modify-box">
                            <button class="btn button button-img profile-modify">프로필 수정</button>
                        </div>
                    </div>
                </div>

                <div class="content-box">
                    <h1 class="hide">컨텐츠 박스</h1>
                    <nav class="content-menu">
                        <ul>
                            <li class="nav-item">
                                <a href="friends" class="friend-list">친구목록</a>
                            </li>
                            <li class="nav-item menu-underline">
                                <a href="study" class="my-study">가입한 스터디</a>
                            </li>
                            <li class="nav-item">
                                <a href="bookmark" class="my-bookmark">즐겨찾기</a>
                            </li>
                        </ul>
                    </nav>

                    <div class="my-studies-box">
                        <div class="my-studies">

                            <div class="study-box">
                                <div class="study-bg-box">
                                    <img class="study-bg" src="../img/detail/jumbotron.png">
                                </div>
                                <div class="study-name text-no-over">
                                    날씨 앱 개발 프로젝트 모집합니다
                                </div>
                            </div>

                            <div class="study-box">
                                <div class="study-bg-box">
                                    <img class="study-bg" src="../img/detail/jumbotron.png">
                                </div>
                                <div class="study-name text-no-over">
                                    날씨 앱 개발 프로젝트 모집합니다
                                </div>
                            </div>

                            <div class="study-box">
                                <div class="study-bg-box">
                                    <img class="study-bg" src="../img/detail/jumbotron.png">
                                </div>
                                <div class="study-name text-no-over">
                                    날씨 앱 개발 프로젝트 모집합니다
                                </div>
                            </div>

                            <div class="study-box">
                                <div class="study-bg-box">
                                    <img class="study-bg" src="../img/detail/jumbotron.png">
                                </div>
                                <div class="study-name text-no-over">
                                    날씨 앱 개발 프로젝트 모집합니다
                                </div>
                            </div>

                            <div class="study-box">
                                <div class="study-bg-box">
                                    <img class="study-bg" src="../img/detail/jumbotron.png">
                                </div>
                                <div class="study-name text-no-over">
                                    날씨 앱 개발 프로젝트 모집합니다
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </section>
        </main>
        
        <footer class="footer">
            <h1 class="hide">footer</h1>
            
        </footer>
    </div>
<script>
	window.addEventListener("load", function(){
	
	    var userId = '${email}';
	
		console.log(userId);
	
	    if(userId != null && userId != undefined){
	        var onBox = document.querySelector(".on-box");
	        var outBox = document.querySelector(".out-box");
	        	
	        onBox.className = 'on-box';
	        outBox.className = 'out-box hide';
	    }
	})
</script>
</body>
</html>