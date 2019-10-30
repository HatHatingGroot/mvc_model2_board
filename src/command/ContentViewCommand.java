package command;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardDAO.BDAO;
import boardVO.BVO;
import boardVO.RVO;
import userDAO.UserDAO;
import userVO.UserVO;

public class ContentViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int bID = Integer.parseInt(request.getParameter("bID"));
		
		BDAO bdao = new BDAO();
		HashMap<BVO, ArrayList<RVO>> map = bdao.getContent(bID);
		BVO bvo = map.keySet().iterator().next();
		ArrayList<RVO> rList = map.get(bvo);
		if(map!=null) {
			request.setAttribute("bvo",bvo );
			request.setAttribute("rList",rList );
		}
		
		
		System.out.println(map);
	}

}
