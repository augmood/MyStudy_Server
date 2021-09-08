package notice.model.server;

import java.sql.Connection;
import java.sql.SQLException;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

}
