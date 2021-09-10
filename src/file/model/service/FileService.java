package file.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import file.model.dao.FileDAO;
import file.model.vo.FileData;

public class FileService {

	private JDBCTemplate jdbcTemplate;
	
	public FileService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
	
	public int registerFileInfo(FileData fileData) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new FileDAO().insertFileInfo(conn, fileData);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}


	public List<FileData> printFileList(String userId) {
		Connection conn = null;
		List<FileData> fList = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			fList = new FileDAO().selectFileList(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return fList;
	}


	public int removeFile(String fileUser, String fileName) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new FileDAO().deleteFile(conn, fileUser, fileName);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

}
