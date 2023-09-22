package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;

public interface AttractionService {
	
	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
}
