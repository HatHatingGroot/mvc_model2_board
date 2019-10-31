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

public class ModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bID = Integer.parseInt(request.getParameter("bID"));
		BVO bvo = new BVO(bID, "", bTitle, bContent, "", 0, 0);
		BDAO bdao = new BDAO();
		
		int result = bdao.modify(bvo);
		
	}

}
