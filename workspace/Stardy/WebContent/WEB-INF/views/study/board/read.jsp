
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
    <link rel="stylesheet" href="../../../css/main-only/layout.css">
    <link rel="stylesheet" href="../../css/main-only/element.css">
    <link rel="stylesheet" href="../../css/board/read.css">
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/modal.css">

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
                    
                    <input type="hidden" value="id" name="id" value="${boardView.id }">
                    
                    <div class="pager-box">
                        
                            <h1 class="hide">게시글 페이저</h1>
                            <c:if test="${next > 0 }">
                            <a href="read?id=${next }" class="btn button"><i class="fas fa-2x fa-angle-up"></i><!-- &nbsp다음 글 --></a>
                            </c:if>
                            
                            <c:if test="${prev > 0 }">
                            <a href="read?id=${prev }" class="btn button"><i class="fas fa-2x fa-angle-down"></i><!-- &nbsp이전 글 --></a>
                            </c:if>
                            
                        </div>
                    
                    <div class="input-box">
                        <input type="text" class="input-item title" value="${boardView.title }" readonly>
                    </div>

                    <div class="input-box span-box">
                        <span class="span writer">${boardView.name }</span>
                        <span class="span">/</span>
                        <span class="span regdate">${boardView.regDate }</span>
                    </div>
                    <hr>
                    <div class="input-box">
                        <textarea name="content" rows="20" class="input-item input--text" readonly>${boardView.content }</textarea>
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
                        
                        <div class="info-box">
	                        <button class="button-like button-img ${isLike? 'like':'unlike'}"></button>
		                    <span class="likes">${boardView.likes }</span>
		                    <button class="bookmark button-img ${isSub? 'icon-bookmark':'icon-bookmark-off'}"></button>
	                    </div>
                        <div class="button-box">
                            <h1 class="hide">버튼 박스</h1>
                            <a href="/study/board/detail?id=${boardView.studyId }" class="btn button button-back">목록</a>
                            
                            <c:if test="${id eq boardView.memberId }">
                            <a href="modify?id=${boardView.id }" class="btn button button-modify">수정하기</a>
                            </c:if>
                            
                            <!-- 사용자 인증을 위한 Email 데이터, Session의 Email과 대조 해서 본인 확인 -->
                            <input type="hidden" name="email" value="${boardView.memberId }">
                        </div>
                    </nav>
                </section>
                
                <section class="reply-box">
                    <h1 class="hide">댓글 창</h1>

                    <section class="reply-add-box">
                        <h1 class="hide">댓글 작성 창</h1>
                        <textarea class="input-item input--text" name="reply-content" rows="5" maxlength="100" placeholder="여러분의 소중한 댓글을 작성해주세요."></textarea>
                        <div class="limit">
                            <span>(</span>
                            <span class="text-limit">0</span>
                            <span >/100)</span>
                        </div>
                        <button class="btn button button-register">등록</button>
                    </section>

                    <section class="reply-list-box">
                        <h1 class="hide">댓글 목록</h1>
                        <label class="reply-box-title">Comments</label>
                        <span class="reply-count">${boardView.replyCnt }</span>
                        <div class="reply-list">
                            <!-- <div class="replies">
                                <div>
                                    <p class="reply">댓글 댓글 댓글1</p>
                                    <span class="span reply-writer">gorany</span>
                                    <span class="span">/</span>
                                    <span class="span regdate">2021-04-22</span>
                                </div>
                            </div> -->
                            
                        </div>
                        <div class="more-box">
                            <i class="fas fa-2x fa-plus-circle button-more"></i>
                        </div>
                    </section>

                </section>
            </section>
            
        </main>
        
<%@include file="/layout/footer.jsp" %>
    </div>

<!-- Modal -->
<div class="modal hide">
    <div class="modal-main">
        <label class="modal-title">Comment</label>
        <div class="reply-content-box">
        <textarea class="input--text reply" rows="10" cols="5"></textarea>
            <!-- <input type="text" class="input--text reply" value=""> -->
        </div>
        <div class="reply-info-box">
            <input type="text" class="input--text reply-writer" value="" readonly>
            <input type="text" class="input--text regdate" value="" readonly>
            <input type="hidden" class="reply-email" value="">
            <input type="hidden" class="reply-rid" value="">
        </div>
    </div>

    <div class="modal-footer">
        <div class="modal-button-box">
            <button class="button button-modify">수정</button>
            <button class="button button-delete">삭제</button>
            <button class="button button-cancel">취소</button>
        </div>
    </div>
</div>
<!-- Modal -->

<!-- Javascript -->
<script>
	window.loginId = '${id}';
	window.id = '${boardView.id}';
	window.isSub = ${isSub};
	window.isLike = ${isLike};
</script>
<script src="../../js/mymodal/modal.js"></script>
<script src="../../js/mypage/subModule.js"></script>
>>>>>>> refs/remotes/real/master
<script src="../../js/board/read.js"></script>
<script src="../../js/board/replyModule.js"></script>
<script src="../../js/ajax/ajax.js"></script>
</body>
</html>