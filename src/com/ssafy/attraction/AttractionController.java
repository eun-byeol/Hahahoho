package com.ssafy.attraction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.service.AttractionServiceImpl;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttractionServiceImpl attractionService = AttractionServiceImpl.getServiceInstance();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
			case "goMap":
				request.getRequestDispatcher("/enjoytrip/map.jsp").forward(request, response);
				break;
			case "search":
				doSearch(request, response);
				break;
			case "searchSido":
				doSearchSido(request, response);
				break;
			case "searchGugun":
				doSearchGugun(request, response);
				break;
		}
	}

	private void doSearchGugun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GugunDto> guguns = new ArrayList<>();
		int sidoCode = Integer.parseInt(request.getParameter("sidoCode")); 
		
		try {
			guguns = attractionService.listGugun(sidoCode);
			
			response.setContentType("application/json;charset=utf-8");
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("infos", guguns);
			
			response.getWriter().write(new ObjectMapper().writeValueAsString(resultMap));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doSearchSido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SidoDto> sidos = new ArrayList<>();
		
		try {
			sidos = attractionService.listSido();
			
			response.setContentType("application/json;charset=utf-8");
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("infos", sidos);
			
			response.getWriter().write(new ObjectMapper().writeValueAsString(resultMap));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AttractionInfoDto attractionInfo = new AttractionInfoDto();
		List<AttractionInfoDto> attractions = new ArrayList<>();
		
		attractionInfo.setSidoCode(Integer.parseInt(request.getParameter("sidoCode")));
		attractionInfo.setGugunCode(Integer.parseInt(request.getParameter("gugunCode")));
		attractionInfo.setContentTypeId(Integer.parseInt(request.getParameter("contentTypeId")));
		
		try {
			attractions = attractionService.listAttraction(attractionInfo);
			
			response.setContentType("application/json;charset=utf-8");
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("infos", attractions);
			
			response.getWriter().write(new ObjectMapper().writeValueAsString(resultMap));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
