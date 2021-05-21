window.addEventListener("load", function(){

    var bookmarks = document.querySelector(".bookmarks");
	const pagerList = document.querySelector('#pager-list');
	const pageVal = parseInt(window.pageVal);

	/* 즐겨찾기 해제 */
    bookmarks.addEventListener("click", function(e) {
		let target = e.target;
        let targetName = target.tagName;

        if(targetName !== 'SPAN') return;

        if(confirm('즐겨찾기를 해제하시겠습니까?')){

			let bid = target.dataset.bid;
			
            /* ajax -> post */
			subModule.cancel(bid);
			subModule.showBookmarks();
        }
    });
	
	/* 즐겨찾기 목록 보여주는 함수 */
	function showBookmarks(p) {
		
		bookmarks.innerHTML = '';
		let page = 1;
		
		if(page)
			page = p;
		
		ajax({
			url: `/sub/${page}`,
			method: 'GET',
			loadend: (json) => {
				let boards = JSON.parse(json);
				
				let html = '';
				boards.forEach((board, idx) => {
				
					//console.log(board);
					html += `
							<div class="bookmark-box">
                                <div class="bookmark-main">
                                    <div class="content-box text-no-over">
                                        <a href="/board/read.jsp?id=${board.id}">${board.title}</a>
                                    </div>
                                    <span class="icon-bookmark delete-bm" data-bid="${board.id}"></span>
                                </div>
                                <div class="bookmark-sub">
                                    <div class="writer-box">
                                        ${board.name}
                                    </div>
                                    <div class="date-box">
                                        ${board.regDate}
                                    </div>
                                </div>
                            </div>`;
					
				});
				bookmarks.insertAdjacentHTML('beforeend', html);
			}
		});
	}
	showBookmarks(pageVal);
	
	/* Pager 클릭 시 즐겨찾기 페이징 */
	let selected = pagerList.querySelector('.highlight');
	pagerList.addEventListener('click', function(e) {
		e.preventDefault();
		
		if(e.target.tagName !== 'A') return;
		
		selected.classList.toggle('highlight');
		selected = e.target;
		selected.classList.toggle('highlight');
		
		const page = e.target.innerText;
		
		showBookmarks(page);
	});
	
});