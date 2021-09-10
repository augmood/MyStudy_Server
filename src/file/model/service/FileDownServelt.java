package file.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownServelt
 */
@WebServlet("/file/down")
public class FileDownServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String filePath = request.getParameter("file-path");
		// 기존 응답처리 -> html 
		// 다운로드는 페이지가 아니라 파일이 응답 되어야 함.
		// html이 아닌 application 파일로 설정해줌 (오타주의)
		File file = new File(filePath);
		response.setContentType("application/octet-stream");
		// 파일의 크기, 파일 이름이 필요
		response.setContentLength((int)file.length());
		String fileName = new String(file.getName().getBytes(), "ISO-8859-1");
		response.setHeader("Content-Dispositon", "attachment;filename="+fileName);
		
		// 파일을 보내기 위한 스트림 생성
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream output = response.getOutputStream();
		
		byte [] outputByte = new byte[4096]; // 4*1024 -> 4K
		while(fileIn.read(outputByte, 0, 4096) != -1) { // 읽어들이기
			output.write(outputByte, 0, 4096); // 파일 다운로드
		}
		fileIn.close();
		output.flush();
		output.close();
	}

}
