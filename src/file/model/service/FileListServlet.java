package file.model.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import file.model.vo.FileData;

/**
 * Servlet implementation class FileListServlet
 */
@WebServlet("/file/list")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentId =(String)session.getAttribute("userId");
		List<FileData> fList = new FileService().printFileList(studentId);
		if(!fList.isEmpty()) {
			// 바로 아랫줄 반드시 입력
			request.setAttribute("fList", fList);
			request.getRequestDispatcher("/WEB-INF/views/file/fileList.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/file/fileError.html").forward(request, response);
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
