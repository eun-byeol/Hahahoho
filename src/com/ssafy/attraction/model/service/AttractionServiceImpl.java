package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.dao.AttractionDao;
import com.ssafy.attraction.model.dao.AttractionDaoImpl;

public class AttractionServiceImpl implements AttractionService {
	
	// 싱글톤
	private static AttractionServiceImpl attractionServiceImpl = new AttractionServiceImpl();
//	AttractionDao attractionDao = new AttractionDaoImpl();
	
	private AttractionServiceImpl() {
	}
	
	public static AttractionServiceImpl getServiceInstance() {
		return attractionServiceImpl;
	}

	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
		int sidoCode = attractionInfoDto.getSidoCode();
		int contentTypeId = attractionInfoDto.getContentTypeId();
		
//		관광지 지역 선택
//		"0:전국, 1:서울, 2:인천, 3:대전, 4:대구, 5:광주, 6:부산, 7:울산, 8:세종특별자치시"
//		"31:경기도, 32:강원도, 33:충청북도, 34:충청남도, 35:경상북도, 36:경상남도, 37:전라북도, 38:전라남도, 39:제주도"
		
//		관광지 타입 선택
//		"0:모든타입, 12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점"
		
		if(0 > sidoCode || (8 < sidoCode && sidoCode < 31) || sidoCode > 39) {
			System.out.println("---- 지역을 잘못 입력하셨습니다.----");
			return null;
		}
		
		if(contentTypeId != 0 && contentTypeId != 12 && contentTypeId != 14 && contentTypeId != 15 && contentTypeId != 25
				&& contentTypeId != 28 && contentTypeId != 32 && contentTypeId != 38 && contentTypeId != 39) {
			System.out.println("----타입을 잘못 입력하셨습니다.----");
			return null;
		}
		
		// 타입을 주고 리스트로 정보 받아오기
		List<AttractionInfoDto> resultList = AttractionDaoImpl.getAttractionDao().attractionList(attractionInfoDto);
		
		if(resultList == null) {
			System.out.println("해당하는 정보가 없습니다.");
			return null;
		}
		else {
			return resultList;
		}
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
		// TODO Auto-generated method stub
//		"검색 할 관광지 이름 : "
//		"0:전국, 1:서울, 3:대전, 5:광주, 6:부산, 35:경상북도"
		
		if(title.length() == 0) {
			System.out.println("---- 관광지를 잘못 입력하셨습니다.----");
			return null;
		}
		
		if(sidoCode != 0 && sidoCode != 1 && sidoCode != 3 && sidoCode != 5 && sidoCode != 6
				&& sidoCode != 35) {
			System.out.println("----지역을 잘못 입력하셨습니다.----");
			return null;
		}
		// 타입을 주고 리스트로 정보 받아오기
		List<AttractionInfoDto> resultList = AttractionDaoImpl.getAttractionDao().searchByTitle(title, sidoCode);
		
		if(resultList == null) {
			System.out.println("해당하는 정보가 없습니다.");
			return null;
		}
		else {
			return resultList;
		}
	}

}
