package file.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import file.model.service.FileService;
import file.model.vo.FileData;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/file/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/file/upload.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업로드 폴더에 실제 파일을 저장하는 작업
		// 프로젝트 내의 실제 경로를 가지고 옴 (이것만 돌렸을때는 경로만 출력됨, 업로드 폴더에 파일이 저장되진 않음 -> 올려지긴 했음)
		// 멀티파트리퀘스트 객체를 생성하면 파일 저장됨 -> 편리한 방식
		// 폴더에 파일 자체를 넣어버리고 싶다면 서버 설정값 변경해줌 -> 서버옵션 -> 서버 모델링 어쩌구 클림
		HttpSession session = request.getSession();
		String fileUser = (String)session.getAttribute("userId");
		String uploadFilePath = request.getServletContext().getRealPath("upload");
//		System.out.println("업로드 리얼 패스 ="+uploadFilePath);
		// 5MB -> 10^6
		int uploadFileLimit = 5*1024*1024;
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
				uploadFileLimit, encType, new DefaultFileRenamePolicy());
		// 2. 
		String fileName = multi.getFilesystemName("upFile");
		File uploadFile = multi.getFile("upFile"); 
		String filePath = uploadFile.getPath();
		long fileSize = uploadFile.length();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		 // 시간을 시분초로 볼 수 있음
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		FileData fileData = new FileData();
		fileData.setFileName(fileName);
		fileData.setFilePath(filePath);
		fileData.setFileSize(fileSize);
		fileData.setFileUser(fileUser);
		fileData.setUploadTime(uploadTime);
		
		int result = new FileService().registerFileInfo(fileData);
		if(result > 0) {
			response.sendRedirect("/file/list");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/file/fileError.html").forward(request, response);
		}
	}

}
