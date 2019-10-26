package userVO;

import java.sql.Blob;

public class UserVO {
	private String userName;
	private String userID;
	private String userPW;
	private String userEmail;
	private String userGender;
	private String userIntro;
	
	
	public UserVO() {
	}


	public UserVO(String userName, String userID, String userPW, String userEmail, String userGender,
			String userIntro) {
		super();
		this.userName = userName;
		this.userID = userID;
		this.userPW = userPW;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userIntro = userIntro;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserPW() {
		return userPW;
	}


	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserGender() {
		return userGender;
	}


	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}


	public String getUserIntro() {
		return userIntro;
	}


	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}
	
	
	
	
//	private Blob userPhoto;
}
