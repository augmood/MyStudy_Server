package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.model.service.StudentService;
import model.model.vo.Student;

/**
 * Servlet implementation class ListAllServlet
 */
@WebServlet("/member/listAll")
public class ListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> sList = new StudentService().printAllList();
		if(!sList.isEmpty()) {
			// 성공하면 sList 데이터를 가지고 memberList.jap로 이동
			request.setAttribute("sList", sList);
			request.getRequestDispatcher("/member/list.jsp").forward(request, response);
		}else {
			// 실패하면 실패페이지로 이동
			response.sendRedirect("/member/StudentError.html");
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
