package notice.model.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	
	private JDBCTemplate jdbcTemplate;
	public NoticeService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}

	public int registerNotice(Notice notice) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new NoticeDAO().insertNotice(conn, notice);
			if(result > 0) {
				// commit
				JDBCTemplate.commit(conn);
			}else {
				// rollback
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

	public PageData printAllNotice(int currentPage) {
//		List<Notice> nList = null;
//		String pageNavi = null;
		PageData pd = new PageData();
		Connection conn = null;
		NoticeDAO nDAO = new NoticeDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			List<Notice> nList = new NoticeDAO().selectAllNotice(conn, currentPage);
			String PageNavi = nDAO.getPageNavi(conn,currentPage);
			pd.setNoticeList(nDAO.selectAllNotice(conn, currentPage));
			pd.setPageNavi(nDAO.getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

	public Notice printOneByNo(int noticeNo) {
		Notice noticeOne = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			noticeOne = new NoticeDAO().selectOneByNo(conn, noticeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return noticeOne;
	}

	public int removeNotice(int noticeNo) {
		// 서비스의 역할?
		// 1. 연결해주기
		// 2. 커밋 롤백을 한다.
		
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			result = new NoticeDAO().deleteNotice(conn, noticeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	public int modifyNotice(Notice notice) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new NoticeDAO().updateNotice(conn, notice);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public PageData printSearchNotice(String searchKeyword, int currentPage) {
		Connection conn = null;
		List<Notice> nList = null;
		String searchPageNavi = null;
		PageData pd = new PageData();
		NoticeDAO nDAO = new NoticeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			nList = nDAO.selectSearchNotice(conn, searchKeyword, currentPage);
			searchPageNavi = nDAO.getSearchPageNavi(conn, searchKeyword, currentPage);
			pd.setNoticeList(nList);
			pd.setPageNavi(searchPageNavi);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	

}
