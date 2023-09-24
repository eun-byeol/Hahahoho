package com.ssafy.user.model.service;

import java.sql.SQLException;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.UserLoginDto;
import com.ssafy.user.model.UserPageDto;

public interface UserService {
	
	public int registUser(UserDto userDto) throws SQLException;
	
	public int userLogin(UserLoginDto userLoginDto) throws SQLException;
	
	public UserPageDto userInfo(int userNo) throws SQLException;

	public int modifyUser(UserPageDto userPageDto) throws SQLException;
	
	public int quitUser(int userNo) throws SQLException;
}
