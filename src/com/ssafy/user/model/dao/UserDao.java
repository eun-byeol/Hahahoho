package com.ssafy.user.model.dao;

import java.sql.SQLException;

import com.ssafy.user.model.UserChangeDto;
import com.ssafy.user.model.UserCheckAnswerDto;
import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.UserLoginDto;
import com.ssafy.user.model.UserPageDto;

public interface UserDao {
	public int insertUser(UserDto userDto) throws SQLException;
	
	public UserLoginDto getLoginUser(UserLoginDto userLoginDto) throws SQLException;
	
	public UserPageDto getUserPageDto(int userNo) throws SQLException;
	
	public int updateUser(UserPageDto userPageDto) throws SQLException;
	
	public int deleteUser(int userNo) throws SQLException;
	
	public String selectUserName(String userId) throws SQLException;
	
	public String selectUserEmail(String userEmail) throws SQLException;
	
	public UserCheckAnswerDto getAnswer(UserCheckAnswerDto userCheckAnswerDto) throws SQLException;
	
	public int updatePwd(UserChangeDto userChangeDto) throws SQLException;
}
