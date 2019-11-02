package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userDAO.UserDAO;
import userVO.UserVO;

public class WithdrawalCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = (String) request.getSession().getAttribute("userID");
		System.out.println(userID);
		String inputPW = request.getParameter("inputPW");
		System.out.println(inputPW);
		UserDAO udao = new UserDAO();
		UserVO uvo = udao.getUser(userID);
		request.setAttribute("uvo", uvo);
		if(uvo!=null) {
			if(uvo.getUserPW().equals(inputPW)) {
				if(udao.withdraw(userID)==1) {
					request.getSession().invalidate();
					request.setAttribute("withdrawal", true);
				}
			}else {
				request.setAttribute("withdrawal", false);
			}
		}else {
			request.setAttribute("withdrawal", false);
		}
		
	}

}
