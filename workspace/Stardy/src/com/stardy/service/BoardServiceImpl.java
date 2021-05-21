package com.stardy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stardy.entity.Board;
<<<<<<< HEAD
import com.stardy.entity.Member;
import com.stardy.entity.view.BoardView;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.Logger;

public class BoardServiceImpl implements BoardService{

	Logger log = new Logger();
	
	public String getWriters(int bid) {
		
		Connection con = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String nickName = "";

		String sql = "SELECT NICKNAME FROM MEMBER WHERE ID="+bid;
		
		try {
			con = DatabaseUtil.getConnection();
			ptst = con.prepareStatement(sql);
			
			rs = ptst.executeQuery();
			
			if(rs.next())
				nickName = rs.getString("NICKNAME");
			
			ptst.close();
			con.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nickName;

	}
	/* 게시글의 좋아요 증가 */
//	public int incLike(int bid) {
//		
//		String sql = "UPDATE BOARD SET LIKES = LIKES + 1 WHERE BID = ?";
//		int result = 0;
//		
//		try {
//			Connection con = DatabaseUtil.getConnection();
//			PreparedStatement ptst = con.prepareStatement(sql);
//						
//			ptst.setInt(1, bid);
//			
//			result = ptst.executeUpdate();
//			log.info("[" + bid +"] 번 게시글에 좋아요가 증가되었습니다.");
//			
//			ptst.close();
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	/* 게시글의 좋아요 감소 */
//	public int decLike(int bid) {
//		
//		String sql = "UPDATE BOARD SET LIKES = LIKES - 1 WHERE BID = ?";
//		int result = 0;
//		
//		try {
//			Connection con = DatabaseUtil.getConnection();
//			PreparedStatement ptst = con.prepareStatement(sql);
//						
//			ptst.setInt(1, bid);
//			
//			result = ptst.executeUpdate();
//			log.info("[" + bid +"] 번 게시글에 좋아요가 감소되었습니다.");
//			
//			ptst.close();
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	/* 특정 스터디의 게시글 목록 */
	public List<BoardListContent> getList(int sid) {
		
		List<BoardListContent> list = new ArrayList<BoardListContent>();
		
		String sql = "SELECT * FROM BOARD_LIST_CONTENT WHERE STUDY_ID = ?";
		
		try {
			
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, sid);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date regDate = rs.getDate("REGDATE");
				int memberId = rs.getInt("MEMBER_ID");
				int studyId = rs.getInt("STUDY_ID");
				Date updateDate = rs.getDate("UPDATEDATE");
				int likes = rs.getInt("LIKES");
				String name = rs.getString("NAME");
				int replyCnt = rs.getInt("REPLYCNT");


				BoardListContent board = new BoardListContent(id, title, content, regDate, memberId, studyId, updateDate, likes, name, replyCnt);
				list.add(board);
			}
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* 다음 글 BID 가져오기 */
	public int getNext(int id, int studyId) {
		
		String sql = "SELECT ID FROM BOARD WHERE ID IN (SELECT MIN(ID) FROM BOARD WHERE ID > ?) AND STUDY_ID = ?";
		int next = -1;
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, id);
			ptst.setInt(2, studyId);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				next = rs.getInt("ID");
			}
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return next;
	}
	
	/* 이전 글 BID 가져오기 */
	public int getPrev(int id, int studyId) {
		
		String sql = "SELECT ID FROM BOARD WHERE ID IN (SELECT MAX(ID) FROM BOARD WHERE ID < ?) AND STUDY_ID = ?";
		int prev = -1;
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, id);
			ptst.setInt(2, studyId);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				prev = rs.getInt("ID");
			}
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prev;
	}
	
	/* 게시글 조회 */
	public BoardView read(int id) {
		
		BoardView boardView = null;
		
		String sql = "SELECT * FROM BOARD_VIEW_CONTENT WHERE ID = ?";
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
			
			ptst.setInt(1, id);
			
			ResultSet rs = ptst.executeQuery();
			
			while(rs.next()) {
				int memberId = rs.getInt("MEMBER_ID");
				int studyId = rs.getInt("STUDY_ID");
				int likes = rs.getInt("LIKES");
				int replyCnt = rs.getInt("REPLYCNT");
				
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String name = rs.getString("NAME");
				
				Date regDate = rs.getDate("REGDATE");
				Date updateDate = rs.getDate("UPDATEDATE");
				
				
				boardView = BoardView.builder()
						.content(content)
						.title(title)
						.id(id)
						.studyId(studyId)
						.memberId(memberId)
						.regDate(regDate)
						.updateDate(updateDate)
						.name(name)
						.likes(likes)
						.replyCnt(replyCnt).build();
				//파일은 추후
			}
			log.info(boardView.toString());
			
			rs.close();
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardView;
	}
	
	/* 게시글 수정 */
	public int modify(BoardView board) {
		
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE ID = ?";
		int result = 0;
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setString(1, board.getTitle());
			ptst.setString(2, board.getContent());
			ptst.setInt(3, board.getId());
			
			result = ptst.executeUpdate();
			log.info("[" + board.getId() +"] 번 게시글이 수정되었습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 게시글 삭제 */
	public int delete(int id) {
		
		String sql = "DELETE FROM BOARD WHERE ID = ?";
		int result = 0;
		
		try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ptst = con.prepareStatement(sql);
						
			ptst.setInt(1, id);
			
			result = ptst.executeUpdate();
			log.info("[" + id +"] 번 게시글이 삭제되었습니다.");
			
			ptst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}

