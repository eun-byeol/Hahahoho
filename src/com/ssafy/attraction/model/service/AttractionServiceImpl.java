package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.dao.AttractionDao;
import com.ssafy.attraction.model.dao.AttractionDaoImpl;

public class AttractionServiceImpl implements AttractionService {
	
	private static AttractionServiceImpl attractionServiceImpl = new AttractionServiceImpl();
	private static AttractionDao attractionDao = AttractionDaoImpl.getAttractionDao();
	
	private AttractionServiceImpl() {}
	
	public static AttractionServiceImpl getServiceInstance() {
		return attractionServiceImpl;
	}

	@Override
	public List<AttractionInfoDto> listAttraction(AttractionInfoDto attractionInfoDto) throws SQLException {
		List<AttractionInfoDto> resultList = attractionDao.listAttraction(attractionInfoDto);
		
		if(resultList == null) {
			System.out.println("해당하는 정보가 없습니다.");
			return null;
		}
		else {
			return resultList;
		}
	}

	@Override
	public List<SidoDto> listSido() throws SQLException {
		return attractionDao.listSido();
	}

	@Override
	public List<GugunDto> listGugun(int sidoCode) throws SQLException {
		return attractionDao.listGugun(sidoCode);
	}

}
