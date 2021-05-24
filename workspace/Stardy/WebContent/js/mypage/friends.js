window.addEventListener("load", function(){

	var list = document.querySelector('.content-list');
	let originId = window.loginId;
	let statusVal = window.status;
	
	list.addEventListener("click", function(e) {
		
		let targetName = e.target.tagName;
		
		if(targetName === 'BUTTON'){
			
			if(confirm('친구를 삭제하시겠습니까?')){
				
				let targetId = e.target.dataset['tid'];
				friendModule.unfollow(targetId);
				showFriends(statusVal);
			}
			
		}	
	});
	
	function showFriends(flag){
		friendModule.showFriends(list, originId, flag);
	}
	
	showFriends(statusVal);
});