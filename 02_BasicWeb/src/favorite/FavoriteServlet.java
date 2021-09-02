package favorite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
	
	public FavoriteServlet() {}
	
	@Override
	protected void doGet(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("pname");
		String userColor = req.getParameter("color");
		String userPlayer = req.getParameter("player");
		String [] userFood = req.getParameterValues("food");
		
		
		req.setAttribute("userName", userName);
		req.setAttribute("userColor", userColor);
		req.setAttribute("userPlayer", userPlayer);
		req.setAttribute("userFood", userFood);
		
		req.getRequestDispatcher("/favorite/favoriteResult.jsp").forward(req, resp);
		
		
		
		
/*		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>취향테스트 결과</title>");
		out.println("<body><h2>취향테스트 결과화면</h2>");
		out.println(userName+"님은 ");
		if(userColor.equals("red")) {
			out.println("<span style = 'color:red;'>빨간색을 좋아합니다.</span>"+"<br>");
		}else if(userColor.equals("blue")) {
			out.println("<span style = 'color:blue;'>파란색을 좋아합니다.</span>"+"<br>");
		}else if(userColor.equals("green")) {
			out.println("<span style = 'color:green;'>초록색을 좋아합니다.</span>"+"<br>");
		}
		
		if(userPlayer.equals("sjkim")) {
			out.println("김수지 선수를 좋아합니다.");
		}else if(userPlayer.equals("japark")) {
			out.println("박정아 선수를 좋아합니다.");
		}else if(userPlayer.equals("yjhwang")) {
			out.println("황연주 선수를 좋아합니다.");
		}else if(userPlayer.equals("hjyang")) {
			out.println("양효진 선수를 좋아합니다.");
		}
		
		out.println("좋아하는 음식은 ");
		for(String food : userFood) {
			if(food.equals("zzajang")) {
				out.println("짜장면,");
			}else if(food.equals("zzam")) {
				out.println("짬뽕,");
			}else if(food.equals("tang")) {
				out.println("탕수육,");
			}else if(food.equals("pal")) {
				out.println("팔보채,");
			}
		}
		out.println("입니다.");
		out.println("</body></html>"); 
		*/
	}

	
}
