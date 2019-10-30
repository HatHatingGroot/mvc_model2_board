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
		System.out.println("========ListCommand Start ============");
		
		String sort = "bDate";
		String order = "DESC";
		String pageNum = "1";
		String queryType = "userID";
		String query = "";
		if(request.getParameter("sort")!=null && !request.getParameter("sort").equals("")) {
			sort = request.getParameter("sort");
		}
		if(request.getParameter("order")!=null && !request.getParameter("order").equals("")) {
			order = request.getParameter("order");
		}
		if(request.getParameter("pageNum")!=null && !request.getParameter("pageNum").equals("")) {
			pageNum = request.getParameter("pageNum");
		}
		if(request.getParameter("queryType")!=null && !request.getParameter("queryType").equals("")) {
			queryType = request.getParameter("queryType");
		}
		if(request.getParameter("query")!=null && !request.getParameter("query").equals("")) {
			query = request.getParameter("query");
		}
		

//		System.out.println("========최종 매개변수 시작============");
//		System.out.println("sort :" + sort);
//		System.out.println("order :" + order);
//		System.out.println("pageNum :" + pageNum);
//		System.out.println("queryType :" + queryType);
//		System.out.println("query :" + query);
//		System.out.println("========최종 매개변수 시작============");
		
		BDAO bdao = new BDAO();
		ArrayList<BVO> list = bdao.getList(sort,order,pageNum, queryType, query);
		
		if(list!=null) request.setAttribute("list", list);//중복확인용 파라미터
		
		
		
	}

}
