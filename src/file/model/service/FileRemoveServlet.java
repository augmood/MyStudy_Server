package file.model.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileRemoveServlet
 */
@WebServlet("/file/remove")
public class FileRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fileUser = request.getParameter("file-user");
		String fileName = request.getParameter("file-name");
		String filePath = request.getParameter("file-path");
		int result = new FileService().removeFile(fileUser, fileName);
		File removeFile = new File(filePath);
		if(result > 0) {
			removeFile.delete();
			response.sendRedirect("/file/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/file/fileError.html").forward(request, response);
		}
	}

}
