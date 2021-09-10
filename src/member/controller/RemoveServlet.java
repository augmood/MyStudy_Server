package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.StudentService;

/**
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/member/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentId = (String)session.getAttribute("userId");
		int result = new StudentService().deleteStudent(studentId);
		if(result != 0 ) {
			// 로그아웃 서블릿에 이미 파괴하는 것이 있으니까 이걸 호출해주면 됨.
			// 로그아웃 서블릿 호출은 어떻게 할까
			// 아예 이렇게 부름
			response.sendRedirect("/member/logout");
		}else {
			response.sendRedirect("/member/studentError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
