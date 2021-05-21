
const subModule = {
	page: 1,
	cancel: (bid) => {
		ajax({
			url: `/sub/${bid}`,
			method: 'DELETE',
			loadend:() => {
				console.log(`DELETE /sub/${bid}`);
			    /* 즐겨찾기 해제 */
				alert(`${bid}번 게시글 즐겨찾기가 취소되었습니다.`);
				
			},
			load: () => {
				
			}
		});
	},
	showBookmarks: (p) => {
		
	}
}