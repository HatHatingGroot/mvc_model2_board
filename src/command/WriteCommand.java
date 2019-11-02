package command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardDAO.BDAO;
import boardVO.BVO;
import boardVO.RVO;
import userDAO.UserDAO;
import userVO.UserVO;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String userID = (String) request.getSession().getAttribute("userID");
		BVO bvo = new BVO(0, userID, bTitle, bContent, "", 0, 0);
		BDAO bdao = new BDAO();
		
		int result = bdao.write(bvo);
		
	}

}
