window.addEventListener("load", function(){

	let list = document.querySelector('.content-list');
	let originId = window.loginId;
	
	function showFollowers(){
		friendModule.showFollowers(list, originId);
	}
	
	showFollowers();
});