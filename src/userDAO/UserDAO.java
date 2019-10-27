package userDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import userVO.UserVO;

public class UserDAO {
	DataSource ds;

	public UserDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");// DBCP구현
		} catch (Exception e) {
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

			if (pstmt.executeUpdate() == 1) {
				return 1; // 업데이트 성공
			} else {
				return 0; // 업데이트 실패
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; // DB 오류
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public UserVO getUser(String userID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM mvc_clients WHERE userID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				UserVO uvo = new UserVO();
				uvo.setUserID(rs.getString("userID"));
				uvo.setUserPW(rs.getString("userPW"));
				uvo.setUserName(rs.getString("userName"));
				uvo.setUserEmail(rs.getString("userEmail"));
				uvo.setUserGender(rs.getString("userGender"));
				uvo.setUserIntro(rs.getString("userIntro"));
				return uvo; // 업데이트 성공
			} else {
				return null; // 업데이트 실패
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // DB 오류
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int initPW(String userID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			Random rd = new Random();
			String tempPW = "";
			char[] data = new char[3];
			for(int i=0; i<4; i++) {
				data[0] = (char)(rd.nextInt(10)+48);//숫자
				data[1] = (char)(rd.nextInt(26)+65);//소문자
				data[2] = (char)(rd.nextInt(26)+97);//대문자
				tempPW += String.valueOf(data);
			}
			String sql = "UPDATE mvc_clients SET userPW = ? WHERE userID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tempPW);
			pstmt.setString(2, userID);

			if (pstmt.executeUpdate() == 1) {
				return 1; // 업데이트 성공
			} else {
				return 0; // 업데이트 실패
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; // DB 오류
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public UserVO findID(String userName, String userEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String sql = "SELECT userID FROM mvc_clients WHERE userName = ? AND userEmail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				UserVO uvo = new UserVO();
				uvo.setUserID(rs.getString("userID"));
				return uvo; // 업데이트 성공
			} else {
				return null; // 업데이트 실패
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // DB 오류
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
