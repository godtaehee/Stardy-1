package com.stardy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stardy.entity.Friend;
import com.stardy.entity.Like;
import com.stardy.entity.view.FollowerView;
import com.stardy.entity.view.FollowingView;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;

public class FriendServiceImpl implements FriendService{

	Logger log = new Logger();
	
	/* 내가 팔로우 목록 조회 (OrginId -> TargetId) */
	public List<FollowingView> getFriends(int memberId) {
		
		List<FollowingView> list = new ArrayList<>();
		
		String sql = "SELECT FV.* FROM FOLLOWING_VIEW FV WHERE FV.ORIGIN_ID = ? ORDER BY FV.TARGET_ID DESC";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, memberId);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				int originId = rs.getInt("ORIGIN_ID");
				int targetId = rs.getInt("TARGET_ID");
				String originNickname = rs.getString("ORIGIN_NICKNAME");
				String targetNickname = rs.getString("TARGET_NICKNAME");
				
				FollowingView following = FollowingView.builder().targetId(targetId).targetNickname(targetNickname).originId(originId).originNickname(originNickname).build();
				list.add(following);
				log.info(following.toString());
			}
			
			log.info(memberId + "님이 친구목록을 조회했습니다.");
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/* 나를 팔로우한 목록 조회 (TargetId -> OrginId) */
	public List<FollowerView> getFollowers(int memberId) {
		
		List<FollowerView> list = new ArrayList<>();
		
		String sql = "SELECT FV.* FROM FOLLOWER_VIEW FV WHERE FV.TARGET_ID = ? ORDER BY FV.ORIGIN_ID DESC";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, memberId);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				
				int originId = rs.getInt("ORIGIN_ID"); //상대방
				int targetId = rs.getInt("TARGET_ID"); //나
				String originNickname = rs.getString("ORIGIN_NICKNAME");
				String targetNickname = rs.getString("TARGET_NICKNAME");
				
				FollowerView follower = FollowerView.builder().originId(originId).originNickname(originNickname).targetId(targetId).targetNickname(targetNickname).build();
				
				list.add(follower);
				log.info(follower.toString());
			}
			
			log.info(memberId + "님을 팔로우한 목록을 조회했습니다.");
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* 친구 삭제 */
	public int unfollow(int originId, int targetId) {
		
		int result = 0;
		
		String sql = "DELETE FROM FRIEND WHERE ORIGIN_ID = ? AND TARGET_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, originId);
			ptst.setInt(2, targetId);
			
			result = ptst.executeUpdate();
			
			log.info(originId + "님이 " + targetId + "님과 친구를 해제했습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/* 친구 등록 */
	public int follow(int originId, int targetId) {
		
		int result = 0;
		
		String sql = "INSERT INTO FRIEND(ORIGIN_ID, TARGET_ID) VALUES(?, ?)";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, originId);
			ptst.setInt(2, targetId);
			
			result = ptst.executeUpdate();
			
			log.info(originId + "님이 " + targetId + "님과 친구를 등록했습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}