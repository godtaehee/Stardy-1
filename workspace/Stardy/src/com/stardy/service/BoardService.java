package com.stardy.service;

import java.util.List;

import com.stardy.entity.Board;
import com.stardy.entity.Files;
import com.stardy.entity.view.BoardListContent;
import com.stardy.entity.view.BoardView;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;


public interface BoardService {
	
	String getWriters(int bid);
	/* 특정 스터디의 게시글 목록 */
	List<BoardListContent> getList(int sid);
	
	/* 다음 글 BID 가져오기 */
	 int getNext(int id, int studyId);
	
	/* 이전 글 BID 가져오기 */
	 int getPrev(int id, int studyId);
	
	/* 게시글 조회 */
	 BoardView read(int id);
	
	/* 게시글 수정 */
	 int modify(BoardView board);
	
	/* 게시글 삭제 */
	 int delete(int id);
	 
	 /* 게시글 작성 */
	 int write(Board board, List<Files> file);
}
