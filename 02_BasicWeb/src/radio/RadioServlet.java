package radio;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radio")
public class RadioServlet extends HttpServlet{
	
	public RadioServlet() {}
	
	@Override
	// do는 html의 method와 형태를 같이 한다.
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gender = req.getParameter("gender");
		String chkEmail = req.getParameter("chk-email");
		
		req.setAttribute("gender12", gender);
		req.setAttribute("chk-email", chkEmail);
		
		req.getRequestDispatcher("/radio/radioResult.jsp").forward(req, resp);
		
//		// 한글 인코딩 후 출력
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html; charset=utf-8");
		
//		PrintWriter out = resp.getWriter();
//		
//		out.println("성별 : "+ gender);
//		out.println("메일 수신여부 : "+ chkEmail);
	}


}
