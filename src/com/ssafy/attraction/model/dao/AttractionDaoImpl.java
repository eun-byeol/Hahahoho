package com.ssafy.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
	
	private static AttractionDao attractionDao = new AttractionDaoImpl();
	
	private AttractionDaoImpl() {};
	
	public static AttractionDao getAttractionDao() {
		return attractionDao;
	}

	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
		List<AttractionInfoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			
			String sql = "";
			if (attractionInfoDto.getSidoCode() != 0 && attractionInfoDto.getContentTypeId() != 0) {
				sql = "select * from attraction_info where sido_code=? and content_type_id=? order by readcount desc";
			}
			else if (attractionInfoDto.getSidoCode() == 0 && attractionInfoDto.getContentTypeId() != 0) {
				sql = "select * from attraction_info where content_type_id=? order by readcount desc";
			}
			else if (attractionInfoDto.getSidoCode() != 0 && attractionInfoDto.getContentTypeId() == 0) {
				sql = "select * from attraction_info where sido_code=? order by readcount desc";
			}
			else {
				sql = "select * from attraction_info order by readcount desc";
			}
			
			pstmt = conn.prepareStatement(sql);
			int num = 1;
			if (attractionInfoDto.getSidoCode() != 0) {
				pstmt.setString(num++,  Integer.toString(attractionInfoDto.getSidoCode()));
			}
			if (attractionInfoDto.getContentTypeId() != 0) {
				pstmt.setString(num,  Integer.toString(attractionInfoDto.getContentTypeId()));
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AttractionInfoDto attractionInfo = new AttractionInfoDto();
				attractionInfo.setContentId(rs.getInt("content_id"));
				attractionInfo.setContentTypeId(rs.getInt("content_type_id"));
				attractionInfo.setTitle(rs.getString("title"));
				attractionInfo.setAddr1(rs.getString("addr1"));
				attractionInfo.setAddr2(rs.getString("addr2"));
				attractionInfo.setZipcode(rs.getString("zipcode"));
				attractionInfo.setTel(rs.getString("tel"));
				attractionInfo.setFirstImage(rs.getString("first_image"));
				attractionInfo.setFirstImage2(rs.getString("first_image2"));
				attractionInfo.setReadcount(rs.getInt("readcount"));
				attractionInfo.setSidoCode(rs.getInt("sido_code"));
				attractionInfo.setGugunCode(rs.getInt("gugun_code"));
				attractionInfo.setLatitude(rs.getDouble("latitude"));
				attractionInfo.setLongitude(rs.getDouble("longitude"));
				attractionInfo.setMlevel(rs.getString("mlevel"));
				
				list.add(attractionInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.getInstance().close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
		List<AttractionInfoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			String sql = "select * from attraction_info where title like ? and sido_code=?";
			
			if (sidoCode == 0) {
				sql = "select * from attraction_info where title like ?";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + title + "%");
			if (sidoCode != 0) {
				pstmt.setString(2,  Integer.toString(sidoCode));
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AttractionInfoDto attractionInfo = new AttractionInfoDto();
				attractionInfo.setContentId(rs.getInt("content_id"));
				attractionInfo.setContentTypeId(rs.getInt("content_type_id"));
				attractionInfo.setTitle(rs.getString("title"));
				attractionInfo.setAddr1(rs.getString("addr1"));
				attractionInfo.setAddr2(rs.getString("addr2"));
				attractionInfo.setZipcode(rs.getString("zipcode"));
				attractionInfo.setTel(rs.getString("tel"));
				attractionInfo.setFirstImage(rs.getString("first_image"));
				attractionInfo.setFirstImage2(rs.getString("first_image2"));
				attractionInfo.setReadcount(rs.getInt("readcount"));
				attractionInfo.setSidoCode(rs.getInt("sido_code"));
				attractionInfo.setGugunCode(rs.getInt("gugun_code"));
				attractionInfo.setLatitude(rs.getDouble("latitude"));
				attractionInfo.setLongitude(rs.getDouble("longitude"));
				attractionInfo.setMlevel(rs.getString("mlevel"));
				
				list.add(attractionInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.getInstance().close(rs, pstmt, conn);
		}
		return list;
	}

}
