window.onload = (function(){
		
    const msg = window.msg;
	const modal = document.querySelector('.modal');
	const btnLogin = document.querySelector(".button-login");
    const email = document.querySelector("input[name='email']");
    const actionForm = document.querySelector("form");
	const btnConfirm = modal.querySelector('.button-confirm');
	let screen;
	
    //var msg = "${msg}";
    console.log(msg);
    
    if(msg === 'error'){
        document.querySelector(".login-error").className = 'login-error show';
        document.querySelector("input[name='password']").className = 'input-item input-password input--text';
    }
	else if(msg === 'success'){
		cover();
		modal.classList.toggle('hide');
		modal.classList.toggle('show');
	}
	
	btnConfirm.addEventListener('click', (e) => {
		discover();
		modal.classList.toggle('hide');
		modal.classList.toggle('show');
	});
    
    
    
    email.addEventListener("change", (e) => {
        
        if(isEmail(email.value))
            email.className = 'input-item input--text input-email';
        else
            email.className = 'input-item input--text';
    });
    
    function isEmail(asValue) {

        var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

        return regExp.test(asValue); // 형식에 맞는 경우 true 리턴	

    }

	function cover() {
		screen = document.createElement('div');
		
		screen.classList.add('screen');
		
		screen.style.opacity = '0.7';
		screen.style.height = '100%';
		screen.style.width = '100%';
		screen.style.background = '#e9e9e9';
		screen.style.position = 'absolute';
		screen.style.top = '0';
		screen.style.zIndex = '90';
	
		screen.addEventListener('click', (e) => {
			discover();
			modal.classList.toggle('hide');
			modal.classList.toggle('show');
		});
	
		document.querySelector('body').append(screen);
	}
	
	function discover() {
		
		screen.remove();
	}
});



