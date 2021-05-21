window.onload = (function(){
    
    const btnSignup = document.querySelector(".button-signup");
    const email = document.querySelector("input[name='email']");
    const actionForm = document.querySelector(".form-signup");

	console.dir(actionForm);

    btnSignup.addEventListener("click", (e) => {

        e.preventDefault();

        let password = document.querySelector("input[name='password']").value;
        let check = document.querySelector("input[name='check']").value;

        if(password === check && password != '')
            actionForm.submit();

        else{
            document.querySelector(".signup-error").className = 'signup-error show-ani';
            document.querySelector("input[name='check']").className = 'input-item input-password-check input--text';
        }
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

});