<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
    
    
    
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
                        <input type="text" id="" class="input--text" placeholder="스터디 찾아보기">
                        <input class= "btn btn-search" type="submit" value="검색">
                    </form>
                </div>

                <div class="search-menu">
                    <ul>
                        <li class="btn">IT</li>
                        <li class="btn">어학</li>
                        <li class="btn">자격증</li>
                        <li class="btn">고시/공무원</li>
                        <li class="btn">취미/교양</li>
                        <li class="btn">기타</li>
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


 <c:forEach var ="n" items="${list}">
        <div class="inner">
            <div class="study_img">
                <img src="../../img/study_img.png" alt="">
            </div>

            <div class="desc">
                <div class="text">
                    <div class="category">${n.category}</div>
                    <div class="title">${n.title}</div>
                    <div class="note">${n.intro}</div>
                </div>


                <div class="text_date">
                    <div class="detail">
                        <ul>
                            <li>정원</li>
                            <li>0/30</li>
                            <li>명</li>
                        </ul>
                    </div>

                    <div class="date">
                        <p>개설일 : </p>
                        <p> ${n.regdate}</p>

                        <p>모집 종료일 : </p>
                        <p> 2021-04-12</p>
                    </div>
                </div>

            </div>
        </div>        
        </a>
        <a href="realStudy.html">
  </c:forEach>     
       
        <div class="inner">
            <div class="study_img">
                <img src="../../img/study_img.png" alt="">
            </div>

                <div class="desc">
                    <div class="text">
                        <div class="category"> IT </div>
                        <div class="title">Sihum 스터디</div>
                        <div class="note">열심히 공부하는 사람들을 위한 스터디 입니다. 열정만
                            있다면 누구나 환영합니다. 같이 즐스터디해용</div>
                    </div>


                    <div class="text_date">
                        <div class="detail">
                            <ul>
                                <li>정원</li>
                                <li>0/30</li>
                                <li>명</li>
                            </ul>
                        </div>

                        <div class="date">
                            <p>개설일 : </p>
                            <p> 2021-03-21</p>

                            <p>모집 종료일 : </p>
                            <p> 2021-04-12</p>
                        </div>
                    </div>

                </div>
        </div>
        </a>

        <div class="inner">
            <div class="study_img">
                <img src="../../img/study_img.png" alt="">
            </div>

            <div class="desc">
                <div class="text">
                    <div class="category"> IT </div>
                    <div class="title">Sihum 스터디</div>
                    <div class="note">열심히 공부하는 사람들을 위한 스터디 입니다. 열정만
                    있다면 누구나 환영합니다. 같이 즐스터디해용</div>
                </div>


                <div class="text_date">
                    <div class="detail">
                        <ul>
                            <li>정원</li>
                            <li>0/30</li>
                            <li>명</li>
                        </ul>
                    </div>

                    <div class="date">
                        <p>개설일 : </p>
                        <p> 2021-03-21</p>

                        <p>모집 종료일 : </p>
                        <p> 2021-04-12</p>
                    </div>
                </div>

            </div>
        </div>
        <div class="inner">
            <div class="study_img">
                <img src="../../img/study_img.png" alt="">
            </div>

            <div class="desc">
                <div class="text">
                    <div class="category"> IT </div>
                    <div class="title">Sihum 스터디</div>
                    <div class="note">열심히 공부하는 사람들을 위한 스터디 입니다. 열정만
                    있다면 누구나 환영합니다. 같이 즐스터디해용</div>
                </div>


                <div class="text_date">
                    <div class="detail">
                        <ul>
                            <li>정원</li>
                            <li>0/30</li>
                            <li>명</li>
                        </ul>
                    </div>

                    <div class="date">
                        <p>개설일 : </p>
                        <p> 2021-03-21</p>

                        <p>모집 종료일 : </p>
                        <p> 2021-04-12</p>
                    </div>
                </div>

            </div>
        </div>
        <div class="inner">
            <div class="study_img">
                <img src="../../img/study_img.png" alt="">
            </div>

            <div class="desc">
                <div class="text">
                    <div class="category"> IT </div>
                    <div class="title">Sihum 스터디</div>
                    <div class="note">열심히 공부하는 사람들을 위한 스터디 입니다. 열정만
                    있다면 누구나 환영합니다. 같이 즐스터디해용</div>
                </div>


                <div class="text_date">
                    <div class="detail">
                        <ul>
                            <li>정원</li>
                            <li>0/30</li>
                            <li>명</li>
                        </ul>
                    </div>

                    <div class="date">
                        <p>개설일 : </p>
                        <p> 2021-03-21</p>

                        <p>모집 종료일 : </p>
                        <p> 2021-04-12</p>
                    </div>
                </div>

            </div>
        </div>

        <nav class="pager">
            <h1 class="d-none">페이저</h1>
            <div class="button"></div>
            <ul>
                <li><a href>1</a></li>
                <li><a href>2</a></li>
                <li><a href>3</a></li>
                <li><a href>4</a></li>
                <li><a href>5</a></li>
            </ul>
            <div class="button">다음</div>
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