package member.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.StudentService;
import member.model.vo.Student;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 온 값을 받아주기
		String userId = request.getParameter("user-id");
		String userPw= request.getParameter("user-pw");
		
		// 값이 잘 넘어가는지 확인 용도로 작성했던 코드 
//		System.out.println("userId : "+userId+", userPw : "+userPw);
//		System.out.println("OK");
		
		// 인증 과정 == > 값을 셀렉트 해줌 == > 값이 있으면 로그인을 성공하게 해준다.
		Student student = new StudentService().printOneLogin(userId, userPw);
		if(student != null) {
			// 세션을 만들어서 값을 저장해줄 것임
			HttpSession session = request.getSession();
			// 유저아이디에서 실제로 저장된 아이디 값을 가져온다. -> jsp는 공통적으로 모든 jsp에서 같은 값을 꺼내쓸 수 있다.
			session.setAttribute("userId", student.getStudentId());
			// 로그인 성공시 페이지 이동
			response.sendRedirect("/member/loginSuccess.jsp");
		}else {
			// 로그인 실패시 에러 페이지 이동
			response.sendRedirect("/member/studentError.html");
		}
	}

}
