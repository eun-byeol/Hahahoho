package com.ssafy.user.model;

public class UserChangeDto {
	private Integer userNo;
	private String userPwd;
	private String userPwd2;
	
	public UserChangeDto() {
		
	}

	public UserChangeDto(Integer userNo, String userPwd, String userPwd2) {
		super();
		this.userNo = userNo;
		this.userPwd = userPwd;
		this.userPwd2 = userPwd2;
	}
	
	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwd2() {
		return userPwd2;
	}

	public void setUserPwd2(String userPwd2) {
		this.userPwd2 = userPwd2;
	}

	@Override
	public String toString() {
		return "UserChangeDto [userPwd=" + userPwd + ", userPwd2=" + userPwd2 + "]";
	}
	
	
}
