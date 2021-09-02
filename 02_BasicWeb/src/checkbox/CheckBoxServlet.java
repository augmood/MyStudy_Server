package checkbox;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class CheckBoxServlet extends HttpServlet{
	
	public CheckBoxServlet() {}
	
	@Override
	// html 에서 보내준걸 받아야 하기 때문에 doget 오버로딩 해주기
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// html의 name=place를 불러온다 -> but 전부 이름이 place라 의미 없음
		// req.getParameter("place"); 
		// 배열로 불러오자 -> place의 값이 여러개, 값의 형태는 string
		String [] places = req.getParameterValues("place");
		
		//출력
		req.setAttribute("place", places);
		
		//보내기
		RequestDispatcher view = req.getRequestDispatcher("/checkbox/checkboxResult.jsp");
		view.forward(req, resp);
		
		
		// 버전 1
		/*
		 * // 한글을 입력했을때 깨짐을 방지하기 위해 작성하는 코드 // but 이것만 바꾼다고 되지 않음
		 * resp.setCharacterEncoding("UTF-8"); // 출력 타입도 함께 바꿔줌
		 * resp.setContentType("text/html; charset=UTF-8");
		 * 
		 * // 요청한 값에 대한 응답을 받기 위해 반드시 적어주어야 함 PrintWriter out = resp.getWriter(); // 사이트
		 * 이름 변경 out.println("<html><head><title>도시 선택 결과</title>");
		 * out.println("<style>h2{color : salmon} </style></head>"); // 당신이 선택한 도시입니다 +
		 * 선 out.println("<body><h2>당신이 선택한 도시입니다.</h2><hr>"); //for each 문 for(String
		 * place : places) { out.println("선택한 도시는 : "+ place+"<br>"); }
		 * out.println("</body></html>");
		 */
	}

}
