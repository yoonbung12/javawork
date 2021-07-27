package guest.domain;

import java.util.List;

public class MessageListView {
	
	 
	private List<Message> messageList;  // 게시물 Message 객체 3개
	private int messageTotalCount; 		// 전체 게시물의 개수
	private int currentPagenumber; 		// 현재 페이지 번호
	private int pageTotalCount; 		// 페이지의 개수
	private int messageCountPerpage;	// 한 페이지에 표현할 메시지의 개수
	private int firstRow;				// DB 게시물의 시작 위치
	private int endRow;					// 마지막 위치
	
	public MessageListView(List<Message> messageList, int messageTotalCount, int currentPagenumber, 
			int messageCountPerpage, int firstRow, int endRow) {
		super();
		this.messageList = messageList;
		this.messageTotalCount = messageTotalCount;
		this.currentPagenumber = currentPagenumber;
		this.messageCountPerpage = messageCountPerpage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calpageTotalCount();
	}
	
	private void calpageTotalCount() {
		
		if(this.messageTotalCount == 0) {
			
			this.pageTotalCount = 0;
		} else {
			// 10 / 3 -> 3    10%3 > 0 -> 3+1
			this.pageTotalCount = this.messageTotalCount / this.messageCountPerpage;
			if(this.messageTotalCount / this.messageCountPerpage > 0) {
				this.pageTotalCount++;
			}
		}
			
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public void setMessageTotalCount(int messageTotalCount) {
		this.messageTotalCount = messageTotalCount;
	}

	public int getCurrentPagenumber() {
		return currentPagenumber;
	}

	public void setCurrentPagenumber(int currentPagenumber) {
		this.currentPagenumber = currentPagenumber;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getMessageCountPerpage() {
		return messageCountPerpage;
	}

	public void setMessageCountPerpage(int messageCountPerpage) {
		this.messageCountPerpage = messageCountPerpage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	
}
