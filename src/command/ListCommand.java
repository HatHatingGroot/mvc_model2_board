package command;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardDAO.BDAO;
import boardVO.BVO;
import userDAO.UserDAO;
import userVO.UserVO;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String arrStandard = "bDate";
		String direction = "DESC";
		if(request.getParameter("arrStandard")!=null && !request.getParameter("arrStandard").equals("")) {
			arrStandard = request.getParameter("arrStandard");
		}
		if(request.getParameter("direction")!=null && !request.getParameter("direction").equals("")) {
			direction = request.getParameter("direction");
		}
		System.out.println(arrStandard);
		System.out.println(direction);
		BDAO bdao = new BDAO();
		ArrayList<BVO> list = bdao.getList(arrStandard,direction);
		
		if(list!=null) request.setAttribute("list", list);//중복확인용 파라미터
		
		
		
	}

}
