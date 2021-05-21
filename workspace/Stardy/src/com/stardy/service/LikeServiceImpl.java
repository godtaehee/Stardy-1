package com.stardy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stardy.entity.Like;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;

public class LikeServiceImpl implements LikeService{

	Logger log = new Logger();
	
	/* 좋아요 등록 */
	public int register(Like like) {
		
		int result = 0;
		
		String sql = "INSERT INTO LIKES(MEMBER_ID, BOARD_ID) VALUES(?, ?)";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, like.getMemberId());
			ptst.setInt(2, like.getBoardId());
			
			result = ptst.executeUpdate();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 좋아요 해제 */
	public int cancel(Like like) {
		
		int result = 0;
		
		String sql = "DELETE FROM LIKES WHERE MEMBER_ID = ? AND BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, like.getMemberId());
			ptst.setInt(2, like.getBoardId());
			
			result = ptst.executeUpdate();

			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 좋아요 개수 */
	public int count(int id) {
		
		int result = 0;
		
		String sql = "SELECT COUNT(MEMBER_ID) CNT FROM LIKES GROUP BY BOARD_ID HAVING BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, id);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next())
				result = rs.getInt("CNT");

			log.info(id + "번 게시글의 좋아요 개수를 조회했습니다. : " + result + "개");

			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 좋아요 했는지 여부 */
	public boolean isLike(Like like) {
		
		boolean result = false;
		
		String sql = "SELECT MEMBER_ID FROM LIKES WHERE MEMBER_ID = ? AND BOARD_ID = ?";

		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, like.getMemberId());
			ptst.setInt(2, like.getBoardId());
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				result = true;

			}
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 특정 게시글의 모든 좋아요 삭제 */
	public void removeAll(int id) {
		
		String sql = "DELETE FROM LIKES WHERE BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, id);
			
			ptst.executeUpdate();
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
