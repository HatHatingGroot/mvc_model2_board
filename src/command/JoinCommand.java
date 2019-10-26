package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.UserDAO;
import userVO.UserVO;

public class JoinCommand implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userEmail = request.getParameter("userEmailID") + "@" + request.getParameter("userEmailDomain");
		String userGender = request.getParameter("userGender");
//		File userPhoto = request.getParameter("userPhoto");
		String userIntro = request.getParameter("userIntro");

		UserVO uvo = new UserVO(userName, userID, userPW, userEmail, userGender, userIntro);
		UserDAO udao = new UserDAO();
		int result = udao.join(uvo);
		return result;
	}

}
