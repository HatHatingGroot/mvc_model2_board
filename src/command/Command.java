package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	int execute(HttpServletRequest request, HttpServletResponse response);//명령 실행 메서드
}
