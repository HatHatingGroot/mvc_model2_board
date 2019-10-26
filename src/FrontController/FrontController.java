package FrontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.JoinCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private int result;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//한글 세팅
		
		String viewPage = null; //뿌려질 페이지 변수
		Command command = null; // 명령어 객체
		
		String uri=request.getRequestURI();
		System.out.println("uri : " +uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String com=uri.substring(conPath.length());
		
		if(com.equals("/main.do")) {
			viewPage = "main.jsp";
		}else if(com.equals("/error404.do")) {
			viewPage = "error404.jsp";
		}else if(com.equals("/error500.do")) {
			viewPage = "error500.jsp";
		}else if(com.equals("/join.do")) {
			viewPage = "join.jsp";
		}else if(com.equals("/joinAction.do")) {
			command = new JoinCommand();
			result = command.execute(request, response);
			if(result == 1) viewPage = "main.do";
			else if(result == 0) viewPage = "error500.do";
			else if(result == -1) viewPage = "error500.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
