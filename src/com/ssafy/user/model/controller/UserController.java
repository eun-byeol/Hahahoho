package com.ssafy.user.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.user.model.UserChangeDto;
import com.ssafy.user.model.UserCheckAnswerDto;
import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.UserLoginDto;
import com.ssafy.user.model.UserPageDto;
import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserService userService = UserServiceImpl.getServicImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		String method = request.getMethod();
		Integer login = (Integer)request.getSession().getAttribute("loginUser");
		
		if(login == null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if((cookie.getName()).equals("userNo")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
		
		if(method.equals("POST")) {
			switch(action) {
				case "login":
					doLogin(request, response);
					break;
				case "regist":
					doRegist(request, response);
					break;
				case "modify":
					doModify(request, response);
					break;
				case "logout":
					doLogout(request, response);
					break;
				case "findById":
					doFindById(request, response);
					break;
				case "findByQuest":
					doFindByQuest(request, response);
					break;
				case "change":
					if(request.getAttribute("method") != null) {
						request.getRequestDispatcher("/user/changePassword.jsp").forward(request, response);
						request.setAttribute("method", "post");
					}
					else
						doChange(request, response);
					break;
			}
		}
		else {
			switch(action) {
				case "index":
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					break;
				case "login":
					request.getRequestDispatcher("/user/login.jsp").forward(request, response);
					break;
				case "regist":
					request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
					break;
				case "modify":
					viewModify(request, response);
					break;
				case "logout":
					doLogout(request, response);
					break;
				case "delete":
					doDelete(request, response);
					break;
				case "findById":
					request.getRequestDispatcher("/user/findPasswordById.jsp").forward(request, response);
					break;
				case "findByQuest":
					request.getRequestDispatcher("/user/findPasswordByQuestion.jsp").forward(request, response);
					break;
				case "change":
					request.getRequestDispatcher("/user/changePassword.jsp").forward(request, response);
					break;
			}
		}
		
	}
	protected void doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		UserDto userDto = new UserDto(userId, userName, userPwd, userEmail, userAge, question, answer);
		
		try {
			int cnt = userService.registUser(userDto);
			response.sendRedirect("/user?action=login");
		}catch(SQLException e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=regist';</script>"); 
			writer.close();
			return;
		}
	}

	protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int loadUserNo;
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		UserLoginDto userLoginDto = new UserLoginDto(userId, userPwd);
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("userNo")) {
					response.sendRedirect("/user/login.jsp");
					return;
				}
			}
		}
		
		try {
			loadUserNo = userService.userLogin(userLoginDto);
			
		}catch(Exception e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=login';</script>"); 
			writer.close();
			return;
		}
		// 쿠키 설정
		Cookie userNo = new Cookie("userNo", String.valueOf(loadUserNo));
		// 쿠키 소멸 시간 설정 ( 1일 )
		userNo.setMaxAge(60*60*24);
		// 응답 헤더에 쿠키 추가
		response.addCookie(userNo);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loadUserNo);
		
		response.sendRedirect("/attraction?action=goMap");
	}
	
	protected void viewModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer userNo = (Integer)session.getAttribute("loginUser");
		UserPageDto userPageDto;
		try {
			userPageDto = userService.userInfo(userNo);
		}catch(SQLException e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=modify';</script>"); 
			writer.close();
			return;
		}
		request.setAttribute("userInfo", userPageDto);
		request.getRequestDispatcher("/user/userControl.jsp").forward(request, response);
	}
	
	protected void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		UserPageDto userPageDto = new UserPageDto(userNo, userName, userPwd, userEmail, userAge, question, answer);
		
		try {
			int cnt = userService.modifyUser(userPageDto);
		}catch(SQLException e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=modify';</script>"); 
			writer.close();
			return;
		}
		// 로그아웃으로 보내기
		request.getRequestDispatcher("/user?action=logout").forward(request, response);
	}
	
	protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer userNo = (Integer)session.getAttribute("loginUser");
		
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if((cookie.getName()).equals("userNo")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('로그아웃 되었습니다.'); location.href='user?action=login';</script>"); 
		writer.close();
		return;
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer userNo = (Integer)session.getAttribute("loginUser");
		
		try {
			int cnt = userService.quitUser(userNo);
		}catch(SQLException e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=modify';</script>"); 
			writer.close();
			return;
		}
		// 로그아웃
		
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if((cookie.getName()).equals("userNo")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
		response.sendRedirect("/user?action=logout");
	}
	
	protected void doFindById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
		
		String userName;
		
		try {
			userName = userService.userFindName(userId);
		}catch(Exception e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=findById';</script>"); 
			writer.close();
			return;
		}
		
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("check", true);
		request.getRequestDispatcher("/user/findPasswordById.jsp").forward(request, response);
	}
	
	protected void doFindByQuest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userEmail = request.getParameter("userEmail");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		if(question != "" && answer != "") {
			checkAnswer(request, response);
			return;
		}
		
		try {
			question = userService.userFindEmail(userEmail);
			
		}catch(Exception e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.replace('user?action=findByQuest');</script>"); 
			writer.close();
			return;
		}
		
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("question", question);
		request.getRequestDispatcher("/user/findPasswordByQuestion.jsp").forward(request, response);
	}
	
	protected void checkAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Integer userNum = null;
		
		String userEmail = request.getParameter("userEmail");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		UserCheckAnswerDto userCheckAnswerDto = new UserCheckAnswerDto(userEmail, question, answer);
		
		try {
			userNum = userService.userCheckAnswer(userCheckAnswerDto);
			
		}catch(Exception e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=findByQuest';</script>"); 
			writer.close();
			return;
		}

		request.setAttribute("userNo", userNum);
		request.setAttribute("method", "get");
		request.getRequestDispatcher("/user?action=change").forward(request, response);
	}
	
	protected void doChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Integer userNum = Integer.parseInt(request.getParameter("userNo"));
		String userPwd = request.getParameter("password");
		String userPwd2 = request.getParameter("password2");
		
		if(userNum == null || !userPwd.equals(userPwd2)) {
			//오류 메세지
			String msg = "비밀번호, 비밀번호 확인이 일치하지 않습니다.";
			request.setAttribute("method", "post");
			request.setAttribute("userNo", userNum);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.replace('user?action=findByQuest');</script>"); 
			writer.close();
			return;
		}
		UserChangeDto userChangeDto = new UserChangeDto(userNum, userPwd, userPwd2);
		
		try {
			int cnt = userService.changePwd(userChangeDto);
		}catch(SQLException e) {
			String msg = e.getMessage();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg+ "'); location.href='user?action=findByQuest';</script>"); 
			writer.close();
			return;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('비밀번호 변경 완료'); location.href='/user?action=login';</script>"); 
		writer.close();
		return;
	}
}
