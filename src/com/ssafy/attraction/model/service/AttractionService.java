package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

public interface AttractionService {
	
	List<AttractionInfoDto> listAttraction(AttractionInfoDto attractionInfoDto) throws SQLException;
	List<SidoDto> listSido() throws SQLException;
	List<GugunDto> listGugun(int sidoCode) throws SQLException;
	
}
