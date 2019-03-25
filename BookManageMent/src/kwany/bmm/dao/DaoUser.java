package kwany.bmm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kwany.bmm.common.ConnectMariaDB;
import kwany.bmm.model.ModelUser;

public class DaoUser {
	private ConnectMariaDB db;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sb;
	private String sql;
	private ModelUser modelUser;

	public DaoUser() {
		db = ConnectMariaDB.getInstance();
		con = db.getConnection();
		pstmt = db.getPstmt();
		rs = db.getRs();
		sb = new StringBuilder();
		sql = null;
		modelUser = new ModelUser();
	}

	// 회원가입
	public ModelUser doSignUp(ModelUser modelUser) {
		sb.setLength(0);
		sql = sb.append("INSERT INTO User (id, pwd, name, birth, gender, phone, email) ")
				.append("VALUES(?,?,?,?,?,?,?)").toString();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modelUser.getId());
			pstmt.setString(2, modelUser.getPwd());
			pstmt.setString(3, modelUser.getName());
			pstmt.setString(4, modelUser.getBirth());
			pstmt.setString(5, modelUser.getGender());
			pstmt.setString(6, modelUser.getPhone());
			pstmt.setString(7, modelUser.getEmail());
			pstmt.executeUpdate();
			return modelUser;
		} catch (Exception e) {
			return null;
		}
	}

	// 아이디 중복 체크
	public boolean confirmUserId(String id) {
		sb.setLength(0);
		sql = sb.append("SELECT idx FROM User WHERE id=?").toString();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e1) {
			return true;
		}
	}

	// 로그인
	public boolean doSignIn(String id, String pwd) {
		sb.setLength(0);
		sql = sb.append("SELECT id FROM User WHERE id=? AND pwd=?;").toString();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			return false;
		}
	}

	// 마이페이지
	public ModelUser getMyPage(String id) {
		sb.setLength(0);
		sql = sb.append("SELECT id, name, birth, gender, phone, email, point, grade ")
				.append("FROM User WHERE id=?")
				.toString();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			modelUser.setId(rs.getString("id"));
			modelUser.setName(rs.getString("name"));
			modelUser.setBirth(rs.getString("birth"));
			modelUser.setGender(rs.getString("gender"));
			modelUser.setPhone(rs.getString("phone"));
			modelUser.setEmail(rs.getString("email"));
			modelUser.setPoint(rs.getInt("point"));
			modelUser.setGrade(rs.getString("grade"));
			return modelUser;
		} catch (Exception e) {
			return null;
		}
	}

	// 비밀번호 확인
	public ModelUser confirmPwd(String id, String pwd) {
		sb.setLength(0);
		sql = sb.append("SELECT name, phone, pwd FROM User WHERE id=? AND pwd=?;").toString();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			rs.next();
			modelUser.setName(rs.getString("name"));
			modelUser.setPhone(rs.getString("phone"));
			modelUser.setPwd(rs.getString("pwd"));
			return modelUser;
		} catch (Exception e) {
			return null;
		}
	}

	// 내정보 변경
	public int updateMyInfo(String field, String id, String updateInfo) {
		sb.setLength(0);// StringBuilder 버퍼 초기화
		switch(field) {
		case "pwd":		// 비밀번호변경
			sql = sb.append("UPDATE User SET pwd=? WHERE id=?").toString();
			break;
		case "name":	// 이름변경
			sql = sb.append("UPDATE User SET name=? WHERE id=?").toString();
			break;
		case "birth":	// 생년월일변경
			sql = sb.append("UPDATE User SET birth=? WHERE id=?").toString();
			break;
		case "gender":	// 성별변경
			sql = sb.append("UPDATE User SET gender=? WHERE id=?").toString();
			break;
		case "phone":	// 연락처변경
			sql = sb.append("UPDATE User SET phone=? WHERE id=?").toString();
			break;
		case "email":	// 이메일변경
			sql = sb.append("UPDATE User SET email=? WHERE id=?").toString();
			break;
		}
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateInfo);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("잘못된 내정보 변경 시도입니다.");
			return 0;
		}
	}

	// 프로그램 종료
	public void closeProgram() {
		try {
			if (null != rs) {
				ConnectMariaDB.getInstance().closeConnection(con, pstmt, rs);
			} else if (null != pstmt) {
				ConnectMariaDB.getInstance().closeConnection(con, pstmt);
			} else {
				ConnectMariaDB.getInstance().closeConnection(con);
			}
		} catch (Exception e) {
			System.out.println("db 연결종료 중 오류");
		}
	}
}
