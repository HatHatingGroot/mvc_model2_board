package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.UserDAO;
import userVO.UserVO;

public class PersonalInfoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = (String) request.getSession().getAttribute("userID");
		System.out.println(userID);
		UserDAO udao = new UserDAO();
		UserVO uvo = udao.getUser(userID);
		
		if(uvo!=null) request.setAttribute("uvo", uvo);//중복확인용 파라미터
		
		
		
	}

}
