package com.ssafy.attraction.model.dao;

import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;

public interface AttractionDao {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);

}
