package com.ssafy.user.model;

public class UserLoginDto {
	private int userNo;
	private String userId; 
	private String userPwd;
	
	public UserLoginDto() {
		// TODO Auto-generated constructor stub
	}

	public UserLoginDto(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd= userPwd;
	}
	
	
	
}
