package kwany.bmm.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectMariaDB {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	private ConnectMariaDB() {
		con = setConnectiontMariaDB();
		pstmt = null;
		rs = null;
	}
	private static class ConnectMariaDBInstance{
		private static final ConnectMariaDB INSTANCE = new ConnectMariaDB();
	}
	public static ConnectMariaDB getInstance() {
		return ConnectMariaDBInstance.INSTANCE;
	}
	
	private Connection setConnectiontMariaDB() {
		String driver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost:3307/bookmanagement?"
				+"autoReconnect=true&verifyServerCertificate=false&useSSL=false";
		String id = "root";
		String pwd = "1234";
		try {
			Class.forName(driver);
			Connection connectionMariaDB = DriverManager.getConnection(url, id, pwd);
			return connectionMariaDB;
		} catch (Exception e) {
			System.out.println("DB연결 실패");
		}
		return null;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	
	public ResultSet getRs() {
		return rs;
	}
	
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("db연결해제중 오류발생");
		}
	}
	
	public void closeConnection(Connection con, PreparedStatement pstmt) {
		try {
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("db연결해제중 오류발생");
		}
	}
	
	public void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("db연결해제중 오류발생");
		}
	}
}
