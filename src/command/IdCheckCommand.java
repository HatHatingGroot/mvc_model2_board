package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.UserDAO;
import userVO.UserVO;

public class IdCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idInput = request.getParameter("idInput");
		UserDAO udao = new UserDAO();
		UserVO uvo = udao.getUser(idInput);
		
		if(uvo!=null) request.setAttribute("able", false);//중복확인용 파라미터
		else request.setAttribute("able", true);
		
		request.setAttribute("uvo", uvo);
		
	}

}
