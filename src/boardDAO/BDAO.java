package boardDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import boardVO.BVO;
import userVO.UserVO;

public class BDAO {
	DataSource ds;

	public BDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");// DBCP구현
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<BVO> getList(String arrStand, String direction) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM mvc_board ORDER BY "+arrStand +" "+ direction;
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<BVO> list = new ArrayList<>();
			while (rs.next()) {
				BVO bvo = new BVO();
				bvo.setbID(rs.getInt("bID"));
				bvo.setUserID(rs.getString("userID"));
				bvo.setbTitle(rs.getString("bTitle"));
				bvo.setbContent(rs.getString("bContent"));
				bvo.setbDate(rs.getString("bDate"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbLike(rs.getInt("bLike"));
				
				list.add(bvo);
			}
			return list;
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
