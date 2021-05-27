
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
    <link rel="stylesheet" href="../css/mypage/bookmark.css">

    <!-- Javascript -->
    
    <title>Document</title>
</head>

<body>


    <div class="container-only body__container">
        <%@include file="/layout/header.jsp" %>

        <main class="main-only">
            <section class="my-page-box">
                <%@include file="/layout/mypage/common.jsp" %>

                    <div class="bookmarks-box">
                        <div class="bookmarks">
                            <!-- <div class="bookmark-box">
                                <div class="bookmark-main">
                                    <div class="content-box text-no-over">
                                        오늘 스터디 중 유익한 내용 공유합니다.
                                    </div>
                                    <span class="icon-bookmark delete-bm"></span>
                                </div>
                                <div class="bookmark-sub">
                                    <div class="writer-box">
                                        James
                                    </div>
                                    <div class="date-box">
                                        2021-03-27
                                    </div>
                                </div>
                            </div> -->
                        </div>
                    </div>
					
					<!-- Pager -->
					<nav class="pager">
			            <h1 class="d-none">페이저</h1>
			            
			            <a href="#"><button class="button prev"></button></a>
			            
			            <ul id="pager-list">
			                
			            </ul>
			            
			            <a href="#"><button class="button next"></button></a>
			            
			        </nav> 
					<!-- Pager -->
                
            </section>
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
<script>
	window.loginId = '${id}';
</script>
<script src="../js/ajax/ajax.js"></script>
<script src="../js/mypage/bookmark.js"></script>
<script src="../js/mypage/mypage.js"></script>
<script src="../js/mypage/subModule.js"></script>
>>>>>>> refs/remotes/real/master
</body>
</html>