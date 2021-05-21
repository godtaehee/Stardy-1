<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="com.stardy.service.StudyServiceImpl" %>
<%@ page import="com.stardy.entity.Study" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stardy.entity.Category" %>
<%@ page import="com.stardy.entity.view.StudyView" %>
<%@ page import="com.stardy.service.*" %>


<%
	int memberId = 0;

	if(request.getSession().getAttribute("id") != null)
		memberId = (int) request.getSession().getAttribute("id");

    IndexService indexService = new IndexServiceImpl();
    
    List<StudyView> myStudy = null;
    List<StudyView> notInStudy = null;
    boolean haveStudy = false;
    
	if(memberId != 0){
		myStudy = indexService.getMyStudyList(memberId);
		notInStudy = indexService.getStudyListNotMember(memberId);
		haveStudy = (myStudy.size() == 0) ? false : true;
	}
	
	if(memberId == 0) {
		haveStudy = false;
		notInStudy = indexService.getStudyList();
	}

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/newIndex.css">
    <link rel="stylesheet" href="css/basic.css">
    <link rel="stylesheet" href="css/header.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <%@include file="/layout/header.jsp"%>
        <section class="jumbotron">
            <h1 class="hide">jumbo</h1>
            <div class="jumbo-content">
                <div class="jumbo-title">Stardy</div>
                <div class="large-content">
                    <div class="first-content">공부가 막막하다면 ?</div>
                    <div class="second-content">스타디에서 함께할 팀원을 찾아보세요</div>
                </div>
                <div class="small-content">
                    <div class="first-content">작심삼일은 이제 그만 !</div>
                    <div class="second-content">팀원들과 함께 배우고, 나누고, 성장하세요</div>
                </div>
            </div>
        </section>

        <main class="main">
            <div class="exam-info">
                <div class="exam-info-header">
                    <div class="info-title">시험 정보</div>
                    <button type="button" class="info-btn">등록하기
                        <img >
                    </button>
                </div>
                <div class="exam-info-main">
                    <div class="prev-btn">
                        <div class="arrow"></div>
                    </div>
                    <div class="exam-info-item">
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">중간고사</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">20일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                        <div class="exam-card">
                            <div class="exam-name">오픽 241회 시험</div>
                            <div class="exam-term">
                                <div class="exam-term-remain">30일 남음</div>
                                <div class="exam-term-date">2021년 5월 17일</div>
                            </div>
                        </div>
                    </div>
                    <div class="after-btn">
                        <div class="arrow"></div>
                    </div>
                </div>
            </div>

            <div class="study-list">
                    <div class="study-list-header">
                        <div class="study-title">내 스터디 목록</div>
                        <a href="study/board/list.jsp"><div class="arrow"></div></a>
                    </div>
                    <div class="study-list-desc">스터디룸에 입장해보세요</div>
                    <div class="study-list-item">
                        <div class="prev-btn"></div>
                        <ul class="study-list-container">
                        <%if(request.getSession().getAttribute("id") == null || !haveStudy) {%>
                        	<li class="notMember">
                        		<span>아직 가입한 스터디가 없습니다!</span>
                        	</li>
                        	<%} else { %>

                            <%for(int i = 0; i < myStudy.size(); i++) {%>
                            <li class="mini-card">
                                <a href="study/board/detail.jsp?id=<%=String.valueOf(myStudy.get(i).getId())%>">
                                    <div class="mini-card-container" >
                                        <div class="mini-card-img"></div>
                                        <div class="mini-card-title"><%=myStudy.get(i).getTitle()%></div>
                                        <div class="mini-card-info">
                                            <div class="mini-card-population">정원 <%=myStudy.get(i).getCnt()%>/<%=myStudy.get(i).getLimit()%>명</div>
                                            <div class="mini-card-kind"><%=myStudy.get(i).getName()%></div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <%}
                            }%>
                   
                        </ul>
                        <div class="after-btn"></div>
                    </div>
                </div>
            <div class="study-list">
                <div class="study-list-header">
                    <div class="study-title">이런 스터디는 어때요?</div>
                    <div class="arrow"></div>
                </div>
                <div class="study-list-desc">곧 모집이 마감되는 스터디에요! 개설된 스터디는 '스터디 보기' 메뉴에서 조회할 수있어요</div>
                <div class="study-list-item">
                    <div class="prev-btn"></div>
                    <ul class="study-list-container">               
                       <%for(int i = 0; i < notInStudy.size(); i++) {%>
                            <li class="mini-card">
                                <a href="study/board/detail.jsp?id=<%=String.valueOf(notInStudy.get(i).getId())%>">
                                    <div class="mini-card-container">
                                        <div class="mini-card-img"></div>
                                        <div class="mini-card-title"><%=notInStudy.get(i).getTitle()%></div>
                                        <div class="mini-card-info">
                                            <div class="mini-card-population">정원 <%=notInStudy.get(i).getCnt()%>/<%=notInStudy.get(i).getLimit()%>명</div>
                                            <div class="mini-card-kind"><%=notInStudy.get(i).getName()%></div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                       <%}%>
                    </ul>
                    <div class="after-btn"></div>
                </div>
            </div>

        </main>

        <%@include file="/layout/footer.jsp" %>

    </div>
    <script>

         (() => {
             const examInfo = document.querySelector('.exam-info-item');
             const card = document.querySelectorAll('.exam-card');
             const prev = document.querySelector('.exam-info-main .prev-btn');
             console.log(prev);
             const after = document.querySelector('.exam-info-main .after-btn');
             const container = document.querySelector('.container');
             let start = 0;
             let dx = 100;
             // card.style.display = 'flex';


             console.log(card);

             prev.addEventListener('click', () => {
                 if(start >= 0) return;

                 for(let i = 0; i < card.length; i++){
                     card[i].style.transform = `translateX(${start + dx}px)`;
                 }
                 start+=dx;
             });

             after.addEventListener('click', () => {
   
                 if(start <= -1000) return;
                 
                 

                 for(let i = 0; i < card.length; i++){
         
                     card[i].style.transform = `translateX(${start - dx}px)`;
                     console.log(start-dx);
                 }
                 start-=dx;
          
             });

             window.addEventListener("load", function(){

                 var userId = '${email}';

                 console.log(userId);

                 if(userId !== null && userId !== undefined && userId !== ""){
                     var onBox = document.querySelector(".on-box");
                     var outBox = document.querySelector(".out-box");

                     onBox.className = 'on-box';
                     outBox.className = 'out-box hide';
                 }
             });



         })();
    </script>
</body>
</html>