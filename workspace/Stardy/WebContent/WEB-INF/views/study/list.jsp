<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.stardy.controller.study.StudyController" %>
<%@ page import="com.stardy.entity.view.StudyView" %>
<%@ page import="com.stardy.service.StudyServiceImpl" %>
<%@ page import="com.stardy.service.StudyService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
   String success = request.getParameter("success");
   if(success == null)
      success = "0";
%>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/studyList.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../../css/reset.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../../css/basic.css" type="text/css" rel="style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/header.css" type="text/css" rel="style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <title>StudyList</title>
</head>

<body>
<div class="body__container">

<div class="complete-make-study" 
style="display:<%if(success.equals("0")){%>none<%}else{%>flex<%}%>">
   <div class="complete-content">
         <div class="cancel-btn"></div>
         <div class="congratz-img"></div>
         <div class="congratz-say">스터디가 정상적으로 만들어졌습니다 축하드립니다 !</div>
   </div>
</div>


    <%@include file="/layout/header.jsp" %>

    <div class="section section--visual">
        <div class="inner">
            <div class="visual_title">
                <p>스터디 목록</p>
            </div>
        </div>
    </div>

    <div class="section section--category">
            <div class="list_search">
                <div class="search-form">
                    <form id="category-search-form" method="get" action="/study/list">
                        <input type="text" name="q" value="${param.q}" class="input--text" placeholder="스터디 찾아보기">
                        <input class= "btn btn-search" type="submit" value="검색">
                    </form>
                </div>
            
                <div class="search-menu">
                    <ul>
                        <li class="btn"><a href="list?c=0&q=${param.q}&p=${param.p}" class="btn">IT</a></li>
                        <li class="btn"><a href="list?c=1&q=${param.q}&p=${param.p}" class="btn">어학</a></li>
                        <li class="btn"><a href="list?c=2&q=${param.q}&p=${param.p}" class="btn">자격증</a></li>
                        <li class="btn"><a href="list?c=3&q=${param.q}&p=${param.p}" class="btn">고시/공무원</a></li>
                        <li class="btn"><a href="list?c=4&q=${param.q}&p=${param.p}" class="btn">취미/교양</a></li>
                        <li class="btn"><a href="list?c=5&q=${param.q}&p=${param.p}" class="btn">기타</a></li>
                 
                        <a href="../create.jsp">
                            <li class="btn btn--primary">스터디 개설하기</li>
                        </a>
                    </ul>
                </div>
            </div>
    </div>


    <!-- MAIN -->
    <main class="section section--main">
        <a href="realStudy.html">

<c:forEach var ="study" items="${list}">
  <div class="inner">
            <div class="study_img">
                <img src="../../img/study_img.png" alt="">
            </div>

            <div class="desc">
                <div class="text">
                    <div class="category">${study.name}</div>
                    <div class="title">${study.title}</div>
                    <div class="note">${study.intro}</div>
                </div>


                <div class="text_date">
                    <div class="detail">
                        <ul>
                            <li>정원</li>
                            <li>${study.cnt}/${study.limit}</li>
                            <li>명</li>
                        </ul>
                    </div>

                    <div class="date">
                        <p>개설일 : </p>
                        <p>${study.regDate}</p>

                        <p>모집 종료일 : </p>
                        <p>${study.dueDate}</p>
                    </div>
                </div>

            </div>
        </div>        
        </a>
        <a href="realStudy.html">
</c:forEach>




<c:set var="page" value ="${(param.p==null)?1:param.p}" />
<c:set var="startNum" value ="${page-(page-1)%5}"/>
<c:set var="lastNum" value ="${fn:substringBefore(Math.ceil(total/10),'.')}"/>
<div>${lastNum}</div>
        <nav class="pager">
            <h1 class="d-none">페이저</h1>
            <c:if test="${startNum > 1}">
               <div class="button"><a href="?p=${startNum-1}&q=${param.q}&c=${param.c}">이전</a></div>
         </c:if>
            
            <ul>
               <c:forEach var="i" begin="0" end="4">
               <c:if test="${(startNum+i)<=lastNum}">
                <li><a class="${(page==(startNum+i))?'orange':''}"href="?p=${startNum+i}&c=${param.c}&q=${param.q}">${startNum+i}</a></li>
               </c:if>
               </c:forEach>
            </ul>          

               <c:if test="${startNum+4<lastNum}">
                <div class="button"><a href="?p=${startNum+5}&q=${param.q}&c=${param.c}">다음</a></div>
               </c:if>

        </nav> 
    </main>

<%@include file="/layout/footer.jsp" %>


<script>

   (() => {
      const completeBox = document.querySelector('.complete-make-study');
      completeBox.addEventListener('click', (e) => {      
         if(e.target.className==='complete-make-study' || e.target.className==='cancel-btn')
            completeBox.remove();   
      });       
   })();
   
</script>
</div>
</body>
</html>