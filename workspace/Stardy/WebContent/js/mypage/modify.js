window.addEventListener("load", function(){

    var actionForm = document.querySelector(".modify-form");
    var btnModify = document.querySelector(".button-modify");
    var btnDropOut = document.querySelector(".button-drop-out");
	let msg = window.msg;
	
	if(msg === 'dupError'){
		alert('중복된 닉네임입니다. 다른 닉네임으로 다시 시도해주세요.');
	}

    btnModify.addEventListener("click", function(e) {
        e.preventDefault();

        /* 비밀번호 비어있는지 & check와 동일한지 확인 */
        var password = document.querySelector("input[name='password']").value;
        var check = document.querySelector("input[name='check']").value;

        if(password === check){
            alert("회원님의 정보 수정 요청이 완료되었습니다.");
            actionForm.submit();
        }
        else{
            document.querySelector("input[name='check']").className = 'input-item input-password-check input--text';
        }
        
    });

    btnDropOut.addEventListener("click", function(e) {

        e.preventDefault();

        if(confirm('정말 탈퇴하시겠습니까?')){
			actionForm.action = '/mypage/delete';
            actionForm.submit();

            /* 제출 후 메인 페이지로 Redirect */
        }
    })
})