package boardVO;

public class RVO {
	private int rID;
	private String userID;
	private int bID;
	private String rContent;
	private String rDate;
	private int rLike;
	
	public RVO() {
	}
	public RVO(int rID, String userID, int bID, String rContent, String rDate, int rLike) {
		super();
		this.rID = rID;
		this.userID = userID;
		this.bID = bID;
		this.rContent = rContent;
		this.rDate = rDate;
		this.rLike = rLike;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getbID() {
		return bID;
	}
	public void setbID(int bID) {
		this.bID = bID;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public int getrLike() {
		return rLike;
	}
	public void setrLike(int rLike) {
		this.rLike = rLike;
	}

	
}
