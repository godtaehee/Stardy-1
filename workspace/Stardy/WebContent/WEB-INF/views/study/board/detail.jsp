<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/layout.css">
    <link rel="stylesheet" href="../../css/basic.css">
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/style.css">


    <title>Document</title>

    <style>
        .offer-study {
            position: fixed;
            width: 100vw;
            height: 100vh;
            background: rgba(0,0,0,0.7);
            z-index: 1000;
        }
    </style>
</head>


<body>
    <div class="complete-make-study" style="display:none">
        <div class="complete-content">
                <div class="cancel-btn"></div>
                <div class="congratz-img" style="background: url('../../img/studying.jpeg') no-repeat center center;"></div>
                <div class="congratz-say">${study.title}에 가입하시겠습니까 ?</div>

            <form action="/study/join" method="post">
                <input type="hidden" name="studyId" value="${study.id}">
                <input type="hidden" name="memberId" value="${study.memberId}">
                <input type="submit" style="width: 100%; height: 2rem; background-color: #2b98ba; color: white" value="가입하기">
            </form>

        </div>
    </div>
    <div class="container">




        <%@include file="/layout/header.jsp" %>


        <aside class="aside">
            <h1 class="hide">aside</h1>
        </aside>


        <section class="jumbotron" style="background: url('${path}') no-repeat center center;">
            <h1 class="hide">jumbo</h1>
            <div class="jumbotron-text">
                <div class="title-container">
                    <div class="jumbo-title">${study.title}</div>
                    <div class="jumbo-study-date">
                        <div class="jumbo-icon"></div>
                        <div class="jumbo-date-text"> ${duringTime} 일째 스터디 중</div>
                    </div>
                </div>
                <div class="jumbo-menu">
                    <div class="jumbo-button">
                        <div class="invite-icon"></div>
                        <button type="button">초대</button>
                    </div>
                    <div class="jumbo-button">
                        <div class="setting-icon"></div>
                        <button type="button">설정</button>
                    </div>
                </div>
            </div>
        </section>

        <main class="main">
            <h1 class="hide">main</h1>
            <div class="main-menu">
                <div class="main-button">
                    <div class="hot-icon"></div>
                </div>
                <div class="main-button">
                    <div class="new-icon"></div>
                </div>
                <div class="aside-active-btn">
                    <button class="btn btn-show">=</button>
                    <div class="about-study">
                        <div class="about-study-header">About Study</div>
                        <div class="about-main">
                            <div class="about-study-content">${study.intro}</div>
                            <div class="about-study-info">
                                <div class="about-member">
                                    <div class="member-cnt">${study.cnt}</div>
                                    <div class="member-txt">Members</div>
                                </div>
                                <div class="about-posts">
                                    <div class="post-cnt">130</div>
                                    <div class="post-txt">Posts</div>
                                </div>

                            </div>
                            <div class="about-study-create-date">
                                <div class="calender">
                                    <div class="calender-img"></div>
                                    <div class="calender-txt">Created</div>
                                </div>
                                <div class="create-date">2021년 3월 31일</div>
                            </div>
                            <div class="about-study-master">
                                <div class="master-img-container">
                                    <div class="master-img"></div>
                                    <div class="master-txt">스터디 개발자</div>
                                </div>
                                <div class="master-name">왕밤빵</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="write">
                <div class="pencil"></div>
                <input class="write-section" type="text" value="게시글을 작성해주세요.">
            </div>

            <ul class="card-list">
                <c:forEach var="board" items="${board}">
                    <li class="card">
                        <div class="up-and-down">
                            <div class="up"><input type="button" style="border:0; background-color:transparent"></div>
                            <div class="recommend-cnt">${board.likes}</div>
                            <div class="down"><input type="button" style="border:0; background-color:transparent"></div>
                        </div>
                        <div class="card-content">
                            <div class="profile">
                                <div class="profile-picture"></div>
                                <div class="profile-name">${board.name}</div>
                                <div class="date">1h 20m ago</div>
                            </div>
                            <div class="title">${board.title}</div>
                            <div class="content">${board.content}</div>
                            <div class="etc">
                                <div class="comment">
                                    <div class="comment-img"></div>
                                    <div class="comment-cnt">${board.replyCnt} comments</div>
                                </div>
                                <div class="save">
                                    <div class="save-img"></div>
                                    <div class="save-txt">save</div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </main>

        <aside class="aside">
            <c:if test="${!isMember}">
                <div class="join-study" style="background-color:#3ca0bf; height:50px; display:flex; justify-content:center; align-items:center; cursor:pointer; margin-bottom:10px">
                    <a class="" style="color:white">스터디 가입하기</a>
                </div>
            </c:if>
            <div class="about-study">
                <div class="about-study-header">About Study</div>
                <div class="about-main">
                    <div class="about-study-content">${study.intro}</div>
                    <div class="about-study-info">
                        <div class="about-member">
                            <div class="member-cnt">${study.cnt}</div>
                            <div class="member-txt">Members</div>
                        </div>
                        <div class="about-posts">
                            <div class="post-cnt">130</div>
                            <div class="post-txt">Posts</div>
                        </div>

                    </div>
                    <div class="about-study-create-date">
                        <div class="calender">
                            <div class="calender-img"></div>
                            <div class="calender-txt">Created</div>
                        </div>
                        <div class="create-date">${study.regDate}</div>
                    </div>
                    <div class="about-study-master">
                        <div class="master-img-container">
                            <div class="master-img"></div>
                            <div class="master-txt">스터디 개설자</div>
                        </div>
                        <div class="master-name">${study.leader}</div>
                    </div>
                </div>
            </div>
            <div class="top-rate">
                <div class="top-rate-header flex">상위 게시글</div>
                <div class="top-content">
                    <ul>
                        <li class="card">
                            <div class="card-content">
                                <div class="profile">
                                    <div class="profile-picture"></div>
                                    <div class="profile-name">왕밤빵</div>
                                    <div class="date">1h 20m ago</div>
                                </div>
                                <div class="title">CSS 적용이 왜 안될까요?</div>
                                <div class="content">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto culpa error eum, exercitationem, magni molestiae nam natus ratione repellendus sapiente sint temporibus unde! Culpa distinctio exercitationem facilis ipsa maiores odio, possimus quam quis recusandae repudiandae saepe sapiente similique sunt suscipit ut. Dolores excepturi incidunt porro quaerat quidem? Alias cumque cupiditate dolores iusto magnam magni recusandae, sit totam ullam. A doloremque eos labore libero maiores molestias nihil nulla quas rerum sed!</div>
                                <div class="etc">
                                    <div class="comment">
                                        <div class="comment-img"></div>
                                        <div class="comment-cnt">2 comments</div>
                                    </div>
                                    <div class="save">
                                        <div class="save-img"></div>
                                        <div class="save-txt">save</div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </aside>

        <div class="modal hide">
            <div class="modal-board">
                <div class="modal-con">
                    <div class="modal-head">
                        <div class="write-hand"></div>
                        <div class="write-content">글을 작성해주세요.</div>
                    </div>
                    <form class="modal-body" action="/study/write" method="post">
                    	<input type="hidden" name="id" value=${memberId}>
                        <input class="modal-title" type="text" name="title" placeholder="제목을 입력하세요">
                        <input type="file" name="f">
                        <textarea class="modal-content" cols="30" rows="10" name="content"></textarea>
                        <div class="modal-footer">
                            <button type="submit">작성</button>
                            <button type="button" class="cancelBtn">취소</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <%@include file="/layout/footer.jsp" %>
    </div>
    <script>

        window.addEventListener("load", () =>{

    

            const write = document.querySelector('.write-section');
            const modal = document.querySelector('.modal');
            const jumboMenu = document.querySelector('.jumbo-menu');
            const joinStudy = document.querySelector('.join-study');
            const completeMakeStudy = document.querySelector('.complete-make-study');




            if(joinStudy){
                joinStudy.addEventListener('click', () => {
                    completeMakeStudy.classList.toggle('show');
                });
            }

            completeMakeStudy.addEventListener('click', (e) => {

                if(e.target.className==='complete-make-study' || e.target.className==='cancel-btn')
                    completeMakeStudy.remove();

            });


            let flag = ${isRegistered};
            if(!flag) {
                jumboMenu.style.display = 'none';
            }


            write.addEventListener('click', () =>{
                console.log("ㅁㅇㄴㄹ클릭됬당ㄹㄴㅁㅇㄴㄹㅁㅇㄴㄹ");
                if(modal.classList.contains('hide')){
                    modal.classList.remove('hide');
                    modal.classList.add('flex-show');
                }
            });


            modal.addEventListener('click', (e) => {
                if(e.target.classList.contains('modal') || e.target.classList.contains('cancelBtn')){
                    modal.classList.add('hide');
                    modal.classList.remove('flex-show');
                }
            });

            const button = document.querySelector(".btn-show");
            flag = true;
            button.addEventListener("click", function(e) {
                const aside = document.querySelector(".about-study");

                console.log(aside);
                if(flag){
                    aside.style='display: block; transition: 1s;';
                    flag = !flag;
                }
                else{
                    aside.style='display: none; transition: 1s;';
                    flag = !flag;
                }
            });
        });
    </script>
</body>
</html>