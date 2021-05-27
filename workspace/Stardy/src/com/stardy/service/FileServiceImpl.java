package com.stardy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stardy.entity.Files;
import com.stardy.util.DatabaseUtil;

public class FileServiceImpl implements FileService {

	@Override
	public void addFile(Files file) {
		
		String sql = "INSERT INTO FILES(ID, NAME, PATH, BOARD_SEQ.CURRVAL) VALUES (?, ?, ?, ?)";
		
		try (
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			)
		{	
			pstmt.setString(1, file.getUuid());
	        pstmt.setString(2, file.getName());
	        pstmt.setString(3, file.getPath());
	        pstmt.setInt(4, file.getBoardId());

	        pstmt.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
}
