(() => {
	
	
	const completeBox = document.querySelector('.complete-make-study');
	
	completeBox.style.display = 'flex';
	
	completeBox.addEventListener('click', (e) => {
		
		if(e.target.className==='complete-make-study' || e.target.className==='cancel-btn')
			completeBox.remove();
	

	}); 
	
	console.log('gdgdgdgdgd emffdjdhk');

})();