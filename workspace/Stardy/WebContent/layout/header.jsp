<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <header class="header">
            <h1 class="hide">header</h1>
            <!-- <a href=""></a> -->
    
            <div class="menu">
                <div class="logo flex">
                
                    <a href="/index2.jsp"><div class="icon"></div></a>
                </div>
    
                <div class="user-menu">
                    <form id="search-form" method="POST" action="">
                        <input type="text" id="search" class="input--text" placeholder="Search">
                        <input type="submit" value="Submit" class="hide">
                    </form>
    
                    <div class="on-box hide">
                        <div class="user-item">
                            <a class="btn show-study" href="/study/list.jsp">스터디 보기</a>
                        </div>
                        <div class="user-item">
                            <a class="btn my-page" href="/mypage/friends.jsp">마이페이지</a>
                        </div>
 
                        <div class="drop-menu flex">
                            <div class="drop-list hide">
                                <div class="drop-item">
                                    <a href="#" class="btn-logout">로그아웃</a>
                                </div>
                                <div class="drop-item">
		                            <a class="" href="/study/list.jsp">스터디 보기</a>
		                        </div>
		                        <div class="drop-item">
		                            <a class="" href="/mypage/friends.jsp">마이페이지</a>
		                        </div>
                                <div class="drop-item">
                                    <a href="#">설정</a>
                                </div>
                                <div class="drop-item">
                                    <a href="#">도움말</a>
                                </div>
                            </div>
                        </div>
                    </div>
    
                    <div class="out-box">
                        <div class="">
                            <a class="header-login btn" href="/login.jsp">로그인</a>
                        </div>
                        <div class="">
                            <a class="header-signup btn" href="/signup.jsp">회원가입</a>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        
        <script>
        	var dropMenu = document.querySelector('.drop-menu');
        	dropMenu.addEventListener('click', (e) => {
        		
        		var dropList = document.querySelector('.drop-list');
        		dropList.classList.toggle('show');
        		dropList.classList.toggle('hide');
        	});
        </script>