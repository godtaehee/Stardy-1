const subModule = {
	
	add: (id, callback) => {
		ajax({
			url: `/sub/${id}`,
			method: 'POST',
			loadend: () => {
				
				if(callback)
					callback();
			}
		});
	},
	cancel: (id, callback) => {
		ajax({
			url: `/sub/${id}`,
			method: 'DELETE',
			loadend:() => {
				
				if(callback)
					callback();
			},
			load: () => {
				
			}
		});
	},
	showBookmarks: function(page, target) {
		
		target.innerHTML = '';

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
                                        <a href="/study/board/read?id=${board.id}">${board.title}</a>
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
				target.insertAdjacentHTML('beforeend', html);
			}
		});
		
	},
	createPager: function(c, target) {
		
		target.innerHTML = '';
		let html = '';
		
		for(let i=c.start; i<=c.curEnd; i++){
			
			let highlight = (c.page == i)? 'highlight' : '';  
			
			html += `<li><a href="#" class="pages ${highlight}">${i}</a></li>`;
			
		}
		target.insertAdjacentHTML('beforeend', html);
		
	},
	getTotal: function() {
		
		let data;
		
		 ajax({
			url: '/sub/total',
			method: 'GET',
			async: false,
			loadend: (result) => {
				console.log('success');
				const total = JSON.parse(result);
				data = total.total;
				//data = 80;
				
			},
			error: (xhr, status) => {
				console.log('Encoutered an error during take TOTAL => STATUS :' + status);
			}
		});
		
		return data;
	}
}