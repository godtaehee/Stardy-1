package com.stardy.service;


import com.stardy.entity.Like;

public interface LikeService {
	int register(Like like);
	int cancel(Like like);
	int count(int id);
	boolean isLike(Like like);
	void removeAll(int id);

}

