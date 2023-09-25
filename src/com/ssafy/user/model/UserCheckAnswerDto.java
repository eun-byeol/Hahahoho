package com.ssafy.user.model;

public class UserCheckAnswerDto {
	private int userNo;
	private String userEmail;
	private String question;
	private String answer;
	
	public UserCheckAnswerDto() {
		
	}
	
	public UserCheckAnswerDto(String userEmail, String question, String answer) {
		super();
		this.userEmail = userEmail;
		this.question = question;
		this.answer = answer;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "UserCheckAnswerDto [userEmail=" + userEmail + ", question=" + question + ", answer=" + answer + "]";
	}
	
	
	
}
