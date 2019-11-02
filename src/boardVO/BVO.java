package boardVO;

public class BVO {
	private int bID;
	private String userID;
	private String bTitle;
	private String bContent;
	private String bDate;
	private int bHit;
	private int bLike;

	public BVO() {
	}
	public BVO(int bID, String userID, String bTitle, String bContent, String bDate, int bHit, int bLike) {
		super();
		this.bID = bID;
		this.userID = userID;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bLike = bLike;
	}
	public int getbID() {
		return bID;
	}
	public void setbID(int bID) {
		this.bID = bID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public int getbLike() {
		return bLike;
	}
	public void setbLike(int bLike) {
		this.bLike = bLike;
	}
	
	
	
}
