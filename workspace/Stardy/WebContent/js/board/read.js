window.addEventListener("load", function() {

	let id = window.id;
	let isSub = window.isSub;
	let isLike = window.isLike;

	console.log('isLike : ' + isLike);

    let replyList = document.querySelector('.reply-list');		
    let moreBox = document.querySelector('.more-box');
	let btnRegister = document.querySelector(".button-register");
	let bookmark = document.querySelector('.bookmark');
	let btnLike = document.querySelector(".button-like");
	let replyContent = document.querySelector('textarea[name="reply-content"]');
	let limitBox = document.querySelector('.limit');
	let textLimit = limitBox.querySelector('.text-limit');
	
	/* 댓글 새로 작성 시 글자제한 */
	replyContent.addEventListener('keydown', (e) => {
		textLimit.innerText = replyContent.value.length;
	});
	
	
/*--- 댓글 --- */

	/* 댓글 개수 조회 */
	//replyModule.getReplyCount();
	/* 댓글 목록 조회 */
	replyModule.showList();
	
	/* 댓글 등록 버튼 Click */
	btnRegister.addEventListener("click", (e) => {
		
		let content = document.querySelector('textarea[name="reply-content"]');
		let reply = {
			content: content.value,
			id: id //board_id
		};
		
		replyModule.addReply(reply);
		content.value = '';
		textLimit.innerText = '0';
	});
	
	/* 특정 댓글 선택 시 댓글 모달창 show */
    replyList.addEventListener('click', function(e) {        
        let target = e.target;

		if(target.className == 'reply-list') return;

        openModal(target);
    });
    

    /* 댓글 더 보기 버튼 Click*/
    moreBox.addEventListener('click', function(e) {

		replyModule.showList();
    });
/*--- 댓글 --- */

    /* 즐겨찾기 버튼 Click */
	bookmark.addEventListener('click', function(e) {
	
	    this.classList.toggle('icon-bookmark-off');
	    this.classList.toggle('icon-bookmark');
	
		if(!isSub)
			subModule.add(id, () => {
				console.log(`POST /sub/${id}`);
				/* 즐겨찾기 추가 */
				isSub = !isSub;
	        	alert(`${id}번 게시글 즐겨찾기가 추가되었습니다.`);
			});
		else
			subModule.cancel(id, () => {
				console.log(`DELETE /sub/${id}`);
			    /* 즐겨찾기 해제 */
		    	isSub = !isSub;
				alert(`${id}번 게시글 즐겨찾기가 취소되었습니다.`);
			});
	});
/* 좋아요 */
	btnLike.addEventListener('click', function(e) {
		
		this.classList.toggle('like');
	    this.classList.toggle('unlike');
	
		if(!isLike)
			ajax({
				url: `/likes/reg/${id}`,
				method: 'POST',
				loadend: () => {
					console.log(`POST /likes/reg/${id}`);
					/* 좋아요 추가 */
					isLike = !isLike;
		        	console.log(`${id}번 게시글을 좋아합니다.`);

					getLikes();
				}
			});
		else
			ajax({
				url: `/likes/rm/${id}`,
				method: 'DELETE',
				loadend:() => {
					console.log(`DELETE /likes/rm/${id}`);
				    /* 좋아요 해제 */
					isLike = !isLike;
					console.log(`${id}번 게시글의 좋아요를 취소했습니다.`);
					getLikes();
				}
			});
	});
	/* 좋아요 개수 */
	function getLikes() {
		
		let likes = document.querySelector('.likes');
		
		ajax({
			url: `/likes/${id}`,
			method: 'GET',
			loadend: (count) => {
				console.log('좋아요 개수 : ' + count);
				likes.innerHTML = count;
			}
		});
	}
	//getLikes();
/* 좋아요 */
});