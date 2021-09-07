package model.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import model.model.dao.StudentDAO;
import model.model.vo.Student;

public class StudentService {
	
	private JDBCTemplate jdbcTemplate;
	
	public StudentService() {
		jdbcTemplate = JDBCTemplate.getConnection();
		//생성자에서 JDBCTemplate 객체를 만들어준다 -> 드라이버 등록이 됨 -> 연결 생성 준비 완료
	}
	
	// 9개의 데이터 값을 한꺼번에 받는 참조변수 student 사용
	public int registerStudent(Student student) {
		int result = 0;  // 보통 입력이 얼마나 됐는지를 판단할때는 갯수로 판단함
		
		// 연결생성
		Connection conn = null;
		try {
			conn = jdbcTemplate.createConnection();
			result = new StudentDAO().insertStudent(conn, student);
		
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn); // DB연결 해제
		}
		return result;
	}

	// 로그인 서블릿 -> 로그인으로 들어온 값이 있을떄 출력해줌 
	public Student printOneLogin(String userId, String userPw) {
		Student student = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			student = new StudentDAO().selectOneLogin(conn, userId, userPw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return student;
	}

	public Student printOneById(String studentId) {
		Student student = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			student = new StudentDAO().selectOneById(conn, studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return student;
	}

	public int deleteStudent(String studentId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new StudentDAO().deleteMember(conn, studentId);
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

	public int modifyStudent(Student student) {
		int result = 0;
		Connection conn = null;
		// 연결
		try {
			// 연결 생성하고 
			conn = jdbcTemplate.createConnection();
			result = new StudentDAO().updateMember(conn, student);
			// 커밋 여부 출력
			if (result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 연결을 닫아주기
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public List<Student> printAllList() {
		Connection conn = null;
		List<Student> sList = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			sList = new StudentDAO().selectAllList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return null;
	}

}
