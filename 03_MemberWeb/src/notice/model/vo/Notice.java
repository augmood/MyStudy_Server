package notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;
	private String noticeSubject;
	private String noticeContents;
	private String writerId;
	private Date regDate;
	
	public Notice() {}
	

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSubject() {
		return noticeSubject;
	}

	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}

	public String getNoticeContents() {
		return noticeContents;
	}

	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Notice [공지번호=" + noticeNo + ", 제목=" + noticeSubject + ", 내용="
				+ noticeContents + ", 작성자 ID=" + writerId + ", 등록일=" + regDate + "]";
	}
	
	

}
