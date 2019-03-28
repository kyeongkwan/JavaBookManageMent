package kwany.bmm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kwany.bmm.common.ConnectMariaDB;
import kwany.bmm.model.ModelBook;

public class DaoBook {
	private ConnectMariaDB db;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sb;
	private String sql;
	private ModelBook modelBook;
	
	public DaoBook() {
		db = ConnectMariaDB.getInstance();
		con = db.getConnection();
		pstmt = db.getPstmt();
		rs = db.getRs();
		sb = new StringBuilder();
		sql = null;
		modelBook = new ModelBook();
	}
	//도서검색
	public ArrayList<ModelBook> searchBook() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//도서추가
	public ModelBook doAddBook(ModelBook modelBook) {
		sb.setLength(0);
		sql = sb.append("INSERT INTO Book (number, title, author, publisher, pubDate, genre, cnt, state) ")
				.append("VALUES(?,?,?,?,?,?,DEFAULT(cnt),DEFAULT(state))")
				.toString();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modelBook.getNumber());
			pstmt.setString(2, modelBook.getTitle());
			pstmt.setString(3, modelBook.getAuthor());
			pstmt.setString(4, modelBook.getPublisher());
			pstmt.setString(5, modelBook.getPubDate());
			pstmt.setString(6, modelBook.getGenre());
			pstmt.executeUpdate();
			return modelBook;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("도서추가중 오류 발생.");
			return modelBook;
		}
	}

}
