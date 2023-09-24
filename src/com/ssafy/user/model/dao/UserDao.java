package com.ssafy.user.model.dao;

import java.sql.SQLException;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.UserLoginDto;
import com.ssafy.user.model.UserPageDto;

public interface UserDao {
	public int insertUser(UserDto userDto) throws SQLException;
	public UserLoginDto getLoginUser(UserLoginDto userLoginDto) throws SQLException;
	public UserPageDto getUserPageDto(int userNo) throws SQLException;
	public int updateUser(UserPageDto userPageDto) throws SQLException;
	public int deleteUser(int userNo) throws SQLException;
}
