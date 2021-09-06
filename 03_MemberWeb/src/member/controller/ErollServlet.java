package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.model.service.StudentService;
import model.model.vo.Student;

@WebServlet("/member/enroll")
public class ErollServlet extends HttpServlet{
	
	public ErollServlet() {}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user-id");
		String userPw = request.getParameter("user-pw");
 		String userName = request.getParameter("user-name");
		String userGender = request.getParameter("gender");
		int userAge = Integer.parseInt(request.getParameter("user-age"));
		String userEmail = request.getParameter("user-email");
		String userPhone = request.getParameter("use-phone");
		String userAddress = request.getParameter("user-address");
		String userHobby = request.getParameter("user-hoddy");
		
		//1. 입력받은 값을 Member객체 저장
		Student student = new Student(userId, userPw, userName, userGender, userAge, userEmail, 
				userPhone, userAddress, userHobby);
		
		//2. Member객체를 Service, DAO로 넘겨서
		//3. DB연결 후 쿼리 실행해서 정보를 디비에 저장
		int result = new StudentService().registerStudent(student);
		//4. 쿼리 실행 결과를 가지고 성공/실패 여부에 따라서 view를 선택해서 보여줌
		if(result > 0) {
			// 가입성공 -> 로그인 페이지
			response.sendRedirect("/index.jsp");
		} else {
			// 가입실패 - > 실패 메세지
			response.sendRedirect("/member/studentError.html");
		}
		
		
	}
	


}
