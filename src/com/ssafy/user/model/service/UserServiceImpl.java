package com.ssafy.user.model.service;

import java.sql.SQLException;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.UserLoginDto;
import com.ssafy.user.model.UserPageDto;
import com.ssafy.user.model.dao.UserDao;
import com.ssafy.user.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
	
	private static UserServiceImpl userServiceImpl = new UserServiceImpl();
	UserDao userDao = UserDaoImpl.getUserDaoInstance();
	
	public static UserServiceImpl getServicImpl() {
		return userServiceImpl;
	}

	@Override
	public int registUser(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		try{
			userDao.insertUser(userDto);
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 등록 실패");
		}
	}

	@Override
	public int userLogin(UserLoginDto userLoginDto) throws SQLException {
		// TODO Auto-generated method stub
		UserLoginDto loadUser = userDao.getLoginUser(userLoginDto);
		if(loadUser == null) {
			throw new SQLException("아이디가 없습니다.");
		}
		else if(!loadUser.getUserPwd().equals(userLoginDto.getUserPwd())) {
			throw new SQLException("비밀번호가 틀립니다.");
		}
		return loadUser.getUserNo();
	}

	@Override
	public UserPageDto userInfo(int userNo) throws SQLException {
		
		UserPageDto userPageDto = userDao.getUserPageDto(userNo);
		if(userPageDto == null) {
			throw new SQLException("등록된 회원이 아닙니다.");
		}
		return userPageDto;
	}

	@Override
	public int modifyUser(UserPageDto userPageDto) throws SQLException {
		// TODO Auto-generated method stub
		try {
			userDao.updateUser(userPageDto);
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 변경 실패");
		}
	}

	@Override
	public int quitUser(int userNo) throws SQLException {
		// TODO Auto-generated method stub
		try {
			userDao.deleteUser(userNo);
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 삭제 실패");
		}
	}

	@Override
	public String userFindName(String userId) throws SQLException {
		// TODO Auto-generated method stub
		try {
			return userDao.selectUserName(userId);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 조회 실패");
		}
	}

	@Override
	public String userFindEmail(String userEmail) throws SQLException {
		try {
			return userDao.selectUserEmail(userEmail);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 조회 실패");
		}
	}

}
