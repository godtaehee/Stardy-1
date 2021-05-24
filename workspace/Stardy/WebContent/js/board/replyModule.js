var moreBox = document.querySelector('.more-box');
var replyList = document.querySelector('.reply-list');
var replyCount = document.querySelector('.reply-count');

var replyModule = {
	page: 0,
	showList: function() {
		
		var p = this.page;
		this.page++;
		
		ajax({
			url: `/replies/${id}/${p}`,
			method: 'GET',
			loadend: (result) => {
				//debug
				//console.dir(result);
			
				var parentNode = replyList;
				var replies = JSON.parse(result);
				
				if(replies.length == 0){
					console.log('empty');
					moreBox.classList.add('hide');
				}
				
				var html = '';
				replies.forEach((reply, idx) => {
					html += `<div class="replies" data-id="${reply.id}" data-memberId="${reply.memberId}">
								<div>
				                    <p class="reply">${reply.content}</p>
				                    <span class="span reply-writer">${reply.name}</span>
				                    <span class="span">/</span>
				                    <span class="span regdate">${reply.regDate}</span>
				                </div>
							</div>`;
				});
				
				parentNode.insertAdjacentHTML('beforeend', html);
			},
			progress: () => {
				console.log('댓글 요청이 진행 중 입니다.');
			},
			load: () => {
				console.log('댓글 요청이 완료되었습니다.');
				discover();
			},
			abort: () => {
				console.log('댓글 요청이 중지되었습니다.');
				discover();
			},
			loadstart: (xhr) => {
				console.log('댓글 요청이 시작되었습니다.');
				cover(replyList.parentElement, xhr);
			},
			error: (xhr, status, text) => {
				console.log('댓글 에러가 발생 되었습니다.');
				console.log(status + ' , ' + text);
			}
		});
	},
	addReply: function(reply) {
		
		ajax({
			url: '/replies/add',
			method: 'POST',
			contentType: 'application/json; charset=UTF-8',
			data: JSON.stringify(reply),
			loadend: (result) => {
				
				//debug
				console.log(result);
				console.log(reply);
				console.log('Registed an reply');
			},
			load: () => {
				
				this.getReplyCount();
				this.initList();
			},
			error: (xhr, status, statusText) => {
				console.log('Encountered an Error during sending data');
				console.log(status);
			}
		});
	},
	removeReply: function(rid) {
		
		ajax({
			url: `/replies/${rid}`,
			method: 'DELETE',
			loadend: (result) => {
				console.log(id + '번 댓글을 삭제했습니다.');
				
				this.getReplyCount();
				this.initList();
			}
		});
	},
	modifyReply: function(reply) {
		
		console.log(reply);
		
		ajax({
			url: `/replies/${reply.id}`,
			method: `PUT`,
			data: JSON.stringify(reply),
			loadend: (result) => {
				
				//debug
				console.log(`${reply.id}번 댓글을 수정했습니다.`);
				console.log('result : ' + result);
				
				this.initList();
			}
		});
	},
	initList: function() {
		replyList.innerHTML = '';
		moreBox.classList.remove('hide');
		this.page = 0;
		this.showList();
	},
	getReplyCount: () => {
		
		ajax({
			url: `/replies/get/${id}`,
			method: 'GET',
			loadend: (count) => {
				console.log('댓글의 개수 : ' + count);
				
				replyCount.innerHTML = count;
			}
		});
	}
};

/* ------------------- */

var screen;
function cover(target, xhr) {
	screen = document.createElement('div');
	
	screen.classList.add('screen');
	
	screen.style.opacity = '0.7';
	screen.style.height = '100%';
	screen.style.width = '100%';
	screen.style.background = '#e9e9e9 url("../../img/ajax-loader.gif") no-repeat center';
	screen.style.position = 'absolute';
	screen.style.top = '0';
	screen.style.zIndex = '100';
	
	var btnClose = document.createElement('button');
	btnClose.style.height = '20px';
	btnClose.style.width = '20px';
	btnClose.style.position = 'absolute';
	btnClose.style.right = '20px';
	btnClose.style.top = '20px';
	btnClose.style.border = 'none';
	btnClose.style.background = 'url("../../img/icon-close.svg") no-repeat center';
	btnClose.style.cursor = 'pointer';

	btnClose.addEventListener('click', function (e) {
		console.log('click');
		xhr.abort();
	});

	screen.append(btnClose);
	target.append(screen);
}
function discover() {
	
	screen.remove();
}