package com.stardy.service;

import com.stardy.entity.Reply;
import com.stardy.entity.view.ReplyView;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;
import java.util.List;


public interface ReplyService {

	/* 댓글을 등록하는 메서드 */
	 void register(Reply reply);
	
	/* 댓글을 삭제하는 메서드 */
	 void remove(int id);
	
	/* 특정 게시글의 모든 댓글을 삭제하는 메서드 */
	 int removeAll(int boardId);
	
	/* 댓글 목록을 가져오는 메서드 */
	 List<ReplyView> getList(int boardId, int page);
	
	/* 댓글을 수정하는 메서드 */
	 int modify(int id, String content);
	
	/* 특정 댓글을 가져오는 메서드 */
	 Reply get(int id);
	
	/* 특정 게시글의 댓글 개수를 가져오는 메서드 */
	 int count(int boardId);

}
