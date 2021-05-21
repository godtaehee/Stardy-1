package com.stardy.service;


import java.util.List;

import com.stardy.entity.Friend;
import com.stardy.entity.view.FollowerView;
import com.stardy.entity.view.FollowingView;

 interface FriendService {
	 List<FollowingView> getFriends(int memberId);
	 List<FollowerView> getFollowers(int memberId);

	 int unfollow(int originId, int targetId);
	 int follow(int originId, int targetId);
}
