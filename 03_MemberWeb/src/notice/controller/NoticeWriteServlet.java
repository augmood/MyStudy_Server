package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/notice/write")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지 이동용
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeWrite.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String subject = request.getParameter("notice-sub");
		String contents = request.getParameter("notice-contents");
		HttpSession session = request.getSession();
		// 로그인 서블릿에서 userId로 받았기 떄문에
		String writerId = (String)session.getAttribute("userId");
		// notice 객체에 정보셋팅
		Notice notice = new Notice();
		notice.setNoticeSubject(subject);
		notice.setNoticeContents(contents);
		notice.setWriterId(writerId);
		// service에 넘김
		int result = new NoticeService().registerNotice(notice);
		// 결과여부에 따라서 페이지 이동
		if(result > 0) {
			// 작성 성공후에 noticeList.jsp를 보고 싶음
			// but noticeList.jsp를 바로 이동하는 것이 아니라
			// 이미 noticeList.jsp를 보여주도록 하는 서블릿을 요청해서 볼 수 있도록 함
			response.sendRedirect("/notice/list");
		}else {
			// 작성 실패 
			request.getRequestDispatcher("/WEB-INF/views/notice/serviceFailed.html").forward(request, response);
		}
	}

}
