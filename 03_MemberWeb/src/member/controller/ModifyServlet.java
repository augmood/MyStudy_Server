package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.model.service.StudentService;
import model.model.vo.Student;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/member/modify")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글이 깨지지 않게 한다.
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user-id");
		String userPw = request.getParameter("user-pw");
		String userEmail = request.getParameter("user-email");
		String userPhone = request.getParameter("user-phone");
		String userAddress = request.getParameter("user-address");
		String userHobby = request.getParameter("user-hobby");
		Student student = new Student(userId, userPw, userEmail, userPhone, userAddress, userHobby);
		int result = new StudentService().modifyStudent(student);
		if(result > 0) {
			response.sendRedirect("/index.jsp");
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
