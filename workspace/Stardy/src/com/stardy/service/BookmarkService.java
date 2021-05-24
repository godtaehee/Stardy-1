package com.stardy.service;

import java.util.List;

import com.stardy.entity.Board;
import com.stardy.entity.view.SubView;

public interface BookmarkService {
	boolean isSub(int memberId, int boardId);
	void add(int memberId, int boardId);
	void remove(int memberId, int boardId);
	void removeAll(int id);
	List<SubView> getSubs(int loginId);
	
	int getTotal(int memberId);//전체 즐겨찾기 개수

}
