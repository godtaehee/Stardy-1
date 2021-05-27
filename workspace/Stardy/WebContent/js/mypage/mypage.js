window.addEventListener("load", function(){

	//프로필 수정 버튼
    const profileModify = document.querySelector(".profile-modify");

	//글자 제한 박스
	const profileStatus = document.querySelector('.profile-status');
	const limitBox = document.querySelector('.limit');
	const textLimit = limitBox.querySelector('.text-limit');
	
    const uploadBox = document.querySelector('.upload-box');
    const btnUpload = document.querySelector('.button-upload');
    const inputFile = document.querySelector('input[type="file"]');
	
	//프로필 수정 버튼이 눌렸는가? true : false
    let isModify = false;

	profileStatus.addEventListener('keypress', function(e) {
		
		let len = profileStatus.value.length;
		textLimit.innerText = len > 0? len-1 : len;
	});

	//프로필 수정 버튼 Click
    profileModify.addEventListener("click", (e) => {
			
        if(!isModify){
			textLimit.innerText = profileStatus.value.length;
	
			profileModify.innerText = '저장';			
            isModify = true;
            profileStatus.readOnly = false;
            btnUpload.classList.toggle('hide');
			limitBox.classList.toggle('hide');
            /* 프로필 수정 이미지를 변경 */
            
        }
        else{
			profileModify.innerText = '프로필 수정';
            isModify = false;
            profileStatus.readOnly = true;
            btnUpload.classList.toggle('hide');
			limitBox.classList.toggle('hide');
            /* 프로필 수정 이미지를 변경 */

            /* ajax post 요청 -> 이미지 , 상태명 변경 */
			let data = {
				
				status: profileStatus.value
			};

			ajax({
				url: `/mypage/modify`,
				method: 'PUT',
				data: JSON.stringify(data),
				loadend: (result) => {
					console.log(result);
					console.log('프로필 변경 완료');
				},
				async: false,
				error: (xhr, status, text) => {
					console.log(status);
				}
			});
			
			window.location.reload();
        }
    });

//파일 업로드 관련 **----------------------------------------

    /* Drag & Drop */
    uploadBox.addEventListener('dragenter', function(e) {
		if(!isModify) return;
		
    });

    uploadBox.addEventListener('dragleave', function(e) {
		if(!isModify) return;
	
        uncheck();
    });

	uploadBox.addEventListener('dragover', function(e) {
		
        e.preventDefault();
		if(!isModify) return;

        /* 선택한 객체의 MIME 타입 */
        const data = e.dataTransfer.types;

        /* 드래그 해온 객체가 File이 아니면 false */
        let valid = data.indexOf('Files') >= 0;

        uploadBox.classList.add(valid? 'valid' : 'invalid');
    });

    uploadBox.addEventListener('drop', function(e) {
		
        e.preventDefault();
		if(!isModify) return;

        uncheck();
        
		const data = e.dataTransfer;
        
		//유효성 Check
		if(!isValid(data)) return;

		upload(data.files[0]);
    });
    
    inputFile.oninput = function(e) {

        const file = inputFile.files[0];

        upload(file);
    }

    /* File Event Trigger */
    btnUpload.addEventListener('click', function(e) {

        var event = new MouseEvent('click', { 
            view: window, 
            bubbles: true, 
            cancelable: true }
        );

        inputFile.dispatchEvent(event);
    });

	function uncheck(){
		uploadBox.classList.remove('valid');
		uploadBox.classList.remove('invalid');
	}
	
	function upload(file){
		
		const formData = new FormData();
		formData.append('uploadFile', file);
		
		ajax({
			url: '/mypage/upload',
			method: 'POST',
			data: formData,
			progress: () => {
				
			},
			loadend: () => {
				
			}
		});
	}
	
	function isValid(data){
		
		//파일인지 유효성 검사
		if(data.types.indexOf('Files') < 0)
			return false;
		
		//이미지인지 유효성 검사
		if(data.files[0].type.indexOf('image') < 0){
			alert('이미지 파일만 업로드 가능합니다.');
			return false;
		}
		
		//파일의 개수는 1개씩만 가능하도록 유효성 검사
		if(data.files.length > 1){
			alert('파일은 하나씩 전송이 가능합니다.');
			return false;
		}
		
		//파일의 사이즈는 50MB 미만
		if(data.files[0].size >= 1024 * 1024 * 50){
			alert('50MB 이상인 파일은 업로드할 수 없습니다.');
			return false;
		}
		
		return true;
	}

});