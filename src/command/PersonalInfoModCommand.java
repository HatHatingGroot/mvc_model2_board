package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.UserDAO;
import userVO.UserVO;

public class PersonalInfoModCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = (String) request.getSession().getAttribute("userID");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmailID") + "@" + request.getParameter("userEmailDomain");
		String userIntro = request.getParameter("userIntro");
		
		UserVO inputUvo = new UserVO();
		inputUvo.setUserID(userID);
		inputUvo.setUserName(userName);
		inputUvo.setUserEmail(userEmail);
		inputUvo.setUserIntro(userIntro);
		UserDAO udao = new UserDAO();
		int result = udao.modUser(inputUvo);
		
		if(result==1) request.setAttribute("result", true);
		else request.setAttribute("result", false);
		
		
	}

}
