package notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.server.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/notice/list")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		String getCurrentPage = request.getParameter("currentPage");
		if(getCurrentPage == null) {
			currentPage = 1;
		}else {
			Integer.parseInt(request.getParameter("currentPage"));
		}			
		PageData pageData = new NoticeService().printAllNotice(currentPage);
		List<Notice> nList = pageData.getNoticeList();
		if(!nList.isEmpty()) {
			request.setAttribute("nList", nList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp").forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html");
			view.forward(request, response);
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
