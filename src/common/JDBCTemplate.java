package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	//싱글톤 패턴적용
	private static JDBCTemplate instance;
	
	public JDBCTemplate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 싱글톤 패턴 - 메소드
	public static JDBCTemplate getConnection() {
		
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	//연결생성
	//createConnection()을 처리하는 곳에서 sql 쿼리를 수행할거야
	public Connection createConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "KHHUMAN";
		String password = "KHHUMAN";
		Connection conn;
		conn = DriverManager.getConnection(url,user,password);
		conn.setAutoCommit(false);

		return conn;
	}

	//연결딛기
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 커밋
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//롤백
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 자원해제 
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!= null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
