package model.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import model.model.vo.Student;


public class StudentDAO { //studentservice의 메소드
	
	public StudentDAO() {}

	public int insertStudent(Connection conn, Student student) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
		
		try {
			// 연결을 통해서  pstmt 객체 생성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPw());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentGender());
			pstmt.setInt(5, student.getStudentAge());
			pstmt.setString(6, student.getStudentEmail());
			pstmt.setString(7, student.getStudentPhone());
			pstmt.setString(8, student.getStudentAddress());
			pstmt.setString(9, student.getStudentHobby());
			
			//쿼리문 실행 메소드
			result = pstmt.executeUpdate();
			// result값이 숫자->성공한 행의 갯수를 말하는 것, 0이상이면 성공, 0이하면 실패(들어온 값이 없으니까)
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 학생서블릿에서 넘어옴 -> 아이디와 비밀번호 값이 맞는지 확인해보기
	public Student selectOneLogin(Connection conn, String userId, String userPw) {
		// 연결을 통해 생성하는  -> statement (위치 홀더를 사용하지 않음)
		// 쿼리문 실행 결과를 받기 위한 -> Resultset
		
		Statement stmt = null;
		ResultSet rset = null;
		Student student  = null;
		String query = "SELECT * FROM STUDENT WHERE STUDENT_ID ='" +userId+"'AND STUDENT_PW = '"+userPw+"'";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			// rset의 값을 student에 담는 과정
			if(rset.next()) {
				student = new Student(); // null에다가 담을거 아니니까 
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return student;
	}

	public Student selectOneById(Connection conn, String studentId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Student student  = null;
		String query = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentId);
			// 쿼리문 실행
			rset = pstmt.executeQuery();
			// 결과 값 받아서 student에 셋팅하기
			if(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPw(rset.getString("STUDENT_PW"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentGender(rset.getString("STUDENT_GENDER"));
				student.setStudentAge(rset.getInt("STUDENT_AGE"));
				student.setStudentEmail(rset.getString("STUDENT_EMAIL"));
				student.setStudentPhone(rset.getString("STUDENT_PHONE"));
				student.setStudentAddress(rset.getString("STUDENT_ADDRESS"));
				student.setStudentHobby(rset.getString("STUDENT_HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return student;
	}

	public int deleteMember(Connection conn, String studentId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
		
		try {
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, studentId);
		result = pstmt.executeUpdate();
		}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}

	return result;
	}

	public int updateMember(Connection conn, Student student) {
		int result  = 0;
		// PreparedStatement 객체
		PreparedStatement pstmt = null;
		// 쿼리문 작성
		String query = "UPDATE STUDENT SET STUDENT_PW = ? , STUDENT_EMAIL = ? , "
				+ "STUDENT_PHONE = ? , STUDENT_ADDRESS = ? , STUDENT_HOBBY = ?  WHERE STUDENT_ID = ?";
		// 쿼리문 실행
		try {
			pstmt = conn.prepareStatement(query);
			// (x 부분을 모르겠어서 진행을 못 했음)
			pstmt.setString(1, student.getStudentPw());
			pstmt.setString(2, student.getStudentEmail());
			pstmt.setString(3, student.getStudentPhone());
			pstmt.setString(4, student.getStudentAddress());
			pstmt.setString(5, student.getStudentHobby());
			pstmt.setString(6, student.getStudentId());
			// 쿼리문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
	
		// 자원 해제 
		return result;
	}

	public List<Student> selectAllList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		List<Student> sList = null;
		String query = "SELECT * FROM STUDENT";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			sList = new ArrayList<Student>();
			while(rset.next()) {
				Student student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPw(rset.getString("STUDENT_PW"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentGender(rset.getString("STUDENT_GENDER"));
				student.setStudentAge(rset.getInt("STUDENT_AGE"));
				student.setStudentEmail(rset.getString("STUDENT_EMAIL"));
				student.setStudentPhone(rset.getString("STUDENT_PHONE"));
				student.setStudentHobby(rset.getString("STUDENT_HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				// 최종적으로 저장
				sList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return null;
	}
		
}
