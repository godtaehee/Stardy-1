package com.stardy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stardy.entity.Reply;
import com.stardy.entity.view.ReplyView;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;

public class ReplyServiceImpl implements ReplyService{

	Logger log = new Logger();
	
	/* 댓글을 등록하는 메서드 */
	public void register(Reply reply) {
		
		String sql = "INSERT INTO REPLY(CONTENT, MEMBER_ID, BOARD_ID) VALUES(?, ?, ?)";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setString(1, reply.getContent());
			ptst.setInt(2, reply.getMemberId());
			ptst.setInt(3, reply.getBoardId());

			
			ptst.executeUpdate();
			log.info("[" + reply.getMemberId() + "] 님이 [" + reply.getBoardId() + "]번 게시글에 댓글을 작성했습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 댓글을 삭제하는 메서드 */
	public void remove(int id) {
		
		String sql = "DELETE FROM REPLY WHERE ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setInt(1, id);
			
			ptst.executeUpdate();
			log.info("[" + id +"] 번 댓글이 삭제되었습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 특정 게시글의 모든 댓글을 삭제하는 메서드 */
	public int removeAll(int boardId) {
		
		String sql = "DELETE FROM REPLY WHERE BOARD_ID = ?";
		int result = 0;
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setInt(1, boardId);
			
			result = ptst.executeUpdate();
			log.info("[" + boardId +"] 번 게시글의 댓글이 모두 삭제되었습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/* 댓글 목록을 가져오는 메서드 */
	public List<ReplyView> getList(int boardId, int page) {
		
		List<ReplyView> list = new ArrayList<>();
		String sql = "SELECT "
					+ "    RV.* "
					+ "FROM "
					+ "    (SELECT R.*, ROWNUM RN FROM REPLY_VIEW R WHERE R.BOARD_ID = ?) RV "
					+ "WHERE "
					+ "    RN > ? AND RN <= ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setInt(1, boardId);
			ptst.setInt(2, page * 5);
			ptst.setInt(3, (page * 5) + 5);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				String content = rs.getString("CONTENT");
				int memberId = rs.getInt("MEMBER_ID");
				Date regDate = rs.getDate("REGDATE");
				Date updateDate = rs.getDate("UPDATEDATE");
				String name = rs.getString("NAME");
				int id = rs.getInt("ID");
				
				ReplyView replyView = ReplyView.builder().name(name).id(id).boardId(boardId).content(content).regDate(regDate).updateDate(updateDate).memberId(memberId).build();
				list.add(replyView);
				log.info(replyView.toString());
			}
			
			log.info("[" + boardId +"] 번 게시글의 댓글들을 조회했습니다.");

			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* 댓글을 수정하는 메서드 */
	public int modify(int id, String content) {
		
		int result = 0;
		String sql = "UPDATE REPLY SET CONTENT = ? WHERE ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setString(1, content);
			ptst.setInt(2, id);
			result = ptst.executeUpdate();
			
			log.info("[" + id +"] 번 댓글을 수정했습니다.");

			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 특정 댓글을 가져오는 메서드 */
	public Reply get(int id) {
		Reply reply = null;
		
		String sql = "SELECT * FROM REPLY WHERE ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setInt(1, id);
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				String content = rs.getString("CONTENT");
				int memberId = rs.getInt("MEMBER_ID");
				String email = rs.getString("EMAIL");
				Date regDate = rs.getDate("REGDATE");
				int bid = rs.getInt("BID");
				
				reply = Reply.builder().content(content).id(id).memberId(memberId).regDate(regDate).build();
			}
			
			log.info("[" + id +"] 번 댓글을 조회했습니다.");
			log.info(reply.getContent());

			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reply;
	}
	
	/* 특정 게시글의 댓글 개수를 가져오는 메서드 */
	public int count(int boardId) {
		
		int result = 0;
		
		String sql = "SELECT COUNT(ID) CNT FROM REPLY GROUP BY BOARD_ID HAVING BOARD_ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, boardId);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next())
				result = rs.getInt("CNT");
			
			log.info(boardId + "번 게시글의 댓글 개수를 조회했습니다. : " + result + "개");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
