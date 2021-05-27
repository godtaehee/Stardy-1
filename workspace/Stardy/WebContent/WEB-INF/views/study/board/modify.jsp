
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS -->
    
    <link rel="stylesheet" href="../../css/basic.css">
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/main-only/layout.css">
    <link rel="stylesheet" href="../../css/main-only/element.css">
    <link rel="stylesheet" href="../../css/board/read.css">
    <link rel="stylesheet" href="../../css/reset.css">

    <!-- CDN -->
    <script src="https://kit.fontawesome.com/a93ae2d5d1.js" crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <title>Document</title>
</head>

<body>
    <div class="container-only body__container">
        <%@include file="/layout/header.jsp" %>
        
        <main class="main-only">
            <section class="board-read-box">
                <h1 class="hide">게시글 상세 보기</h1>
                
                <section class="board-box">
                    <h1 class="hide">게시글 상세</h1>
                    
                    <form action="/study/board/modify" method="post" id="action-form">
	                    <input type="hidden" name="id" value="${boardView.id }">
	                    
	                    <div class="pager-box">
	                        <h1 class="hide">게시글 페이저</h1>
	                    </div>
	                    
	                    <div class="input-box">
	                        <input type="text" class="input-item title" name="title" value="${boardView.title }">
	                    </div>
	
	                    <div class="input-box span-box">
	                        <span class="span writer">${boardView.name }</span>
	                        <span class="span">/</span>
	                        <span class="span regdate">${boardView.regDate }</span>
	                    </div>
	                    <hr>
	                    <div class="input-box">
	                        <textarea name="content" rows="20" class="input-item input--text" >${boardView.content }</textarea>
	                    </div>
	
						<div class="input-box">
	                    	<ul>
	                        <c:forEach var="file" items="${files}">
	                        	<li class="">
	                        		<a download="${file.name }" href="/upload/${file.path}/${file.uuid}_${file.name}">${file.name}</a>
	                        	</li>
	                        </c:forEach>
	                        </ul>
	                    </div>
						<hr>
	
	                    <nav class="util-box">
	                        
	                        <div class="button-box">
	                            <h1 class="hide">버튼 박스</h1>
	                            <a href="/study/board/read?id=${boardView.id }" class="btn button button-back">취소</a>
	                            
	                            
	                            <c:if test="${id eq boardView.memberId }">
	                            <a href="#" class="btn button button-modify">저장</a>
	                            <a href="#" class="btn button button-delete">삭제</a>
	                            </c:if>
	                            
	                            <!-- 사용자 인증을 위한 Email 데이터, Session의 Email과 대조 해서 본인 확인 -->
	                            <input type="hidden" name="email" value="${boardView.memberId }">
	                        </div>
	                    </nav>
                    </form>
                </section>
                
            </section>
            
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>

<!-- Javascript -->
<script>
	window.email = '${email}';
	window.bid = '${boardView.id }';
</script>
<script src="../../js/board/modify.js"></script>
<script src="../../js/ajax/ajax.js"></script>
</body>
</html>