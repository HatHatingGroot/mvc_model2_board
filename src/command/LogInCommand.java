package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userDAO.UserDAO;
import userVO.UserVO;

public class LogInCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		UserDAO udao = new UserDAO();
		UserVO uvo = udao.getUser(userID);
		if(uvo!=null) {
			if(uvo.getUserPW().equals(userPW)) {
				HttpSession session = request.getSession();
				session.setAttribute("userID", uvo.getUserID());
				request.setAttribute("uvo", uvo);
			}else {
				request.setAttribute("pwIncorrect", true);
			}
		}else {
			request.setAttribute("idIncorrect", true);
		}
		
	}

}
