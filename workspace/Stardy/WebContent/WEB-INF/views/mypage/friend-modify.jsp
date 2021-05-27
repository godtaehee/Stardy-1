<%@page import="com.stardy.entity.Friend"%>
<%@page import="java.util.List"%>
<%@page import="com.stardy.service.FriendServiceImpl"%>
<%@page import="com.stardy.entity.Member"%>
<%@page import="com.stardy.service.MemberServiceImpl"%>
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
    <link rel="stylesheet" href="../css/mypage/common.css">
    <link rel="stylesheet" href="../css/mypage/friends.css">

    <!-- Javascript -->
    <script src="../js/mypage/mypage.js"></script>
    
    <title>Document</title>
</head>

<body>
<%

	Integer loginId = (Integer) request.getSession().getAttribute("id");

MemberServiceImpl service = new MemberServiceImpl();

Member member = service.get(loginId);
%>
    <div class="container-only  body__container">
        <%@include file="/layout/header.jsp" %>

        <main class="main-only">

            <section class="my-page-box">
                <%@include file="/layout/mypage/common.jsp" %>

                    <div class="content-form-box">
                        <form action="#" class="content-form form">
                            <div>
                                <input type="text" name="nickname" class="input-friend input--text" placeholder="친구 검색">
                                <button class="button-img button-search">팔로잉 검색</button>
                            </div>
                        </form>
                    </div>

                    <div class="content-list-box">
                        <div>
                            <label class="content-list-title">내 팔로잉 목록</label>
                            <a href="friends.jsp"><button class="btn button friend-modify">확인</button></a>
                        </div>
                        <div class="content-list">
                        
                 			<div class="friend-profile-box">
                                <div class="friend-profile-main">
                                	<button class="button delete-friend button-delete button-img"></button>
                                    <img class="friend-icon" src="../img/11.png" alt="친구 프로필 아이콘">
                                </div>
                                <div class="friend-profile-sub">
                                    <p class="friend-name">정다겸</p>
                                </div>
                            </div>
                 			
                        </div>
                    </div>
                </div>

            </section>
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
<script>
	window.loginId = '<%=loginId %>';
	window.status = true;
</script>   
<script src="../js/ajax/ajax.js"></script>
<script src="../js/mypage/friends.js"></script>
<script src="../js/friendModule.js"></script>
</body>
</html>