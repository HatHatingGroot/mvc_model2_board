package FrontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import command.Command;
import command.ContentViewCommand;
import command.FindIDPWCommand;
import command.IdCheckCommand;
import command.JoinCommand;
import command.ListCommand;
import command.LogInCommand;
import command.LogOutCommand;
import command.PersonalInfoCommand;
import command.PersonalInfoModCommand;
//import command.SearchCommand;
import command.WithdrawalCommand;

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
			command.execute(request, response);
			int result = (int) request.getAttribute("result");
			if(result == 1) viewPage = "main.do";
			else if(result == 0) viewPage = "error500.do";
			else if(result == -1) viewPage = "error500.do";
		}else if(com.equals("/idCheck.do")) {
			command = new IdCheckCommand();
			command.execute(request, response);
			viewPage = "idCheck.jsp";
		}else if(com.equals("/logIn.do")) {
			viewPage = "logIn.jsp";
		}else if(com.equals("/logInAction.do")) {
			System.out.println("loginAction cont ");
			command = new LogInCommand();
			command.execute(request, response);
			if(request.getAttribute("idIncorrect") == null &&
			   request.getAttribute("pwIncorrect") == null) {
				//아이디 또는 패스워드 불일치
				viewPage = "main.jsp";
			}else {
				viewPage="logIn.jsp";
			}
		}else if(com.equals("/logOut.do")) {
			System.out.println("logOut cont ");
			command = new LogOutCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		}else if(com.equals("/findIDPW.do")) {
			System.out.println("findIDPW cont ");
			viewPage = "findIDPW.jsp";
		}else if(com.equals("/findAction.do")) {
			System.out.println("findAction cont ");
			command = new FindIDPWCommand();
			command.execute(request, response);
			viewPage = "findIDPW.jsp";	
		}else if(com.equals("/myPage.do")) {
			viewPage = "mypage.jsp";	
		}else if(com.equals("/personalInfo.do")) {
			command = new PersonalInfoCommand();
			command.execute(request, response);
			viewPage = "personalInfo.jsp";	
		}else if(com.equals("/withdrawal.do")) {
			command = new WithdrawalCommand();
			command.execute(request, response);
			viewPage = "mypage.jsp";	
		}else if(com.equals("/personalInfoMod.do")) {
			command = new PersonalInfoModCommand();
			command.execute(request, response);
			viewPage = "mypage.jsp";	
		}else if(com.equals("/list.do")) {
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		}else if(com.equals("/content_view.do")) {
			command = new ContentViewCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
