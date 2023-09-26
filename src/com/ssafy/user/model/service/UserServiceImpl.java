package com.ssafy.user.model.service;

import java.sql.SQLException;

import com.ssafy.user.model.UserChangeDto;
import com.ssafy.user.model.UserCheckAnswerDto;
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
		// TODO Auto-generated method stubchangePwd
		userDao.insertUser(userDto);
		return 1;	
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
		userDao.updateUser(userPageDto);
		return 1;
	}

	@Override
	public int quitUser(int userNo) throws SQLException {
		// TODO Auto-generated method stub
		userDao.deleteUser(userNo);
		return 1;
	}

	@Override
	public String userFindName(String userId) throws SQLException {
		// TODO Auto-generated method stub
		String result = userDao.selectUserName(userId);
		if(result == null)
			throw new SQLException("해당하는 아이디가 없습니다.");
		return result;
	}

	@Override
	public String userFindEmail(String userEmail) throws SQLException {
		String result = userDao.selectUserEmail(userEmail);
		if(result == null)
			throw new SQLException("해당하는 이메일이 없습니다.");
		return result;
	}

	@Override
	public Integer userCheckAnswer(UserCheckAnswerDto userCheckAnswerDto) throws SQLException {
		// TODO Auto-generated method stub
		UserCheckAnswerDto loadAnswer = userDao.getAnswer(userCheckAnswerDto);
		if(loadAnswer == null) {
			throw new SQLException("아이디가 없습니다.");
		}
		else if(!loadAnswer.getAnswer().equals(userCheckAnswerDto.getAnswer())) {
			throw new SQLException("정답이 아닙니다.");
		}
		return loadAnswer.getUserNo();
	}

	@Override
	public int changePwd(UserChangeDto userChangeDto) throws SQLException {
		userDao.updatePwd(userChangeDto);
		return 1;
	}

}
