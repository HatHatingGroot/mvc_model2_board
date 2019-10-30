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
			String sql = "SELECT * FROM mvc_board ORDER BY "+sort +" "+ order;
			
			String sql2 = "SELECT a.rn, bID, userID, bTitle, bDate, bHit, bLike  "
						+ "FROM ("
							+ "SELECT ROWNUM as rn,bID, userID, bTitle, bDate, bHit, bLike "
							+ "FROM ("
								+ "SELECT bID, userID, bTitle, bDate, bHit, bLike "
								+ "FROM mvc_board  ORDER BY ? )"
							+ "WHERE ROWNUM <= ?) a "
						+ "WHERE a.rn >= ?";
			System.out.println(sql2);
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, sort + " "+order);
			pstmt.setInt(2, max);
			pstmt.setInt(3, min);
			
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<BVO> list = new ArrayList<>();
			while (rs.next()) {
				BVO bvo = new BVO();
				bvo.setbID(rs.getInt("bID"));
				bvo.setUserID(rs.getString("userID"));
				bvo.setbTitle(rs.getString("bTitle"));
				bvo.setbDate(rs.getString("bDate"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbLike(rs.getInt("bLike"));
				
				if(queryType.equals("bID")) {
					if(query.equals(bvo.getbID()+"")){
						list.add(bvo);
					}
				}else {
					if(rs.getString(queryType).contains(query)) {
						list.add(bvo);
					}
				}
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

	public HashMap<BVO, ArrayList<RVO>> getContent(int bID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "SELECT b.userID buserID, b.bDate bDate, b.bHit bHit, b.bTitle bTitle, b.bLike bLike,"
					+ " b.bContent bContent, r.userID ruserID, r.rContent rContent, r.rDate rDate, r.rLike rLike"
					+ " FROM mvc_board b, mvc_reply r "
					+ "	WHERE b.bID = r.bID AND b.bID = ?";
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
			}
			if (rs.next()) {
				bvo.setbID(bID);//
				bvo.setUserID(rs.getString("buserID"));//
				bvo.setbTitle(rs.getString("bTitle"));//
				bvo.setbDate(rs.getString("bDate"));//
				bvo.setbHit(rs.getInt("bHit"));//
				bvo.setbLike(rs.getInt("bLike"));
				bvo.setbContent(rs.getString("bContent"));//
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
}
