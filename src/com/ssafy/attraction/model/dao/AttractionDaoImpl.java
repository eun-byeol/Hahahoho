package com.ssafy.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
	
	private static AttractionDao attractionDao = new AttractionDaoImpl();
	
	private AttractionDaoImpl() {};
	
	public static AttractionDao getAttractionDao() {
		return attractionDao;
	}

	@Override
	public List<AttractionInfoDto> listAttraction(AttractionInfoDto attractionInfoDto) throws SQLException {
		List<AttractionInfoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder("select * from attraction_info");		
			
			boolean isFirst = true;
			if (attractionInfoDto.getSidoCode() != 0) {
				sql.append(" where sido_code=?");
				isFirst = false;
			}
			if (attractionInfoDto.getGugunCode() != 0) {
				if (isFirst) {
					sql.append(" where gugun_code=?");
					isFirst = false;
				}
				else {
					sql.append(" and gugun_code=?");
				}
			}
			if (attractionInfoDto.getContentTypeId() != 0) {
				if (isFirst) {
					sql.append(" where content_type_id=?");
					isFirst = false;
				}
				else {
					sql.append(" and content_type_id=?");
				}
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int num = 1;
			
			if (attractionInfoDto.getSidoCode() != 0) {
				pstmt.setString(num++,  Integer.toString(attractionInfoDto.getSidoCode()));
			}
			if (attractionInfoDto.getGugunCode() != 0) {
				pstmt.setString(num++,  Integer.toString(attractionInfoDto.getGugunCode()));
			}			
			if (attractionInfoDto.getContentTypeId() != 0) {
				pstmt.setString(num,  Integer.toString(attractionInfoDto.getContentTypeId()));
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AttractionInfoDto attraction = new AttractionInfoDto();
				attraction.setContentId(rs.getInt("content_id"));
				attraction.setContentTypeId(rs.getInt("content_type_id"));
				attraction.setTitle(rs.getString("title"));
				attraction.setAddr1(rs.getString("addr1"));
				attraction.setAddr2(rs.getString("addr2"));
				attraction.setZipcode(rs.getString("zipcode"));
				attraction.setTel(rs.getString("tel"));
				attraction.setFirstImage(rs.getString("first_image"));
				attraction.setFirstImage2(rs.getString("first_image2"));
				attraction.setReadcount(rs.getInt("readcount"));
				attraction.setSidoCode(rs.getInt("sido_code"));
				attraction.setGugunCode(rs.getInt("gugun_code"));
				attraction.setLatitude(rs.getDouble("latitude"));
				attraction.setLongitude(rs.getDouble("longitude"));
				attraction.setMlevel(rs.getString("mlevel"));
				
				list.add(attraction);
			}
		} finally {
			DBUtil.getInstance().close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public List<SidoDto> listSido() throws SQLException {
		List<SidoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			
			String sql = "select * from sido";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SidoDto sido = new SidoDto();
				sido.setSidoCode(rs.getInt("sido_code"));
				sido.setSidoName(rs.getString("sido_name"));

				list.add(sido);
			}
		} finally {
			DBUtil.getInstance().close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public List<GugunDto> listGugun(int sidoCode) throws SQLException {
		List<GugunDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			
			String sql = "select * from gugun where sido_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  Integer.toString(sidoCode));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GugunDto gugun = new GugunDto();
				gugun.setGugunCode(rs.getInt("gugun_code"));
				gugun.setGugunName(rs.getString("gugun_name"));
				gugun.setSidoCode(sidoCode);

				list.add(gugun);
			}
		} finally {
			DBUtil.getInstance().close(rs, pstmt, conn);
		}
		
		return list;
	}

}
