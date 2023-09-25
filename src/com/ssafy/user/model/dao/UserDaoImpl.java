package com.ssafy.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.user.model.UserChangeDto;
import com.ssafy.user.model.UserCheckAnswerDto;
import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.UserLoginDto;
import com.ssafy.user.model.UserPageDto;
import com.ssafy.util.DBUtil;


public class UserDaoImpl implements UserDao {
	
	private static UserDaoImpl userDaoImpl = new UserDaoImpl();
	
	public static UserDaoImpl getUserDaoInstance() {
		return userDaoImpl;
	}
	
	DBUtil userDb = DBUtil.getInstance(); 
	@Override
	public int insertUser(UserDto userDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = userDb.getConnection();
			
			String sql = "insert user (user_id, user_pwd, user_name, user_email, user_age, question, answer)"
					+ " values(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userDto.getUserId());
			pstmt.setString(2, userDto.getUserPwd());
			pstmt.setString(3, userDto.getUserName());
			pstmt.setString(4, userDto.getUserEmail());
			pstmt.setInt(5, userDto.getUserAge());
			pstmt.setString(6, userDto.getQuestion());
			pstmt.setString(7, userDto.getAnswer());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			userDb.close(conn, pstmt);
		}
		
		return 1;
	}
	@Override
	public UserLoginDto getLoginUser(UserLoginDto userLoginDto) throws SQLException {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = userDb.getConnection();
			String sql = "select user_uuid, user_id, user_pwd from user where user_id =?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userLoginDto.getUserId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserLoginDto newUserLoginDto = new UserLoginDto();
				newUserLoginDto.setUserNo(rs.getInt("user_uuid"));
				newUserLoginDto.setUserId(rs.getString("user_id"));
				newUserLoginDto.setUserPwd(rs.getString("user_pwd"));
				return newUserLoginDto;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			userDb.close(conn, pstmt, rs);
		}
		return null;
	}
	@Override
	public UserPageDto getUserPageDto(int userNo) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = userDb.getConnection();
			String sql = "select user_pwd, user_name, user_email, user_age, question, answer from user where user_uuid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserPageDto userPageDto = new UserPageDto();
				userPageDto.setUserPwd(rs.getString("user_pwd"));
				userPageDto.setUserName(rs.getString("user_name"));
				userPageDto.setUserEmail(rs.getString("user_email"));
				userPageDto.setUserAge(rs.getInt("user_age"));
				userPageDto.setQuestion(rs.getString("question"));
				userPageDto.setAnswer(rs.getString("answer"));
				return userPageDto;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			userDb.close(conn, pstmt, rs);
		}
		return null;
	}
	@Override
	public int updateUser(UserPageDto userPageDto) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = userDb.getConnection();
			String sql = "update user set user_pwd = ?, user_Name = ?, user_email = ?, user_age = ?, question = ?, answer = ? where user_uuid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userPageDto.getUserPwd());
			pstmt.setString(2, userPageDto.getUserName());
			pstmt.setString(3, userPageDto.getUserEmail());
			pstmt.setInt(4, userPageDto.getUserAge());
			pstmt.setString(5, userPageDto.getQuestion());
			pstmt.setString(6, userPageDto.getAnswer());
			pstmt.setInt(7, userPageDto.getUserNo());
			
			pstmt.executeUpdate();
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 수정 실패");
		}finally {
			userDb.close(conn, pstmt);
		}
	}
	@Override
	public int deleteUser(int userNo) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = userDb.getConnection();
			String sql = "delete from user where user_uuid = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.executeUpdate();
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 삭제 실패");
		}finally {
			userDb.close(conn, pstmt);
		}
	}
	@Override
	public String selectUserName(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = userDb.getConnection();
			String sql = "select user_name from user where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("user_name");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 조회 실패");
		}finally {
			userDb.close(conn, pstmt, rs);
		}
		return result;
	}
	@Override
	public String selectUserEmail(String userEmail) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = userDb.getConnection();
			String sql = "select question from user where user_email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("question");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원 조회 실패");
		}finally {
			userDb.close(conn, pstmt, rs);
		}
		return result;
	}
	@Override
	public UserCheckAnswerDto getAnswer(UserCheckAnswerDto userCheckAnswerDto) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = userDb.getConnection();
			String sql = "select user_uuid, answer from user where user_email = ? and question = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userCheckAnswerDto.getUserEmail());
			pstmt.setString(2, userCheckAnswerDto.getQuestion());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserCheckAnswerDto loadDto = new UserCheckAnswerDto();
				loadDto.setUserNo(rs.getInt("user_uuid"));
				loadDto.setUserEmail(userCheckAnswerDto.getUserEmail());
				loadDto.setQuestion(userCheckAnswerDto.getQuestion());
				loadDto.setAnswer(rs.getString("answer"));
				return loadDto;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			userDb.close(conn, pstmt, rs);
		}
		return null;
	}
	@Override
	public int updatePwd(UserChangeDto userChangeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = userDb.getConnection();
			String sql = "update user set user_pwd = ? where user_uuid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userChangeDto.getUserPwd());
			pstmt.setInt(2, userChangeDto.getUserNo());
			
			pstmt.executeUpdate();
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("비밀번호 수정 실패");
		}finally {
			userDb.close(conn, pstmt);
		}
	}
}
