window.addEventListener("load", function() {

	const bid = window.bid;
	const btnDelete = document.querySelector('.button-delete');
	const btnSave = document.querySelector('.button-modify');
	const actionForm = document.querySelector('#action-form')
	   
	btnDelete.addEventListener('click', (e) => {
		
		if(!confirm('게시글을 삭제하시겠습니까?')) return;
		
		actionForm.innerHTML = '';
		actionForm.action = '/board/delete';
		actionForm.insertAdjacentHTML('beforeend', `<input type="hidden" name="bid" value="${bid}">`);
		actionForm.submit();
	});
	
	btnSave.addEventListener("click", (e) => {
		e.preventDefault();
		actionForm.submit();
	});
});