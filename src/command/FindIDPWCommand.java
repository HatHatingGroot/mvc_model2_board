package command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.UserDAO;
import userVO.UserVO;

public class FindIDPWCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("findIDPW Cmd");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmailID") + "@" + request.getParameter("userEmailDomain");

		String find = request.getParameter("find");
		String userID = "";

		UserDAO udao = new UserDAO();
		System.out.println(userName);
		System.out.println(userEmail);
		System.out.println(find);
		
		
		if (find.equals("findPW")) {
			userID = request.getParameter("userID");
			UserVO uvo = udao.getUser(userID);
			request.setAttribute("uvo", uvo);
			if (uvo != null) {
				if (uvo.getUserName().equals(userName) && uvo.getUserEmail().equals(userEmail)) {
					if (udao.initPW(userID) == 1) { // 임시비밀번호로 초기화
						uvo = udao.getUser(userID);
						request.setAttribute("inputCorrect", true);// 입력정보 일치여부 플래그
						request.setAttribute("PW", uvo);// 임시비밀번호 전달 변수
					}
				} else {
					request.setAttribute("inputCorrect", false);// 입력정보 불일치 플래그
				}
			} else {// 존재하지 않는 아이디일때
				request.setAttribute("inputCorrect", false);// 입력정보 불일치 플래그
			}
		} else if (find.equals("findID")) {
			UserVO uvo = udao.findID(userName, userEmail);// 아이디 찾기 메서드
			if (uvo != null) {
				request.setAttribute("inputCorrect", true);// 입력정보 일치여부 플래그
				request.setAttribute("ID", uvo.getUserID());// ID 전달 변수
			} else {
				request.setAttribute("inputCorrect", false);// 입력정보 불일치 플래그
			}
		}
	}

}
