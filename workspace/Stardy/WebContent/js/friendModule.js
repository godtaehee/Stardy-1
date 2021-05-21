/**
 * 
 */
const friendModule = {
	follow: (targetId) => {
		
	},
	unfollow: (targetId) => {
		
		ajax({
			url: `/friends/${targetId}`,
			method: 'DELETE',
			loadend: (msg) => {
				if(msg === `success`){
					alert('친구 삭제가 완료되엇습니다.');
				}
			},
			load: () => {
				console.log('친구 삭제 요청 처리 완료');
			}
		});
		
	},
	showFriends: (element, originId, flag) => {
		element.innerHTML = '';
		
		ajax({
			url: `/friends/fr/${originId}`,
			method: 'GET',
			loadend: (jsonList) => {
				let list = JSON.parse(jsonList);
				let html = '';
				list.forEach((friend, idx) => {
					
					html += `<div class="friend-profile-box">
                                <div class="friend-profile-main">
                                	<button class="button delete-friend button-delete button-img ${flag == 'true'? 'show':'hide'}" data-tid="${friend.targetId}"></button>
                                    <img class="friend-icon" src="../img/11.png" alt="친구 프로필 아이콘">
                                </div>
                                <div class="friend-profile-sub">
                                    <p class="friend-name">${friend.targetNickname}</p>
                                </div>
                            </div>`;
				});
				element.insertAdjacentHTML('beforeend', html);
			}
		});
	},
	showFollowers: (element, originId) => {
		element.innerHTML = '';
		
		ajax({
			url: `/friends/fo/${originId}`,
			method: 'GET',
			loadend: (jsonList) => {
				let list = JSON.parse(jsonList);
				let html = '';
				list.forEach((friend, idx) => {
					
					html += `<div class="friend-profile-box">
                                <div class="friend-profile-main">
                                    <img class="friend-icon" src="../img/11.png" alt="친구 프로필 아이콘">
                                </div>
                                <div class="friend-profile-sub">
                                    <p class="friend-name">${friend.originNickname}</p>
                                </div>
                            </div>`;
				});
				element.insertAdjacentHTML('beforeend', html);
			}
		});
	} 
}