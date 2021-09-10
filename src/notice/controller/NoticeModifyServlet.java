package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeModifyServlet
 */
@WebServlet("/notice/modify")
public class NoticeModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 할 내용을 받아오자
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice noticeOne = new NoticeService().printOneByNo(noticeNo);
		request.setAttribute("notice", noticeOne);
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeModify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정한거 보내주자
		request.setCharacterEncoding("UTF-8");
		String subject = request.getParameter("notice-subject");
		String contents = request.getParameter("notice-contents");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice notice = new Notice();
		notice.setNoticeSubject(subject);
		notice.setNoticeContents(contents);
		notice.setNoticeNo(noticeNo);
		int result = new NoticeService().modifyNotice(notice);
		if(result > 0) {
			response.sendRedirect("/notice/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

}
