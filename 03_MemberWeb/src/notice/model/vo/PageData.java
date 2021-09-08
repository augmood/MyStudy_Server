package notice.model.vo;

import java.util.List;

public class PageData {
	
	// 이걸 왜 만들었는지?
	// printallList에서 
	// 리스트와 페이지리스트 두개의 값을 리턴 할 수 없기 때문에 두개의 값을 여기에 넣고 한번에 출력시키려고
	private List<Notice> noticeList;
	private String pageNavi;
	
	public PageData() {}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "PageData [noticeList=" + noticeList + ", pageNavi=" + pageNavi + "]";
	}
	
	

}
