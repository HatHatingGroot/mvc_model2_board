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

public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int bID = Integer.parseInt(request.getParameter("bID"));
		BDAO bdao = new BDAO();
		
		int result = bdao.delete(bID);
		
	}

}
