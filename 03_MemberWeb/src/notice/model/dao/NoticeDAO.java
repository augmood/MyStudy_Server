package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import notice.model.vo.Notice;

public class NoticeDAO {

	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE VALUES(SEQ_NOTICE.NEXTVAL,?,?,?, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContents());
			pstmt.setString(3, notice.getWriterId());
			//  쿼리문 실행 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public List<Notice> selectAllNotice(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> nList = null;
		String query ="SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_NO, NOTICE_SUBJECT, NOTICE_CONTENTS, WRITER_ID, REG_DATE FROM NOTICE) WHERE NUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query); //연결을 통해  statement 객체 생성
			// 1. 1-> 10
			// 2. 11 -> 20
			// 3. 21 -> 30 ..
			int viewCountPerPage = 10; // 페이지마다 10개씩 보여준다.
			int start = currentPage * viewCountPerPage - (viewCountPerPage-1);
			int end = currentPage * viewCountPerPage;
			// 1을 누르면 1*10 = 10페이지 까지 보여준다는 소리
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery(); // 쿼리문 실행 후 rset으로 결과값 받기
			nList = new ArrayList<Notice>();
			while(rset.next()) { // rset에 데이터가 없을때까지 반복
				Notice notice = new Notice(); //notice 객체를 이용해서 list에 데이터 담기
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				notice.setNoticeContents(rset.getString("NOTICE_CONTENTS"));
				notice.setWriterId(rset.getString("WRITER_ID"));
				notice.setRegDate(rset.getDate("REG_DATE"));
				nList.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return nList;
	}
	
	// 리스트 하단의 페이지 넘기는 네비게이션을 만들기
	public String getPageNavi(Connection conn, int currentPage) {
		// 1 2 3 4 5 
	
		
		// 1 페이지 당 10개씩 보여주는데 125개의 게시물
		// 총 13페이지가 필요함
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12 13
		
		// 한 뷰마다 보이는 페이지의 카운트
		int pageCountPerView = 5;
		
		int viewTotalCount = totalCount(conn);
		int viewCountPerPage = 10;
		int pageTotalCount = 0;
		// 나누면 12가 되기 때문에 페이지 수를 맞추려면 +1필요함
		// 125 / 10 -> 12.5
		int pageTotalCountMod = viewTotalCount % viewCountPerPage;
		if(pageTotalCountMod > 0) {
			pageTotalCount = viewTotalCount / viewCountPerPage + 1;
		}else {
			pageTotalCount = viewTotalCount / viewCountPerPage;
		}
		
		// 1. -> 1 2 3 4 5
		// 2. -> 1 2 3 4 5 
		// ....
		// 6. -> 6 7 8 9 10
		int startNavi = ((currentPage - 1) / pageCountPerView) * pageCountPerView + 1;
		int endNavi = startNavi + pageCountPerView - 1;
		// 내가 1 페이지에 있을때 1페이지 버튼이 눌리지 않게 하는 작업
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		
		// 페이지마다 현재 페이지에서 출력하는 화면을 볼 수 있게 하이퍼링크를 연결해주는 식
		if(needPrev) {
			sb.append("<a href='/notice/list?currentPage=" + (startNavi-1) + "'> [이전] </a>");
		}
		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append(i);
			}else {
				sb.append("<a href='/notice/list?currentPage=" + i + "'>" + i + "</a>");
			}
		}
		if(needNext) {
			sb.append("<a href='/notice/list?currentPage=" +(endNavi+1) + "'> [다음] </a>");
		}
		return sb.toString();
		
	}
	
	public int totalCount(Connection conn) {
		int totalValue = 0;
		Statement stmt = null;
		ResultSet rset = null;
		// 공지사항의 글 갯수를 가져오고 토탈카운트라는 별칭을 붙여줌
		String query = "SELECT COUNT (*) AS TOTALCOUNT FROM NOTICE";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				totalValue = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalValue;
	}

	public Notice selectOneByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice noticeOne = null;
		String query ="SELECT * FROM NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				noticeOne = new Notice();
				noticeOne.setNoticeNo(rset.getInt("NOTICE_NO"));
				noticeOne.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				noticeOne.setNoticeContents(rset.getString("NOTICE_CONTENTS"));
				noticeOne.setWriterId(rset.getString("WRITER_ID"));
				noticeOne.setRegDate(rset.getDate("REG_DATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return noticeOne;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		// DAO의 역할
		// 1.쿼리문 실행 및 결과값 받기
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="DELETE FROM NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			}
			return result;
		}

	public int updateNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="UPDATE NOTICE SET NOTICE_SUBJECT = ?, NOTICE_CONTENTS = ? WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContents());
			pstmt.setInt(3, notice.getNoticeNo());
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


