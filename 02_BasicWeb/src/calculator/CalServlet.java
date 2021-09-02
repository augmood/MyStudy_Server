package calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cal") // -> 작성해줄 경우 매핑이 따로 필요하지 않다.
public class CalServlet extends HttpServlet {
	
	public CalServlet() {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1 = Integer.parseInt(req.getParameter("first_num"));
		int num2 = Integer.parseInt(req.getParameter("second_num"));
		char operator = req.getParameter("operator").charAt(0);
		int result = 0;
		switch(operator) {
		case '+' :
			result = num1+num2;
			break;
		case '-' :
			result = num1-num2;
			break;
		case 'x' :
			result = num1*num2;
			break;
		case '/' :
			result = num1/num2;
			break;
		}
		//출력
		req.setAttribute("firstNum", num1);
		req.setAttribute("secondNum", num2);
		req.setAttribute("operator", operator);
		req.setAttribute("result", result);
		
		// 보내기 -> 보내주지 않으면 서블릿에서 값을 꺼내서 쓸 수 없음
		RequestDispatcher view = req.getRequestDispatcher("/calculator/calResult.jsp");
		view.forward(req,resp);
		
		
		//출력
//		PrintWriter out= resp.getWriter();
//		out.print(result);
	}
}
