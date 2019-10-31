package boardDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import boardVO.BVO;
import boardVO.RVO;
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

	public ArrayList<BVO> getList(String sort, String order,String pageNum, String queryType, String query) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 1,9 2 1,12 3 1,15 4 4,18 
		int period = 3;
		int pageNums = Integer.parseInt(pageNum);
		int min = 1;
		if(pageNums>3) {
			min = (pageNums-3)*period + 1;
		}
		int max = (pageNums+2)*period;
		try {
			con = ds.getConnection();
			String sql = "SELECT a.rn as rn, bID as bID, userID as userID, bTitle as bTitle, bDate as bDate, bHit as bHit, bLike as bLike  "
						+ "FROM ("
							+ "SELECT ROWNUM as rn,bID, userID, bTitle, bDate, bHit, bLike "
							+ "FROM ("
								+ "SELECT bID, userID, bTitle, bDate, bHit, bLike "
								+ "FROM mvc_board  ORDER BY "+sort+" "+order+" )"
							+ "WHERE ROWNUM <= "+max+") a "
						+ "WHERE a.rn >= "+min;
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<BVO> list = new ArrayList<>();
			while (rs.next()) {
				BVO bvo = new BVO();
				bvo.setUserID(rs.getString("userID"));
				bvo.setbID(rs.getInt("bID"));
				bvo.setbTitle(rs.getString("bTitle"));
				bvo.setbDate(rs.getString("bDate"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbLike(rs.getInt("bLike"));
					if(rs.getString(queryType).contains(query)) {
						list.add(bvo);
					}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // DB 오류
		} finally {
			try {
				if (pstmt != null)pstmt.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public HashMap<BVO, ArrayList<RVO>> getContent(int bID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "SELECT b.userID buserID, b.bDate bDate, b.bHit bHit, b.bTitle bTitle, b.bLike bLike,"
					+ " b.bContent bContent, r.userID ruserID, r.rContent rContent, r.rDate rDate, r.rLike rLike"
					+ " FROM mvc_board b, mvc_reply r "
					+ "	WHERE b.bID = r.bID(+) AND b.bID = ? "
					+ " ORDER BY r.rDate DESC";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bID);
			ResultSet rs = pstmt.executeQuery();
			HashMap<BVO, ArrayList<RVO>> map = new HashMap<>();
			ArrayList<RVO> list = new ArrayList<>();
			BVO bvo = new BVO();
			
			while (rs.next()) {	
				RVO rvo = new RVO();
				rvo.setUserID(rs.getString("ruserID"));
				rvo.setrContent(rs.getString("rContent"));
				rvo.setrDate(rs.getString("rDate"));
				rvo.setrLike(rs.getInt("rLike"));
				
				list.add(rvo);

				bvo.setbID(bID);//
				bvo.setUserID(rs.getString("buserID"));//
				bvo.setbTitle(rs.getString("bTitle"));//
				bvo.setbDate(rs.getString("bDate"));//
				bvo.setbHit(rs.getInt("bHit"));//
				bvo.setbLike(rs.getInt("bLike"));
				bvo.setbContent(rs.getString("bContent"));//
			}
			if (rs.next()) {
			}
			map.put(bvo, list);
			
			return map;
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
	public void addHit(int bID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "UPDATE mvc_board SET bHit = bHit + 1 "
					+ "	WHERE bID  = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bID);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public int write(BVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO mvc_board VALUES(MVC_BOARD_SEQ.NEXTVAL+100, ?, ?, ?, SYSDATE, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bvo.getUserID());
			pstmt.setString(2, bvo.getbTitle());
			pstmt.setString(3, bvo.getbContent());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return 1;
			}else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
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
	public int modify(BVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "UPDATE mvc_board SET bTitle = ?,bContent =  ?,bDate= SYSDATE WHERE bID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setInt(3, bvo.getbID());
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return 1;
			}else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
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

	public int delete(int bID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "DELETE mvc_board WHERE bID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bID);
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return 1;
			}else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
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
	public int reply(RVO rvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO mvc_reply VALUES(MVC_BOARD_SEQ.NEXTVAL+1000, ?, ?, ?, SYSDATE, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rvo.getUserID());
			pstmt.setInt(2, rvo.getbID());
			pstmt.setString(3, rvo.getrContent());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return 1;
			}else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
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
