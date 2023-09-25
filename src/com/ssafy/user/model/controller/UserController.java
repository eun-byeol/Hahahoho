package com.ssafy.user.model.controller;

import java.io.IOException;
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
		System.out.println(action);
		System.out.println(method);
		
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
			request.setAttribute("msg", "회원가입에 실패했습니다.");
			e.printStackTrace();
			response.sendError(500);
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
//					HttpSession session = request.getSession();
//					session.setAttribute("loginUser", loadUserNo);
					response.sendRedirect("/user/login.jsp");
//					response.sendRedirect("/enjoytrip/map.jsp");
					return;
				}
			}
		}
		
		try {
			loadUserNo = userService.userLogin(userLoginDto);
			
		}catch(Exception e) {
			request.setAttribute("msg", "로그인 실패.");
			e.printStackTrace();
			response.sendError(500);
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
		
		response.sendRedirect("/enjoytrip/map.jsp");
	}
	
	protected void viewModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer userNo = (Integer)session.getAttribute("loginUser");
		System.out.println(userNo);
		UserPageDto userPageDto;
		try {
			userPageDto = userService.userInfo(userNo);
		}catch(SQLException e) {
			request.setAttribute("msg", "회원 정보 불러오기 실패");
			e.printStackTrace();
			response.sendError(500);
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
			request.setAttribute("msg", "회원 정보 변경 실패");
			e.printStackTrace();
			response.sendError(500);
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
		response.sendRedirect("/user?action=login");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer userNo = (Integer)session.getAttribute("loginUser");
		
		try {
			int cnt = userService.quitUser(userNo);
		}catch(SQLException e) {
			request.setAttribute("msg", "회원 탈퇴 실패");
			e.printStackTrace();
			response.sendError(500);
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
			request.setAttribute("msg", "로그인 실패.");
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		
		response.setContentType("application/json;charset=utf-8");
		
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("userName", userName);
		
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.getRequestDispatcher("/user/findPasswordById.jsp").forward(request, response);
//		response.getWriter().write(new ObjectMapper().writeValueAsString(resultMap));
	}
	
	protected void doFindByQuest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userEmail = request.getParameter("userEmail");
		
		String question;
		
		try {
			question = userService.userFindEmail(userEmail);
			
		}catch(Exception e) {
			request.setAttribute("msg", "이메일 찾기 실패.");
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		
//		response.setContentType("application/json;charset=utf-8");
		
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("userName", userName);
		
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("question", question);
		request.getRequestDispatcher("/user/findPasswordByQuestion.jsp").forward(request, response);
//		response.getWriter().write(new ObjectMapper().writeValueAsString(resultMap));
	}
}
