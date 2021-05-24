window.addEventListener("load", function(){

    const bookmarks = document.querySelector(".bookmarks");
	const pagerList = document.querySelector('#pager-list');
	const btnNext = document.querySelector('.next');
	const btnPrev = document.querySelector('.prev');
	/*
	page: 현재 페이지
	count: 보여줄 개수(default 6)
	total: 전체 데이터 개수
	start: 현재 페이지 기준 시작 번호
	tempEnd: start + 5
	realEnd: 전체 데이터 개수 기반 끝 번호
	curEnd: 최종적으로 가질 끝 번호
	*/
	const criteria = {
		page: 1, 
		count: 6,
		total: 0,
		start: 0,
		tempEnd: 0,
		realEnd: 0,
		curEnd: 0,
		isNext: false,
		isPrev: false,
		setCriteria: function() {
			
			this.start = (this.page / 5) * 5;
			this.tempEnd = this.start + 4;
			this.realEnd = Math.floor(this.total / this.count) + Math.ceil(this.total % this.count / this.count);
			this.curEnd = this.tempEnd < this.realEnd? this.tempEnd : this.realEnd;
			
			this.isPrev = this.start > 1;
			this.isNext = this.curEnd < this.realEnd;
		}
	};
	
	/* 즐겨찾기 목록 관련 초기 호출 */
	criteria.total = subModule.getTotal();
	let highlight = null;
	init();
	
	/* Pager [1, 2, 3, 4, 5] 클릭 시 즐겨찾기 페이징 */
	pagerList.addEventListener('click', function(e) {
		e.preventDefault();
		
		if(e.target.tagName !== 'A') return;
		
		if(highlight == null)
			highlight = document.querySelector('.highlight');
		
		highlight.classList.toggle('highlight');
		highlight = e.target;
		highlight.classList.toggle('highlight');
		
		const page = parseInt(e.target.innerText);
		
		criteria.page = page;

		subModule.showBookmarks(criteria.page, bookmarks);
		console.log(criteria);
	});
	
	/* Next Button Click */
	btnNext.addEventListener('click', function() {
		
		//Next 버튼 클릭 시, 현재 페이지는 끝번호 + 1
		criteria.page = criteria.curEnd + 1;
		init();
	});
	/* Prev Button Click */
	btnPrev.addEventListener('click', function() {
		
		//Prev 버튼 클릭 시, 현재 페이지는 앞번호 - 5
		criteria.page = criteria.start - 5;
		init();
	});
	
	/*
	즐겨찾기가 해제되거나 전체 데이터 개수가 변경되어 
	페이저와 next, prev 버튼을 최신화 하는 함수.
	 */
	function init() {
		highlight = null;
		
		criteria.setCriteria();
		subModule.showBookmarks(criteria.page, bookmarks);
		subModule.createPager(criteria, pagerList);
		
		if(criteria.isNext){
			btnNext.classList.add('show');
			btnNext.classList.remove('hide');
		}
		else{
			btnNext.classList.remove('show');
			btnNext.classList.add('hide');
		}
		
		if(criteria.isPrev){
			btnPrev.classList.add('show');
			btnPrev.classList.remove('hide');
		}
		else{
			btnPrev.classList.remove('show');
			btnPrev.classList.add('hide');
		}
	};
	
	/* 즐겨찾기 해제 */
    bookmarks.addEventListener("click", function(e) {
		let target = e.target;
        let targetName = target.tagName;

        if(targetName !== 'SPAN') return;

        if(confirm('즐겨찾기를 해제하시겠습니까?')){

			let bid = target.dataset.bid;
			
            /* ajax -> post */
			subModule.cancel(bid, () => {
				
				console.log(`DELETE /sub/${bid}`);
				alert(`${bid}번 게시글 즐겨찾기가 취소되었습니다.`);
				
				criteria.total = subModule.getTotal();
				criteria.page = 1;
				init();
			});
        }
    });

});