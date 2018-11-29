package com.javalec.guestbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.javalec.guestbook.vo.GuestBookVo;

public class GuestBookMapper implements RowMapper<GuestBookVo> {

	
	public GuestBookVo mapRow(ResultSet rs, int rowNum)throws SQLException{
		
		GuestBookVo guestbook = new GuestBookVo() ;
		
		guestbook.setNo(rs.getLong("NO"));
		guestbook.setName(rs.getString("NAME"));
		guestbook.setPassword(rs.getString("PASSWORD"));
		guestbook.setRegDate(rs.getString("regDate"));
		guestbook.setContent(rs.getString("CONTENT"));
		
		return guestbook; 
	}
}
