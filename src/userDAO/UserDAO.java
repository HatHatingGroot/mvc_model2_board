package userDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import userVO.UserVO;

public class UserDAO {
	DataSource ds;

	public UserDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");//DBCP구현
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int join(UserVO uvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO mvc_clients VALUES(?,?,?,?,?,null,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uvo.getUserID());
			pstmt.setString(2, uvo.getUserPW());
			pstmt.setString(3, uvo.getUserName());
			pstmt.setString(4, uvo.getUserEmail());
			pstmt.setString(5, uvo.getUserGender());
			pstmt.setString(6, uvo.getUserIntro());
			
			if(pstmt.executeUpdate() == 1) {
				return 1; // 업데이트 성공
			}else {
				return 0; // 업데이트 실패
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; //DB 오류
		}
	}
	
	
}
