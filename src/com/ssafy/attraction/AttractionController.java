package com.ssafy.attraction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.attraction.model.service.AttractionServiceImpl;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttractionServiceImpl attractionService = AttractionServiceImpl.getServiceInstance();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		switch (act) {
			case "map":
				request.getRequestDispatcher("enjoytrip/map.jsp").forward(request, response);
				break;
			
		}
	}
	
	
}
