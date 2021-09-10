package file.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import file.model.vo.FileData;

public class FileDAO {

	public int insertFileInfo(Connection conn, FileData fileData) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO FILESTORAGE VALUES (?, ?, ?, ?, ?) ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileData.getFileName());
			pstmt.setString(2, fileData.getFilePath());
			pstmt.setLong(3, fileData.getFileSize());
			pstmt.setString(4, fileData.getFileUser());
			pstmt.setTimestamp(5, fileData.getUploadTime());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			}
		
		return result;
	}

	public List<FileData> selectFileList(Connection conn, String studentId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<FileData> fList = null;
		String query = "SELECT * FROM FILESTORAGE WHERE FILE_USER = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentId);
			// 빼먹지 마세요
			rset = pstmt.executeQuery();
			fList = new ArrayList<FileData>();
			while(rset.next()) {
				FileData fileData = new FileData();
				fileData.setFileName(rset.getString("FILE_NAME"));
				fileData.setFilePath(rset.getString("FILE_PATH"));
				fileData.setFileSize(rset.getLong("FILE_SIZE"));
				fileData.setFileUser(rset.getString("FILE_USER"));
				fileData.setUploadTime(rset.getTimestamp("UPLOAD_TIME"));
				fList.add(fileData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return fList;
	}

	public int deleteFile(Connection conn, String fileUser, String fileName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="DELETE FROM FILESTORAGE WHERE FILE_USER = ? and FILE_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileUser);
			pstmt.setString(2, fileName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
