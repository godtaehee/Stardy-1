package com.stardy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stardy.entity.Board;
import com.stardy.entity.view.SubView;
import com.stardy.util.Criteria;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;

public class BookmarkServiceImpl implements BookmarkService{

	Logger log = new  Logger();
	
	/* 즐겨찾기 했는지 여부 */
	public boolean isSub(int memberId, int boardId) {
		
		boolean result = false;
		
		String sql = "SELECT MEMBER_ID FROM SUB WHERE MEMBER_ID = ? AND BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, memberId);
			ptst.setInt(2, boardId);
			
			ResultSet rs = ptst.executeQuery();
			if(rs.next()) {
				result = true;
				System.out.println("[" + memberId + "] 님은 이미 [" + boardId + "]번 게시글을 즐겨찾기에 추가했습니다.");
			}
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 즐겨찾기 등록 */
	public void add(int memberId, int boardId) {
				
		String sql = "INSERT INTO SUB(MEMBER_ID, BOARD_ID) VALUES(?, ?)";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, memberId);
			ptst.setInt(2, boardId);
			
			ptst.executeUpdate();
			System.out.println("[" + memberId + "] 님이 [" + boardId + "]번 게시글을 즐겨찾기에 추가했습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 즐겨찾기 해제 */
	public void remove(int memberId, int boardId) {

		String sql = "DELETE FROM SUB WHERE MEMBER_ID = ? AND BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, memberId);
			ptst.setInt(2, boardId);
			
			ptst.executeUpdate();
			System.out.println("[" + memberId + "] 님이 [" + boardId + "]번 게시글을 즐겨찾기 취소했습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 특정 게시글의 모든 즐겨찾기 삭제 */
	public void removeAll(int id) {
		
		String sql = "DELETE FROM SUB WHERE BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, id);
			
			ptst.executeUpdate();
			log.info("[" + id + "]번 게시글의 즐겨찾기가 모두 삭제되었습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * SELECT BL.* 
		FROM (
		    SELECT B.ID, S.MEMBER_ID SUBSCRIBER FROM BOARD B 
		    LEFT JOIN SUB S ON S.BOARD_ID = B.ID
		    WHERE S.MEMBER_ID = 5
		) SUBS 
		LEFT JOIN BOARD_LIST BL ON SUBS.ID = BL.ID;
	 */
	
	/* 특정 유저의 즐겨찾기 목록 가져오는 메서드 */
	public List<SubView> getSubs(int loginId) {
		
		return this.getSubs(loginId, 1);
	}
	public List<SubView> getSubs(int loginId, int page) {
		
		List<SubView> list = new ArrayList<>();
		
		String sql = "SELECT RES.* FROM "
					+ "( "
					+ "    SELECT "
					+ "        SUB.*, ROWNUM RN "
					+ "    FROM ( "
					+ "        SELECT "
					+ "            BV.ID, BV.TITLE, BV.STUDY_ID, BV.NAME, BV.REGDATE, BV.UPDATEDATE "
					+ "        FROM "
					+ "            SUB S "
					+ "            LEFT JOIN BOARD_VIEW BV ON S.BOARD_ID = BV.ID "
					+ "        WHERE S.MEMBER_ID = ? "
					+ "        ORDER BY BV.ID DESC "
					+ "        ) SUB "
					+ ") RES "
					+ "WHERE RN > ? AND RN <= ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, loginId);
			ptst.setInt(2, ((page-1) * 10));
			ptst.setInt(3, ((page-1) * 10) + 10);
			
			ResultSet rs = ptst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String name = rs.getString("name");
				Date regDate = rs.getDate("REGDATE");
				Date updateDate = rs.getDate("UPDATEDATE");
				
				SubView subView = SubView.builder().id(id).regDate(regDate).name(name).updateDate(updateDate).title(title).build();
				
				list.add(subView);
				log.info(subView.toString());
			}
			
			log.info(loginId + "님의 즐겨찾기 목록을 조회했습니다.");
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getCount(int memberId) {
		
		String sql = "SELECT COUNT(BOARD_ID) CNT FROM SUB WHERE MEMBER_ID = ?";
		int count = -1;
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, memberId);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) 
				count = rs.getInt("CNT");			
			
			
			log.info(memberId + "님의 즐겨찾기 개수를 조회했습니다. : " + count);
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
}
