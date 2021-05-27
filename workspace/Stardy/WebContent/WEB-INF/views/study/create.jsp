
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    <style>
    	.jumbotron-img-container {
    		width: 100%;
    		height: 100%;
    	}
    </style>
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
                   	<!-- 폼태그폼태 폼태그폼태 폼태그폼태 폼태그폼태 폼태그폼태 폼태그폼태 -->
            	<form action="target" method="post" enctype="multipart/form-data">
            		<input type="hidden" class="category" name="category">
            		<input type="text" name="memberId" value="${memberId }">
            		   <section class="bg-section">
                        <div class="jumbotron-img">
                            <div class="d-none">이미지</div>
                           	<div class="jumbotron-img-container"></div>
                            <label class="camera">
                           	    <input type="file" style="visibility:hidden" name="imgFile" accept="image/*" onchange="setThumbnail(event)"/>

                           	</label>
                        </div>
                        <div class="study-name float-content flex-column">
                            <h2 class="section-title">스터디 이름</h2>
                            <input class="input-create" name="title" type="text" size="60" placeholder="스터디 이름을 입력해 주세요.">
                        </div>
            
                        <div class="category float-conten flex-column">
                            <h2 class="section-title">카테고리</h2>
                            <ul class="choose">
                                <li>IT</li>
								<li>어학</li>
                                <li>자격증</li>
                                <li>공무원</li>
                                <li>취미</li>
                                <li>기타</li>
                            </ul>
                        </div>
                        <br />
                        <div class="member-option float-content flex-column">
                        <ul>
                            <li>
                                <label class="section-title">인원 수</label>
                                <input class="input-create" type="number" name="limit" min="1" max="30">
                            </li>
                            <li>
                                <label class="section-title">비공개</label>
                                <input class="check" type="checkbox" name="open" value="0">
                            </li>
                        </ul>
 
                        </div>
                        <br />
                        
                        <div class="due-date float-content">
                            <h2 class="section-title">모집 종료일</h2>
                            <!-- 모집 종료일을 선택하세요. -->
                            <input class="calendar" type="date" name="duedate" >
                        </div>
                        <br />
                        <div class="introduce-study float-content flex-column">
                            <h2 class="section-title">간단한 스터디 소개</h2>
                            <input class="input-create" type="text" placeholder="" name="intro">
                        </div>
            
                        <section class="create-btn">
                            <label class="d-none">개설하기</label>
                            <a href="studyList.html">
                                <button type="submit" class="img-create" href="study/target">개설하기</button>
                            </a>
                        </section>
            
                </section> <!--bg-section꺼 -->
            	</form>
                 
                </div>
            </div>
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>
    
    <script>
    
    
    const category = document.querySelector(".category");
    
    const choose = document.querySelector('.choose');
    
    choose.addEventListener('click', (e) => {
    	
    	if(e.target.tagName !== 'LI')
    		return;
    	category.value = e.target.innerText;
    });
   	
   	
    function setThumbnail(event) {
        var reader = new FileReader();
        reader.onload = function(event) {
            var img = document.createElement("img");
            img.setAttribute("src", event.target.result);
            img.style.width = "100%";
            img.style.height = "100%";
 			console.log(event.target.result);
 			const container = document.querySelector(".jumbotron-img-container");
 			if(container.innerHTML != ""){
 				container.innerHTML = "";
 			
 			}
            container.appendChild(img); };
        reader.readAsDataURL(event.target.files[0]);
    }
    
    
    </script>
</body>
</html>